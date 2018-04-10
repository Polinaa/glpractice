package com.flowergarden.flowers;


import com.flowergarden.properties.FreshnessInteger;

public class Tulip extends GeneralFlower {

    public Tulip(int lenght, float price, FreshnessInteger fresh){
        this.lenght = lenght;
        this.price = price;
        this.freshness = fresh;
    }

    public Tulip(int id, int lenght, float price, FreshnessInteger fresh){
        this(lenght, price, fresh);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tulip{" +
                "id=" + id +
                ", freshness=" + freshness +
                ", price=" + price +
                ", lenght=" + lenght +
                '}';
    }
}
