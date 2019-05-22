package ua.iasa.happyfridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.iasa.happyfridge.entities.Ingredient;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealRequest {

    @NotNull
    @NotEmpty
    private String name;

    private Integer amount;

    private Long expirationDate;

    private Integer discount;

    private Double price;

    private List<String> ingredients;

    private Long imageId;

    public CreateMealRequest(String name, Integer amount, Long expirationDate, Integer discount, Double price, List<String> ingredients) {
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.discount = discount;
        this.price = price;
        this.ingredients = ingredients;
    }

}
