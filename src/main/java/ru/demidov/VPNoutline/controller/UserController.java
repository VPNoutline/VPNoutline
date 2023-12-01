package ru.demidov.VPNoutline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.demidov.VPNoutline.entity.User;
import ru.demidov.VPNoutline.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(UserController.REST_URL)
public class UserController {

    public static final String REST_URL = "/api/users";
    public static final String START_RATE = "start";
    public static final String BASIC_RATE = "basic";
    public static final String SPECIAL_OFFER_RATE = "special offer";
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("/createUser")
    private String addUser(@RequestParam String email, @RequestParam String rateRequest) {
        boolean currentUser = userServiceImpl.findUserByEmail(email);

        if(!currentUser && START_RATE.equals(rateRequest)) {

            userServiceImpl.createStartUser(email, rateRequest);
            System.out.println("Подписка оформлена");
            System.out.println("Ваша подписка " + userServiceImpl.findNameRateByEmail(email));

        }else if (!currentUser && BASIC_RATE.equals(rateRequest)){

            userServiceImpl.createBasicUser(email, rateRequest);
            System.out.println("Подписка оформлена");
            System.out.println("Ваша подписка " + userServiceImpl.findNameRateByEmail(email));

        } else if (!currentUser && SPECIAL_OFFER_RATE.equals(rateRequest)){

            userServiceImpl.createSpecialOfferUser(email, rateRequest);
            System.out.println("Подписка оформлена");
            System.out.println("Ваша подписка " + userServiceImpl.findNameRateByEmail(email));

        } else {

            System.out.println("CANT CREATE USER!");
            updateUser(email,rateRequest);

        }

        return "redirect:" + REST_URL;
    }

    @PostMapping("/updateUser")
    private String updateUser(@RequestParam String email, @RequestParam String rateRequest) {
        if (START_RATE.equals(rateRequest)){

            System.out.println("Пробную подписку можно оформить только один раз");

        } else {

            userServiceImpl.updateUser(email, rateRequest);
            System.out.println("Ваша подписка " + userServiceImpl.findNameRateByEmail(email));

        }

        return "redirect:" + REST_URL;
    }
}
