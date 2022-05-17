package com.s0qva.todobackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserIdDto {
    @NotNull(message = "id must not be null")
    @Positive(message = "id must be a positive number")
    private Long id;
}
