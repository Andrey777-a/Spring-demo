package org.example.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
//@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
       return userService.findById(id)
                .map(
                        user -> {
                            model.addAttribute("user", user);
                            return "user/users";
                        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute UserCreateEditDto user){
        return "redirect:/users "+ userService.create(user).getId();
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute UserCreateEditDto user){
        return userService.update(id, user)
                .map(it -> "redirect:users/{id}")
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
