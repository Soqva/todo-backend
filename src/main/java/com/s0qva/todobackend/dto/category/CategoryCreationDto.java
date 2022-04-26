package com.s0qva.todobackend.dto.category;

import com.s0qva.todobackend.dto.user.UserIdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreationDto {
    private String title;
    private UserIdDto userId;
}
