package com.ps.ecommerce.controllers;

import com.ps.ecommerce.beans.Cart;
import com.ps.ecommerce.entities.OrderDetails;
import com.ps.ecommerce.entities.OrderItem;
import com.ps.ecommerce.entities.Product;
import com.ps.ecommerce.entities.User;
import com.ps.ecommerce.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final Cart cart;

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
    public String index(Model model, @RequestParam Map<String, String> param) {
        Page<Product> page = productService.findAll(param);
        StringBuilder requestDefinition = productService.getRequestDefinition();
        model.addAttribute("page", page);
        model.addAttribute("requestDefinition", requestDefinition);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(principal.getName());
        List<OrderDetails> orders = orderService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile";
    }

    @GetMapping("/profile/orders/{id}")
    public String showOrderDetailsById(Model model, @PathVariable int id) {
        OrderDetails order = orderService.findById(id);
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
    public String checkoutOrderDetails(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        OrderDetails order = new OrderDetails(user, cart);
        orderService.save(order);
        return "redirect:/";
    }
}
