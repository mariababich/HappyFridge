package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.dto.CreateOrderRequest;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.MealRepository;
import ua.iasa.happyfridge.repositories.OrderRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealRepository mealRepository;

    @PostMapping("/order/create")
    public ResponseEntity createOrder(@RequestBody CreateOrderRequest request) {
        User user = userRepository.getById(request.getUserId());
        List<Meal> orderMeal = mealRepository.findByIdIn(request.getIdMealList());
        Order order = new Order(user, request.getDestinationPoint(),
                request.getPayment(), request.getName(), request.getPhone(), orderMeal);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders/select/{userId}")
    public List<Order> selectOrder(@PathVariable Long userId){
        User user = userRepository.getById(userId);
        return orderRepository.findAllByUser(user);
    }

}
