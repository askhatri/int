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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final Cart cart;
    @Autowired
    private AuthenticationManager authenticationManager; // Spring Security's authentication manager

    @Autowired
    public MainController(ProductService productService, CategoryService categoryService, UserService userService, OrderService orderService, OrderItemService orderItemService, Cart cart) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.cart = cart;
    }

    @GetMapping("/afsdasf")
    public String afasfd(Model model, @RequestParam Map<String, String> param) {
        Page<Product> page = productService.findAll(param);
        StringBuilder requestDefinition = productService.getRequestDefinition();
        model.addAttribute("page", page);
        model.addAttribute("requestDefinition", requestDefinition);
        return "afsdfa";
    }

    @GetMapping("/")
    public String showLoginForm(Model model) {
        logger.info("On showLoginForm Page");
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "home";
        } catch (AuthenticationException e) {
            bindingResult.rejectValue("password", "error.incorrectCredentials", "Invalid phone or password");
            return "index";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.getUserByPhone(user.getPhone()) != null) {
            bindingResult.rejectValue("phone", "error.userExists", "User with this phone already exists");
            return "register";
        }
        userService.createUser(user);
        return "register_success";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.info("On register Page");
        User user = new User();
        user.setPhone("9988776655");
        user.setPassword("9988776655");
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        logger.info("On home Page");
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "index";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.getUserByPhone(principal.getName());
        List<OrderDetails> orders = orderService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        logger.info("On profile GetMapping Page");
        return "profile";
    }

    @GetMapping("/profile/orders/{id}")
    public String showOrderDetailsById(Model model, @PathVariable int id) {
        OrderDetails order = orderService.findById(id);
        List<OrderItem> orderItems = orderItemService.findByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        logger.info("On order GetMapping Page");
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
        User user = userService.getUserByPhone(principal.getName());
        OrderDetails order = new OrderDetails(user, cart);
        orderService.save(order);
        return "redirect:/";
    }
}
