package com.pncbank.registration.service.impl;

import com.pncbank.registration.dto.UserDto;
import com.pncbank.registration.entity.User;
import com.pncbank.registration.ip.IpInfo;
import com.pncbank.registration.mapper.UserMapper;
import com.pncbank.registration.repository.UserRepository;
import com.pncbank.registration.service.IRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.pncbank.registration.constants.RegistrationConstants.API_URL;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements IRegistrationService {

    private final UserRepository userRepository;


    /**
     * Registers a new user with the provided user data and generates a welcome message.
     *
     * @param userDto The user data transfer object containing user information.
     * @return A welcome message or an error message if registration fails.
     */
    @Override
    public String registerUser(UserDto userDto) {
        try {
            User user = UserMapper.mapToUser(userDto, new User());
            user.setCreatedAt(LocalDateTime.now());
            user.setCreatedBy("Anonymous");
            user.setUuid(generateRandomUuid());

            User userIpData = getUserIpData(user.getIpAddress());

            if (userIpData.getCountry().equalsIgnoreCase("Canada")) {
                user.setCity(userIpData.getCity());
                user.setCountry(userIpData.getCountry());
                userRepository.save(user);
            }

            return buildWelcomeMessage(user);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Retrieves additional user information based on the provided IP address.
     *
     * @param ipAddress The IP address for which to fetch additional information.
     * @return A User object containing city and country information based on the IP address.
     */
    private User getUserIpData(String ipAddress) {
        Optional<IpInfo> ipInfoOptional = Optional.ofNullable(getIpInfo(ipAddress));

        User user = new User();
        user.setCity("Invalid");
        user.setCountry("Invalid");

        ipInfoOptional.ifPresent(info -> {
            if ("Canada".equalsIgnoreCase(info.getCountry())) {
                user.setCity(info.getCity());
                user.setCountry(info.getCountry());
            }
        });

        return user;
    }

    /**
     * Retrieves IP information from an external API based on the provided IP address.
     *
     * @param ipAddress The IP address for which to fetch information.
     * @return An IpInfo object containing details about the provided IP address.
     */
    private IpInfo getIpInfo(String ipAddress) {
        try {
            String url = API_URL + ipAddress;

            WebClient webClient = WebClient.builder()
                    .baseUrl(url)
                    .codecs(configure -> configure.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                    .build();

            return webClient.get()
                    .retrieve()
                    .bodyToMono(IpInfo.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Generates a random UUID.
     *
     * @return A string representing a randomly generated UUID.
     */
    private static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Builds a welcome message for the user based on their registration information.
     *
     * @param user The registered user.
     * @return A welcome message for the user.
     */
    private String buildWelcomeMessage(User user) {
        StringBuilder message = new StringBuilder();
        if ("Invalid".equalsIgnoreCase(user.getCity())) {
            message.append("user is not eligible to register");
        } else {
            message.append("Welcome, ").append(user.getUserName()).append("!");
            message.append(" Your UUID is: ").append(user.getUuid());
            message.append(". You are in ").append(user.getCity());
        }
        return message.toString();
    }
}