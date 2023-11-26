package ru.demidov.VPNoutline.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demidov.VPNoutline.entity.Subscription;
import ru.demidov.VPNoutline.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    public final SubscriptionRepository subscriptionRepository;
    public final UserServiceImpl userServiceImpl;
    private final RateServiceImpl rateServiceImpl;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserServiceImpl userServiceImpl, RateServiceImpl rateServiceImpl) {
        this.subscriptionRepository = subscriptionRepository;
        this.userServiceImpl = userServiceImpl;
        this.rateServiceImpl = rateServiceImpl;
    }

    @Transactional(readOnly = true)
    public Subscription buildSubscriptionResponse(@NotNull Subscription subscription) {
        return new Subscription()
                .setId(subscription.getId())
                .setEnabledRate(subscription.isEnabledRate())
                .setCreated(subscription.getCreated())
                .setFinished(subscription.getFinished())
                .setKeyAccess(subscription.getKeyAccess())
                .setRates(subscription.getRates())
                .setUsers(userServiceImpl.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Subscription findById(Long id) {
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(id).map(this::buildSubscriptionResponse);
        return foundSubscription.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> findAllSubscription(){
        return subscriptionRepository.findAll()
                .stream()
                .map(this::buildSubscriptionResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Subscription createStartSubscription() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime finishTime = startTime.plusWeeks(1);
        return  new Subscription()
                .setEnabledRate(false)
                .setCreated(startTime)
                .setFinished(finishTime)
                .setKeyAccess("1111111")
                .setRates(rateServiceImpl.createStartRate());
    }

    @Override
    @Transactional
    public Subscription createBasicSubscription() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime finishTime = startTime.plusMonths(1);
        return  new Subscription()
                .setEnabledRate(true)
                .setCreated(startTime)
                .setFinished(finishTime)
                .setKeyAccess("2222222")
                .setRates(rateServiceImpl.createBasicRate());
    }

    @Override
    @Transactional
    public Subscription createSpecialOfferSubscription() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime finishTime = startTime.plusYears(1);
        return  new Subscription()
                .setEnabledRate(true)
                .setCreated(startTime)
                .setFinished(finishTime)
                .setKeyAccess("3333333")
                .setRates(rateServiceImpl.createSpecialOfferRate());
    }
}
