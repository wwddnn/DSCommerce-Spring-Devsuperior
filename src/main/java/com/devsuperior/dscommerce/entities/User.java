package com.devsuperior.dscommerce.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// tips1: starting with the class relationship for many. in this case for many to class Order.
// tips2: the class that is isolated on the borders.
// this is ORM object-relational mapping, where we will transform entities, to database.
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //this field 'email' has 'unique=true' because not can repeat.
    @Column(unique = true)
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;

    //class User(client) is one to many in relation class Order.
    //name of list is 'orders' because is the name in the project.
    @OneToMany(mappedBy = "client") //name 'client' is the name that is Class Order in 'private User client'
    private List<Order> orders = new ArrayList<>();

    //constructor
    public User()  {
    }

    public User(Long id, String name, String email, String phone, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.password = password;
    }

    //get and set
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //create only 'getOrders()' for the List. don't create method set.
    // is this a collection.
    // attention !! you cant 'set' a collection. don't go change a List, only add or remove items this.
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}