package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        List<User> users;
        users = userService.listUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping
    public String newUser(@RequestParam(value = "user_name") String userName,
                          @RequestParam(value = "mail", required = false) String userMail) {
        User user = new User(userName, userMail);
        userService.add(user);
        return "redirect:/";

    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam(value = "updateId") Long updateId, ModelMap model) {
        User user = userService.getUser(updateId);
        model.addAttribute("user", user);
        return "/edit";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@RequestParam("updateId") Long updateId,
                             @RequestParam("user_name") String userName,
                             @RequestParam("mail") String userMail) {
        userService.update(updateId, userName, userMail);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "del") Long id) {
        userService.del(id);
        return "redirect:/";
    }

}
