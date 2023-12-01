package ru.demidov.VPNoutline.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demidov.VPNoutline.entity.Rate;
import ru.demidov.VPNoutline.repository.RateRepository;

import java.util.Optional;

@Service
public class RateServiceImpl implements RateService{

    private final RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Rate findById(Long id) {
        Optional<Rate> foundRate = rateRepository.findById(id).map(this::buildRateResponse);
        return foundRate.orElse(null);
    }

    @Override
    public Rate createStartRate() {
        Optional<Rate> startRate = rateRepository.findById(1L);
        return startRate.orElse(null);
    }

    @Override
    public Rate createBasicRate() {
        Optional<Rate> basicRate = rateRepository.findById(2L);
        return basicRate.orElse(null);
    }

    @Override
    public Rate createSpecialOfferRate() {
        Optional<Rate> specialOfferRate = rateRepository.findById(3L);
        return specialOfferRate.orElse(null);
    }

    @NotNull
    private Rate buildRateResponse(@NotNull Rate rate) {
        return new Rate()
                .setId(rate.getId())
                .setNameRate(rate.getNameRate())
                .setDescription(rate.getDescription())
                .setPrice(rate.getPrice());
    }
}
