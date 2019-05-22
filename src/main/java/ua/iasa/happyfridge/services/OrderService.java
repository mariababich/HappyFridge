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

import java.util.List;

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
        mailService.sendEmail(MAPPER.writeValueAsString(newOrder), subject, adminProperties.getEmail());
        mailService.sendEmail(MAPPER.writeValueAsString(newOrder), subject, user.getEmail());
        return newOrder;
    }
}
