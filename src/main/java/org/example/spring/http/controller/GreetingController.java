package org.example.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring.model.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes("user")
public class GreetingController {


    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request){
//        request.getSession().setAttribute(); sessionScope
//        request.setAttribute(); requestScope
//        request.getSession().getAttribute("user")

        modelAndView.setViewName("greeting/hello");
//        modelAndView.addObject("user", new UserReadDto(1, "Ivan"));


        return modelAndView;
    }
    @GetMapping("/hello2/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue(value = "JSESSIONID",required = false) String jsessionid,
                              @PathVariable Integer id){
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView, @SessionAttribute("user") UserReadDto userReadDto){
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }


}
