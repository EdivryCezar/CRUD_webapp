package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String printAllUsers(ModelMap model) {
        List<User> list = service.listUsers();
        model.addAttribute("listUsers", list);
        return "index";
    }

    @GetMapping("/create_user")
    public String createUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam("id") long id) {
        service.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit_user")
    public String editUser (@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", service.getUser(id));
        return "edit_user";
    }
    @PostMapping("/{id}")
    public String update (@ModelAttribute("user") User user,
                          @RequestParam("id") long id){
        service.updateUser(id, user);
        return "redirect:/";
    }
}