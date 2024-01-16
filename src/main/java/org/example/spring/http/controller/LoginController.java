package org.example.spring.http.controller;

import org.example.spring.model.dto.LoginReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {


    @GetMapping()
    public String loginPage(){
        return "/user/login";
    }


    @PostMapping()
    public String login(Model model, LoginReadDto loginReadDto){
        var dto = model.getAttribute("loginReadDto");
        System.out.println(dto);
        //        return "forward:/WEB-INF/jsp/user/login.html";
//        return "redirect:https://google.com";
        return "redirect:/login";
    }

}
