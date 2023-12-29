package qdo_ln.q_shop.beans;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import qdo_ln.q_shop.entities.OrderItem;
import qdo_ln.q_shop.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {

    private List<OrderItem> items;
    private double price;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void clearCart(){
        items.clear();
        recalculateTotalPrice();
    }

    public void addToCart(Product product){
        for (OrderItem i: items){
            if(i.getProduct().getId() == product.getId()){
                i.setQuantity(i.getQuantity() + 1);
                i.setPrice(i.getQuantity()*i.getPrice());
                recalculateTotalPrice();
                return;
            }
        }
        items. add(new OrderItem(product));
        recalculateTotalPrice();
    }

    public void removeFromCartById(int productId){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == productId){
                items.remove(i);
                recalculateTotalPrice();
                return;
            }
        }
    }

    public void recalculateTotalPrice(){
        this.price = 0;
        for (OrderItem i: items) {
            price += i.getPrice();
        }
    }

}
