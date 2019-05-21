package ua.iasa.happyfridge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.repositories.MealRepository;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final MealRepository mealRepository;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static final long DAY_IN_MILLS = 86400000;

    @PostConstruct
    public void scheduleDiscount() {
        executorService.schedule(this::checkAndSetDiscount, 1, TimeUnit.DAYS);
    }

    private void checkAndSetDiscount() {

        Iterable<Meal> meals = mealRepository.findAll();

        meals.forEach(meal -> {
            long timeToExpire = meal.getExpirationDate() - System.currentTimeMillis();
            if (timeToExpire <= DAY_IN_MILLS) {
                meal.setDiscount(30);
                mealRepository.save(meal);
            }
            if (timeToExpire <= 3 * DAY_IN_MILLS) {
                meal.setDiscount(20);
                mealRepository.save(meal);
            }
            if (timeToExpire <= 5 * DAY_IN_MILLS) {
                meal.setDiscount(10);
                mealRepository.save(meal);
            }
        });
    }
}
