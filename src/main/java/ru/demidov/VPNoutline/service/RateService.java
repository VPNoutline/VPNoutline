package ru.demidov.VPNoutline.service;

import ru.demidov.VPNoutline.entity.Rate;

public interface RateService {
    Rate findById (Long id);

    Rate createStartRate();

    Rate createBasicRate();

    Rate createSpecialOfferRate();
}
