package com.s0qva.todobackend.dto.category;

import com.s0qva.todobackend.dto.user.UserIdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreationDto {
    @NotBlank(message = "title must not be blank")
    private String title;

    @Valid
    @NotNull(message = "user's id must not be null")
    private UserIdDto userId;
}
