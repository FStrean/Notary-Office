package ru.webapp.notaryoffice.services;

import ru.webapp.notaryoffice.dto.DealDto;

import java.util.List;

public interface DealService {
    List<DealDto> findAllDeals();

    void saveDeal(DealDto dealsDto);
}
