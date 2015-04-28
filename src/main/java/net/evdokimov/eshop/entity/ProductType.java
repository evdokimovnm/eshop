package net.evdokimov.eshop.entity;


public class ProductType {
    private int id;
    private String type;

    public ProductType(String type) {
        this.type = type;
    }

    public ProductType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
