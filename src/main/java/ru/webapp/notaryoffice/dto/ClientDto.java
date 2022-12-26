package ru.webapp.notaryoffice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private String job;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phoneNumber;
}
