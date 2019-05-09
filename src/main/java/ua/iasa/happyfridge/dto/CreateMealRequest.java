package ua.iasa.happyfridge.dto;

import ua.iasa.happyfridge.entities.Ingredient;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateMealRequest {

    @NotNull
    @NotEmpty
    private String name;

    private Integer amount;

    private Long expirationDate;

    private Integer discount;

    private Double price;

    private List<String> ingredients;

    public CreateMealRequest(){}

    public CreateMealRequest(String name, Integer amount, Long expirationDate, Integer discount, Double price, List<String> ingredients) {
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.discount = discount;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
