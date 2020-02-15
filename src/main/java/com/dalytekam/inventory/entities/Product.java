package com.dalytekam.inventory.entities;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
@Entity
public class Product implements Serializable {
    @Id  @GeneratedValue
    private Long id;
    @NotNull
    @Size(min=3,max=31,message = "Must be comprise between 3 and 30!")
    private String name;
    @DecimalMax(value = "100000",message = "Must not be greater than 100,000.00!")
    @DecimalMin(value = "1",message = "Must be at least 1!")
    private double price;
    @Min(value=1, message=" Must be equal or greater than 1!")
    private int quantity;

    public Product() {
    }

    public Product(String name, double price, int quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
