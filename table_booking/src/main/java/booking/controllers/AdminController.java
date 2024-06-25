package booking.controllers;


import booking.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import booking.models.User;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final PromotionService promoService;
    private final CommentService commentService;
    private final TableService tableService;
    private final BookingService bookingService;


    @GetMapping("/admin/catalog")
    public String showUserProfile(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("tables", tableService.listTables());

        return "admin-catalog";

    }


    @PostMapping("/admin/addTable")
    public String addProduct(
                               @RequestParam("image") MultipartFile img,
                               @RequestParam("description") String description,
                               @RequestParam("capacity") int capacity,
                               @RequestParam("price") float price,
                               @RequestParam("type") String type) throws IOException {
        tableService.add(img, description, capacity, type, price);
        return "redirect:/admin/catalog";
    }

    @GetMapping("/admin/deleteTable/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model, Principal principal)
    {

        tableService.deleteTableById(id);

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("tables", tableService.listTables());

        return "admin-catalog";
    }


    @GetMapping("/admin/setTableActive/{id}")
    public  String userSetTableActive(@PathVariable("id") Long id, Model model, Principal principal){

        tableService.setActive(id);
        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("tables", tableService.listTables());

        return "admin-catalog";    }

    @GetMapping("/admin/promos")
    public String promos(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("admin", user);
        model.addAttribute("promos", promoService.listPromos());

        return "admin-promos";

    }


    @PostMapping("/admin/addPromo")
    public String addPromo(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("promocode") String promocode,
            @RequestParam("min_points") int min,
            @RequestParam("max_points") int max,
            @RequestParam("discount") int discount) {

        promoService.createPromotion(name, description, promocode, min, max, discount);
        return "redirect:/admin/promos";
    }

    @GetMapping("/admin/deletePromo/{id}")
    public String deletePromo(@PathVariable("id") Long id)
    {

        promoService.deletePromotion(id);
        return "redirect:/admin/promos";
    }
    @GetMapping("/admin/deactivatePromo/{id}")
    public String deactivatePromo(@PathVariable("id") Long id)
    {

        promoService.deactivatePromotion(id);
        return "redirect:/admin/promos";
    }

    @GetMapping("/admin/activatePromo/{id}")
    public String activatePromo(@PathVariable("id") Long id)
    {

        promoService.activatePromotion(id);
        return "redirect:/admin/promos";
    }


    @GetMapping("/admin/comments")
    public  String adminComments(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("admin", user);
        model.addAttribute("comments", commentService.listComment());
        return "admin-comments";
    }

    @PostMapping("/admin/addComment")
    public String addComment(
            @RequestParam("comment") String comment, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        commentService.createComment(comment, user);
        return "redirect:/admin/comments";
    }

    @GetMapping("/admin/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") Long id)
    {

        commentService.deleteComment(id);
        return "redirect:/admin/comments";
    }

    @GetMapping("/admin/accounts")
    public  String users(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("admin", user);
        model.addAttribute("users", userService.list());
        return "admin-accounts";
    }

    @GetMapping("/admin/bookings")
    public  String bookings(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("admin", user);
        model.addAttribute("bookings", bookingService.list());
        return "admin-bookings";
    }

    @GetMapping("/admin/accounts/ban/{id}")
    public  String userBan(@PathVariable("id") Long id, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);

        userService.banUser(id);
        model.addAttribute("admin", user);
        model.addAttribute("users", userService.list());
        return "admin-accounts";
    }


    @GetMapping("/admin/accounts/setRole/{id}")
    public  String userSetRole(@PathVariable("id") Long id, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        userService.setRole(id);
        model.addAttribute("admin", user);
        model.addAttribute("users", userService.list());
        return "admin-accounts";
    }

    @PostMapping("/admin/createUser")
    public String AdminCreateUser(@RequestParam("image") MultipartFile img,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("login") String login,
                                  @RequestParam("password") String password) throws IOException {
        if (userService.createUser(img, lastName, firstName, phone, login, password)) {
            return "redirect:/admin/accounts";
        }
        return "redirect:/profile";
    }


    @GetMapping("admin/confirmedBooking/{id}")
    public String confirmed(Model model, Principal principal, @PathVariable("id") Long id) {


        bookingService.confirmedById(id);


        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("admin", user);
        model.addAttribute("bookings", bookingService.list());
        return "admin-bookings";

    }

}
