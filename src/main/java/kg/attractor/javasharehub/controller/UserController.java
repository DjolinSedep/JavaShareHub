package kg.attractor.javasharehub.controller;

import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String profile(Model model, Principal principal) {
        UserDto user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile/profile";
    }
}
