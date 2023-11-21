package web.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/allUsers";
    }

    @GetMapping("users/new")
    public String createUser(@ModelAttribute("user") User user){
//        model.addAttribute("user", new User());
        return "users/new";
    }

        @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
//        return "";
//    }
//    @PostMapping
//    public String createUser(@RequestParam(value = "name", required = false) String name,
//                             @RequestParam(value = "surname", required = false) String surname,
//                             @RequestParam(value = "email", required = false) String email,
//                             @RequestParam(value = "age", required = false) int age,
//                             Model model) {
//        User user = new User();
//        user.setName(name);
//        user.setSurName(surname);
//        user.setEmail(email);
//        user.setAge(age);
//
//        model.addAttribute("user", user);
//        userService.createUser(user);


        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userService.readUser(id));

        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}