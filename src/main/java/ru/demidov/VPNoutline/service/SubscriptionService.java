package ru.demidov.VPNoutline.service;

import ru.demidov.VPNoutline.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription findById(Long id);

    List<Subscription> findAllSubscription();

    Subscription createStartSubscription();

    Subscription createBasicSubscription();

    Subscription createSpecialOfferSubscription();
}
