package ru.demidov.VPNoutline.service;

import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demidov.VPNoutline.entity.Rate;
import ru.demidov.VPNoutline.entity.Subscription;
import ru.demidov.VPNoutline.entity.User;
import ru.demidov.VPNoutline.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String BASIC_RATE = "basic";
    public static final String SPECIAL_OFFER_RATE = "special offer";
    public final UserRepository userRepository;
    public final SubscriptionServiceImpl subscriptionServiceImpl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy SubscriptionServiceImpl subscriptionServiceImpl) {
        this.userRepository = userRepository;
        this.subscriptionServiceImpl = subscriptionServiceImpl;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    public boolean findUserByEmail(String email) {
        boolean emailPresent;
        try {
            emailPresent = userRepository.findByEmail(email) != null ? true : false;
            System.out.println("Email present (E): " + emailPresent);
        } catch(NonUniqueResultException nre) {
            return true;
        }
        return emailPresent;
    }

    //метод возвращает список пользователей по подписке
    @Transactional(readOnly = true)
    public List<User> findBySubscription(Subscription subscription){
        return userRepository.findBySubscription(subscription);
    }

    //метод возвращает пользователя по id
    @NotNull
    @Override
    @Transactional(readOnly = true)
    public User findById(@NotNull Long id) {
        Optional<User> foundUser = userRepository.findById(id).map(this::buildUserResponse);
        return foundUser.orElse(null);
    }

    @NotNull
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //метод возвращает подписку по email
    @Override
    public Subscription findSubscriptionByEmail(String email) {
        User foundUser = findByEmail(email);
        return foundUser.getSubscription();
    }


    //метод возвращает название тарифа по email
    @Transactional(readOnly = true)
    @Override
    public String findNameRateByEmail(String email) {
        Rate foundrate = findSubscriptionByEmail(email).getRates();
        return foundrate.getNameRate();
    }

    //метод возвращает флаг активации платного тарифа по email
    @Override
    @Transactional(readOnly = true)
    public boolean findEnabledRateByEmail(String email) {
        return findSubscriptionByEmail(email).isEnabledRate();
    }

    @Transactional(readOnly = true)
    public boolean checkingPaidSubscription(@NotNull String email){
        return (!findEnabledRateByEmail(email)
                && checkingSubscriptionFinish(email));
    }

    @Transactional(readOnly = true)
    public boolean checkingSubscriptionFinish(String email){
        LocalDateTime currentTime = LocalDateTime.now();
        Duration durationSubscription = Duration.between(findSubscriptionByEmail(email).getFinished(), currentTime);
        return !durationSubscription.isNegative();
    }

    @NotNull
    @Override
    @Transactional
    public void createStartUser(@NotNull String email, @NotNull String rateRequest) {
        User newStartUser = new User();

        newStartUser.setEmail(email);

        newStartUser.setSubscription(subscriptionServiceImpl.createStartSubscription());

        userRepository.save(newStartUser);
    }

    @NotNull
    @Override
    @Transactional
    public void createBasicUser(@NotNull String email, @NotNull String rateRequest) {
        User newBasicUser = new User();

        newBasicUser.setEmail(email);

        newBasicUser.setSubscription(subscriptionServiceImpl.createBasicSubscription());

        userRepository.save(newBasicUser);
    }

    @NotNull
    @Override
    @Transactional
    public void createSpecialOfferUser(@NotNull String email, @NotNull String rateRequest) {
        User newSpecialOfferUser = new User();

        newSpecialOfferUser.setEmail(email);

        newSpecialOfferUser.setSubscription(subscriptionServiceImpl.createSpecialOfferSubscription());

        userRepository.save(newSpecialOfferUser);
    }

    @Override
    @Transactional
    public void updateUser(@NotNull String email, @NotNull String rateRequest) {
        User updateCurrentUser = findByEmail(email);

        boolean currentCheckingSubscription = checkingPaidSubscription(email);

        if (currentCheckingSubscription && BASIC_RATE.equals(rateRequest)) {

            updateCurrentUser.setSubscription(subscriptionServiceImpl.createBasicSubscription());

        } else if (currentCheckingSubscription && SPECIAL_OFFER_RATE.equals(rateRequest)){

            updateCurrentUser.setSubscription(subscriptionServiceImpl.createSpecialOfferSubscription());

        } else {
            System.out.println("Подписка ещё действительна!");
            System.out.println("Дождитесь, пожалуйста, окончания подписки и подключитесь снова!!!");
        }
    }

    @NotNull
    @Override
    @Transactional
    public void delete(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }

    @NotNull
    private User buildUserResponse(@NotNull User user) {
        return new User()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setSubscription(user.getSubscription());
    }
}
