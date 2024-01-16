package org.example.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.spring.model.dto.UserTestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.example.spring.http.util.Utils.GREETING_VIEW_BYE_PATH;
import static org.example.spring.http.util.Utils.GREETING_VIEW_PAGE_PATH;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {


    @GetMapping("/hello")
    public String hello(ModelAndView modelAndView, Model model,
                        @ModelAttribute("userTestDto") UserTestDto userTestDto) {
//        request.getSession().setAttribute(); sessionScope
//        request.setAttribute(); requestScope

//        request.getSession().getAttribute("user")
//        modelAndView.addObject("user", userTestDto);

        model.addAttribute("user", userTestDto);
//        model.addAttribute("user", new UserTestDto(1, "Ivan"));

        return GREETING_VIEW_PAGE_PATH;

    }

    @GetMapping("/hello2/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                               @RequestParam Integer age,
                               @RequestHeader String accept,
                               @CookieValue(value = "JSESSIONID", required = false) String jsessionid,
                               @PathVariable Integer id) {
        modelAndView.setViewName(GREETING_VIEW_PAGE_PATH);
        return modelAndView;
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserTestDto user, Model model) {
//        modelAndView.setViewName("greeting/bye");
//        model.addAttribute("user", user);
//        httpSession.setAttribute("user", new UserTestDto(1, "Dima"));
        return GREETING_VIEW_BYE_PATH;
    }


/*    @GetMapping("/bye")
    public ModelAndView bye2(ModelAndView modelAndView, Model model,
                            @SessionAttribute("user") UserReadDto userReadDto){
//        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }*/


}
