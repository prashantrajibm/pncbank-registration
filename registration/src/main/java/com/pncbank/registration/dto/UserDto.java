package com.pncbank.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "User",
        description = "Schema to hold user information"
)
public class UserDto {

    @Schema(
            description = "Name of the user",
            example = "Jhon"
    )
    @NotBlank(message = "Username cannot be blank")
    @NotEmpty(message = "Username cannot be empty")
    private String userName;


    @Schema(
            description = "Password of the user",
            example = "P1assword$"
    )
    @NotBlank(message = "Password cannot be blank")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 9, message = "Password must be greater than 8 characters and contain at least 1 number, 1 special character from set '_ # $ % .'")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).{9,}$", message = "Invalid password format")
    private String password;

    @Schema(
            description = "IP Address of the user",
            example = "174.0.164.142"
    )
    @NotBlank(message = "IP address cannot be blank")
    @NotEmpty(message = "IP address cannot be empty")
    private String ipAddress;
}
