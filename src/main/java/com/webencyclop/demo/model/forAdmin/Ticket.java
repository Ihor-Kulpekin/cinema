package com.webencyclop.demo.model.forAdmin;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Price")
    private int price;

    @Column(name = "Time")
    private String priceByTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceByTime() {
        return priceByTime;
    }

    public void setPriceByTime(String priceByTime) {
        this.priceByTime = priceByTime;
    }
}
