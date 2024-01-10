package com.pncbank.registration.controller;

import com.pncbank.registration.constants.RegistrationConstants;
import com.pncbank.registration.dto.ResponseDto;
import com.pncbank.registration.dto.UserDto;
import com.pncbank.registration.service.IRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class RegistrationController {

    private IRegistrationService iRegistrationService;

    @Operation(
            summary = "Create User Registration REST API",
            description = "This RESTful API enables the registration of new users within the PNC Bank system."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {
        String response = iRegistrationService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(RegistrationConstants.STATUS_201,
                response));
    }

}
