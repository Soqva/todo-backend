package com.s0qva.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
