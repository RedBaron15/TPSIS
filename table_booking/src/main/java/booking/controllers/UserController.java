package booking.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import booking.models.User;
import booking.services.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TableService tableService;
    private final PromotionService promoService;
    private final CommentService commentService;
    private final BookingService bookingService;


    @GetMapping("/user/comments")
    public  String userComments(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentService.listComment());
        return "user-comments";
    }

    @PostMapping("/user/addComment")
    public String userAddComment(
            @RequestParam("comment") String comment, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        commentService.createComment(comment, user);
        return "redirect:/user/comments";
    }

    @GetMapping("/user/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") Long id)
    {
        commentService.deleteComment(id);
        return "redirect:/user/comments";
    }

    @GetMapping("/user/catalog")
    public String showUserMenu(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("tables", tableService.listTables());

        return "user-catalog";

    }

    @GetMapping("/user/bookings")
    public String showUserOrder(Model model, Principal principal) {

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("bookings", userService.getBookings(user.getId()));
        return "user-bookings";

    }

    @PostMapping("/user/GetPromo/{id}")
    public String showUserOrderWithPromo(Model model, Principal principal,
                                         @RequestParam("points") int points,
                                         @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
                                         @RequestParam("promo") String promo,
                                         @PathVariable("id") Long id_table) {

        User user = userService.getUserByPrincipal(principal);

        String message = userService.getPromo(principal, points, promo, time, id_table);

            model.addAttribute("message", message);
            model.addAttribute("user", user);
            model.addAttribute("bookings", userService.getBookings(user.getId()));

        return "user-get-promo";

    }

//    @GetMapping("/user/createOrder/{price}")
//    public String createOrder(Model model, Principal principal, @PathVariable("price") String priceStr) {
//
//        double price = Double.parseDouble(priceStr);
//
//        userService.createOrder(principal, price);
//        User user = userService.getUserByPrincipal(principal);
//        model.addAttribute("user", user);
//        model.addAttribute("orders", orderService.getByUser(user));
//        model.addAttribute("personalPrice", user.getBucket().getBucketPrice());
//        return "user-order";
//
//    }
//
    @GetMapping("user/cancelBooking/{id}")
    public String cancelOrder(Model model, Principal principal, @PathVariable("id") Long id) {


        bookingService.cancelById(id);

        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("user", user);
        model.addAttribute("bookings", userService.getBookings(user.getId()));
        return "user-bookings";

    }



    @GetMapping("/user/BookTable/{id}")
    public String addProductInBucket(@PathVariable("id") Long id, Principal principal, Model model)  {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("table", tableService.getTableById(id));
        return "user-create-booking";
    }
}
