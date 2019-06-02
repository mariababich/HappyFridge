package ua.iasa.happyfridge.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ua.iasa.happyfridge.dto.CreateOrderRequest;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.properties.AdminProperties;
import ua.iasa.happyfridge.repositories.MealRepository;
import ua.iasa.happyfridge.repositories.OrderRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final AdminProperties adminProperties;
    private final MailService mailService;

    @SneakyThrows
    public Order makeOrder(CreateOrderRequest request) {
        User user = userRepository.getById(request.getUserId());
        List<Meal> orderMeal = mealRepository.findByIdIn(request.getIdMealList());
        Order orderForSave = new Order(user, request.getDestinationPoint(),
                request.getPayment(), request.getName(), request.getPhone(), orderMeal);
        Order newOrder = orderRepository.save(orderForSave);
        String subject = String.format("order № %s", newOrder.getId());
//        String resource = "/tmp/email_owner.html";
//        String resourceUser = "/tmp/email_user.html";
        URI resource = getClass().getClassLoader().getResource("email_user.html").toURI();
        URI resourceUser = getClass().getClassLoader().getResource("email_user.html").toURI();
        String orderString = String.format(readHtml(resource),newOrder.getName(),
                newOrder.getMealList().stream().map(Meal::getName).collect(Collectors.toList()),
                newOrder.getPhone(), newOrder.getDestinationPoint(), newOrder.getPayment() );
        String orderUser = String.format(readHtml(resourceUser),
                newOrder.getMealList().stream().map(Meal::getName).collect(Collectors.toList()),
                newOrder.getPhone(), newOrder.getDestinationPoint(), newOrder.getPayment());

//        String orderString = "Order Items: "+newOrder.getMealList().stream().map(Meal::getName).collect(Collectors.toList())+"\n"+
//                "User name: "+newOrder.getName()+"\nPhone: "+newOrder.getPhone()+"\n"+
//                "Destination: "+newOrder.getDestinationPoint();
        mailService.sendHtml(orderString, subject, adminProperties.getEmail());
        mailService.sendHtml(orderUser, subject, user.getEmail());
        return newOrder;
    }

    @SneakyThrows
    public String readHtml(URI filename){
        return Files.lines(Paths.get(filename), StandardCharsets.UTF_8).collect(Collectors.joining());
    }


}
