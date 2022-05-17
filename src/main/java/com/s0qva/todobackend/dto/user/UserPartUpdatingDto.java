package com.s0qva.todobackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPartUpdatingDto {
    private String username;

    @Email(message = "${validatedValue} is not an email")
    private String email;

    private String password;
}
