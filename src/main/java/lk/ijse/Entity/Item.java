package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    private String id;
    private String name ;
    private String qty;
    private Double unitPrice;


    public Item(String id, String name, String qty, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public Item() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
