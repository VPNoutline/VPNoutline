package ru.demidov.VPNoutline.service;

import jakarta.validation.constraints.NotNull;
import ru.demidov.VPNoutline.entity.Subscription;
import ru.demidov.VPNoutline.entity.User;

import java.util.List;

public interface UserService {

    @NotNull
    List<User> findAll();

    @NotNull
    User findById(@NotNull Long userId);

    @NotNull
    User findByEmail(@NotNull String email);

    String findNameRateByEmail(String email);

    Subscription findSubscriptionByEmail(String email);

    boolean findEnabledRateByEmail(String email);

    @NotNull
    void createStartUser(@NotNull String email, @NotNull String rateRequest);

    @NotNull
    void createBasicUser(@NotNull String email, @NotNull String rateRequest);

    @NotNull
    void createSpecialOfferUser(@NotNull String email, @NotNull String rateRequest);

    void updateUser(@NotNull String email, @NotNull String rateRequest);

    void delete(@NotNull Long userId);
}
