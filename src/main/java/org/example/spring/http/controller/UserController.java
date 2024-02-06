package org.example.spring.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.model.dto.UserFilter;
import org.example.spring.model.entity.Role;
import org.example.spring.service.CompanyService;
import org.example.spring.service.UserService;
import org.example.spring.validation.group.CreateAction;
import org.example.spring.validation.group.UpdateAction;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.example.spring.http.util.Utils.USERS_VIEW_PAGE;
import static org.example.spring.http.util.Utils.USER_VIEW_PAGE;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
@SessionAttributes("test")
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter userFilter){
//        Cookie cookie = new Cookie("test", "hello");
//        cookie.setMaxAge(10000);
//        response.addCookie(cookie);
        model.addAttribute("users", userService.findAll(userFilter));
        model.addAttribute("userFilter", userFilter);
//        return "redirect:/users";
        return USERS_VIEW_PAGE;
    }

    /*@GetMapping
    public String findUsers(Model model, @ModelAttribute("userFilter") UserFilter userFilter){
        model.addAttribute("users", userService.findAll(userFilter));
        return USERS_VIEW_PAGE;
    }*/

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String findById(@PathVariable("id") Long id, Model model){
       return userService.findById(id)
                .map(
                        user -> {
                            model.addAttribute("user", user);
                            model.addAttribute("roles", Role.values());
                            model.addAttribute("companies", companyService.findAll());
                            return USER_VIEW_PAGE;
                        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute @Validated({Default.class, CreateAction.class}) UserCreateEditDto user, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/users/registration";
        }
        userService.create(user);
        return "redirect:/login";
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute @Validated({Default.class, UpdateAction.class}) UserCreateEditDto user){
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        if(!userService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/users";
    }

}
