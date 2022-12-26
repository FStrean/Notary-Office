package ru.webapp.notaryoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealDto {

    private Long id;

    private Long amount;

    private Long commission_fees;

    private String description;

    private String clientPhoneNumber;

    private String clientNameSurname;

    private List<String> serviceDtoList;
}
