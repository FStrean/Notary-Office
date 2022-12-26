package ru.webapp.notaryoffice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;
}