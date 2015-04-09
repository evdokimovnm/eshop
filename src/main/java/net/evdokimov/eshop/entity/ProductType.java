package net.evdokimov.eshop.entity;

/**
 * Created by 1 on 09.04.2015.
 */
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
}
