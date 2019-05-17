package ua.iasa.happyfridge.dto;

public class CreateIngredientRequest {

    private String name;
    private Double amount;
    private String measure;

    public CreateIngredientRequest(){}

    public CreateIngredientRequest(String name, Double amount, String measure) {
        this.name = name;
        this.amount = amount;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
