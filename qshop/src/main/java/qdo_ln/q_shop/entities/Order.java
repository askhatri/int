package qdo_ln.q_shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import qdo_ln.q_shop.beans.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private double price;

    public Order(User user, Cart cart) {
        this.user = user;
        this.price = cart.getPrice();
        this.items = new ArrayList<>();
        for (OrderItem i: cart.getItems()) {
            i.setOrder(this);
            this.items.add(i);
        }
        cart.clearCart();
    }
}
