package com.s0qva.todobackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDto {
    @NotBlank(message = "email must not be blank")
    @Size(max = 128, message = "email must be less than {max}")
    @Email(message = "${validatedValue} is not an email")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(max = 512, message = "password must be less than {max}")
    private String password;
}
