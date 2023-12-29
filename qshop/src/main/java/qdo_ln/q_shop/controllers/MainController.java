package qdo_ln.q_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qdo_ln.q_shop.beans.Cart;
import qdo_ln.q_shop.entities.Order;
import qdo_ln.q_shop.entities.OrderItem;
import qdo_ln.q_shop.entities.Product;
import qdo_ln.q_shop.entities.User;
import qdo_ln.q_shop.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    private Cart cart;

    @Autowired
    public MainController(ProductService productService, CategoryService categoryService, UserService userService, OrderService orderService, OrderItemService orderItemService, Cart cart) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.cart = cart;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> param){
        Page<Product> page = productService.findAll(param);
        StringBuilder requestDefinition = productService.getRequestDefinition();
        model.addAttribute("page", page);
        model.addAttribute("requestDefinition", requestDefinition);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.getUserByPhone(principal.getName());
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile";
    }

    @GetMapping("/profile/orders/{id}")
    public String showOrderById(Model model, @PathVariable int id){
        Order order = orderService.findById(id);
        List<OrderItem> orderItems = orderItemService.findByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "order";
    }

    @GetMapping("/cart/add/{id}")
    public void addItemToCart(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productService.findById(id);
        cart.addToCart(product);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/orders/checkout")
    public String checkoutOrder(Principal principal){
        User user = userService.getUserByPhone(principal.getName());
        Order order = new Order(user, cart);
        orderService.save(order);
        return "redirect:/";
    }
}
