package com.example.myapplication;

public class Fedback {
    private String name;
    private String price;
    private int image_id;

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public int getImage_id() {
        return image_id;
    }
    public Fedback(int image_id,String name,String price) {
        this.name = name;
        this.price=price;
        this.image_id=image_id;
    }

}
