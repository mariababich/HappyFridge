package ua.iasa.happyfridge.dto;

import ua.iasa.happyfridge.entities.Meal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private List<Long> idMealList;

    public CreateOrderRequest(){}

    public CreateOrderRequest(@NotNull @NotEmpty String destinationPoint, String payment, @NotNull @NotEmpty String name, String phone, @NotNull @NotEmpty Long userId, List<Long> idMealList) {
        this.destinationPoint = destinationPoint;
        this.payment = payment;
        this.name = name;
        this.phone = phone;
        this.userId = userId;
        this.idMealList = idMealList;
    }

    public List<Long> getIdMealList() {
        return idMealList;
    }

    public void setIdMealList(List<Long> idMealList) {
        this.idMealList = idMealList;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
