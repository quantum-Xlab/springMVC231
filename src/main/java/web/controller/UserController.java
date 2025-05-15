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
    public String addUsers(@RequestParam(value = "user_name", required = false) String userName,
                           @RequestParam(value = "mail", required = false) String userMail,
                           @RequestParam(value = "del", required = false) Long id,
                           @RequestParam(value = "updateId", required = false) Long updateId,
                           ModelMap model) {
        List<User> users;
        if (updateId != null && ((userName != null && !userName.isBlank()) || userMail != null)) {
            userService.update(updateId, userName, userMail);
        } else if (userName != null && !userName.isBlank() && userMail != null) {
            userService.add(new User(userName, userMail));
        } else if (id != null) {
            userService.del(id);
        }
        users = userService.listUsers();
        model.addAttribute("users", users);
        return "index";
    }

}
