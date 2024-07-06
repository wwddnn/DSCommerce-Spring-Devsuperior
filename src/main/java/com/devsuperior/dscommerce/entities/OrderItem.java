package com.devsuperior.dscommerce.entities;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="tb_order_Item")
public class OrderItem {

    //the helper class has to instantiate
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    //constructor
    public OrderItem() {
    }

    //id will be reference for 'order' and 'product', and not orderItemPK.
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    //call 'id' and 'getOrder'.
    public Order getOrder() {
        return id.getOrder();
    }

    //call 'id' and 'setOrder'.
    public void setOrder(Order order) {
        id.setOrder(order);
    }

    //call 'id' and 'getProduct'.
    public Product getProduct() {
        return id.getProduct();
    }

    //call 'id' and 'setProduct'.
    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
