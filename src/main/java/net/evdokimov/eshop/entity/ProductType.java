package net.evdokimov.eshop.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "productType")
    private List<Product> products;

    public ProductType() {
    }

    public ProductType(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
