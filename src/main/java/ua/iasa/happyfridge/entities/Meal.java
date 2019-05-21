package ua.iasa.happyfridge.entities;

import javax.persistence.*;
import java.util.List;


@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer amount;
    private Long expirationDate;
    private Integer discount;
    private Double price;

    @ManyToMany
    private List<Ingredient> ingredients;

    public Meal() { }

    public Meal(String name, Integer amount, Long expirationDate, Integer discount, Double price, List<Ingredient> ingredients) {
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.discount = discount;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    //    @ElementCollection
//    private List<Ingredient> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
