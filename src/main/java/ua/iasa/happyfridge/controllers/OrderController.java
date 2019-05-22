package ua.iasa.happyfridge.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.dto.CreateOrderRequest;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.OrderRepository;
import ua.iasa.happyfridge.repositories.UserRepository;
import ua.iasa.happyfridge.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @PostMapping("/order/create")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.makeOrder(request);
    }

    @GetMapping("/orders/select/{userId}")
    public List<Order> selectOrder(@PathVariable Long userId) {
        User user = userRepository.getById(userId);
        return orderRepository.findAllByUser(user);
    }
}
