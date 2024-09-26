package lk.ijse.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
@Id
    private String id;
    private String name;
    private String address;
    private String tel;

    public Customer() {
    }

    public Customer(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
