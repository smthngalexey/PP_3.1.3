package kulish.webapp.controllers;


import kulish.webapp.models.User;
import kulish.webapp.services.RoleService;
import kulish.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, @AuthenticationPrincipal User currentUser) {
        model.addAttribute("createUser", new User());
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("admin", currentUser);
        return "admin";
    }

    @GetMapping("/admin/user/{id}")
    public String findUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin";
    }

    @PostMapping("/admin/create")
    public String saveUser(@ModelAttribute("createUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user")User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
