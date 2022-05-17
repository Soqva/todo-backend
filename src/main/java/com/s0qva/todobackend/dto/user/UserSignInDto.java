package com.s0qva.todobackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInDto {
    @NotBlank(message = "email must not be blank")
    @Email(message = "${validatedValue} is not an email")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;
}
