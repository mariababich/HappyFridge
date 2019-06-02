package ua.iasa.happyfridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.iasa.happyfridge.entities.Meal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull
    @NotEmpty
    private String destinationPoint;

    private String payment;

    @NotNull
    @NotEmpty
    private String name;

    private String phone;

    @NotNull
    @NotEmpty
    private Long userId;

//    private Double price;

    private List<Long> idMealList;

//    public CreateOrderRequest(@NotNull @NotEmpty String destinationPoint, String payment, @NotNull @NotEmpty String name, String phone, @NotNull @NotEmpty Long userId, List<Long> idMealList) {
//        this.destinationPoint = destinationPoint;
//        this.payment = payment;
//        this.name = name;
//        this.phone = phone;
//        this.userId = userId;
//        this.idMealList = idMealList;
//    }

}
