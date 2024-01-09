package org.example.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.CompanyCreateEditDto;
import org.example.spring.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companys")
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping
    public String findAll(Model model){
        model.addAttribute("company", companyService.findAll());

        return "companys";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model){

        return companyService.findById(id)
                .map(
                        company -> {
                            model.addAttribute("company", company);
                            return "companys/company";
                        }
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(CompanyCreateEditDto companyDto){

        return "redirect:companys " + companyService.create(companyDto).id();
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        if(!companyService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:company";
    }

}
