package com.s0qva.todobackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPartUpdatingDto {
    @Size(max = 128, message = "username must be less than {max}")
    private String username;

    @Email(message = "${validatedValue} is not an email")
    @Size(max = 128, message = "email must be less than {max}")
    private String email;

    @Size(max = 512, message = "password must be less than {max}")
    private String password;
}
