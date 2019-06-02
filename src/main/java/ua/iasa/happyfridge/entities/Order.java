package ua.iasa.happyfridge.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @JsonManagedReference
    private User user;

    private String destinationPoint;

    private String payment;

    private String name;

    private String phone;

//    private Double price;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Meal> mealList;

    public Order(){}

    public Order(User user, String destinationPoint, String payment, String name, String phone, List<Meal> mealList) {
        this.user = user;
        this.destinationPoint = destinationPoint;
        this.payment = payment;
        this.name = name;
        this.phone = phone;
        this.mealList = mealList;
    }

//    public Order(User user, String destinationPoint, String payment, String name, String phone, Double price, List<Meal> mealList) {
//        this.user = user;
//        this.destinationPoint = destinationPoint;
//        this.payment = payment;
//        this.name = name;
//        this.phone = phone;
//        this.price = price;
//        this.mealList = mealList;
//    }
}
