package com.ps.ecommerce.beans;

import com.ps.ecommerce.entities.OrderItem;
import com.ps.ecommerce.entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Embeddable
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    @ElementCollection
    private List<OrderItem> items;
    private BigDecimal price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void clearCart() {
        items.clear();
        recalculateTotalPrice();
    }

    public void addToCart(Product product) {
        for (OrderItem i : items) {
            if (i.getProduct().getId() == product.getId()) {
                i.setQuantity(i.getQuantity() + 1);
                i.setPrice(i.getPrice().multiply(new BigDecimal(i.getQuantity())));
                recalculateTotalPrice();
                return;
            }
        }
        items.add(new OrderItem(product));
        recalculateTotalPrice();
    }

    public void removeFromCartById(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == productId) {
                items.remove(i);
                recalculateTotalPrice();
                return;
            }
        }
    }

    public void recalculateTotalPrice() {
        this.price = new BigDecimal(0);
        for (OrderItem i : items) {
            price.add(i.getPrice());
        }
    }

}
