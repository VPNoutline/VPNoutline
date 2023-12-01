package ru.demidov.VPNoutline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demidov.VPNoutline.entity.User;
import ru.demidov.VPNoutline.repository.SubscriptionRepository;
import ru.demidov.VPNoutline.repository.UserRepository;

import java.util.List;

@Service
public class RecheckingSubscriptionPeriodServiceImpl implements RecheckingSubscriptionPeriodService {

    private final UserRepository userRepository;
    private final UserServiceImpl userServiceimpl;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionServiceImpl subscriptionServiceImpl;

    @Autowired
    public RecheckingSubscriptionPeriodServiceImpl(UserRepository userRepository, UserServiceImpl userServiceimpl, SubscriptionRepository subscriptionRepository, SubscriptionServiceImpl subscriptionServiceImpl) {
        this.userRepository = userRepository;
        this.userServiceimpl = userServiceimpl;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionServiceImpl = subscriptionServiceImpl;
    }

    @Override
    public void recheckingPeriod() {
        subscriptionServiceImpl.findAllSubscription().forEach(subscription -> {
            if (subscription.isEnabledRate()){
                List<User> checkingUser = userServiceimpl.findBySubscription(subscription);
                for (User user: checkingUser) {

                    System.out.println("Подписка активна " + user.getEmail());
                };
            }
            if (!subscription.isEnabledRate()){
                List<User> checkingUser = userServiceimpl.findBySubscription(subscription);
                for (User user: checkingUser) {

                    System.out.println("Подписка неактивна " + user.getEmail());
                };
            }
        });
    }
}
