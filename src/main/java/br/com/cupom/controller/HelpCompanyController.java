package br.com.cupom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpCompanyController {

    @GetMapping("/ajuda")
    public String getHelp(){
        return "ajuda";
    }


}
