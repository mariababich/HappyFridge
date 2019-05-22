package ua.iasa.happyfridge.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer amount;
    private Long expirationDate;
    private Integer discount;
    private Double price;
    private Long imageId;


    @ManyToMany
    private List<Ingredient> ingredients;


    public Meal(String name, Integer amount, Long expirationDate, Integer discount, Double price, List<Ingredient> ingredients) {
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.discount = discount;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Meal(String name, Integer amount, Long expirationDate, Integer discount, Double price, Long imageId, List<Ingredient> ingredients) {
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.discount = discount;
        this.price = price;
        this.imageId = imageId;
        this.ingredients = ingredients;
    }
}
