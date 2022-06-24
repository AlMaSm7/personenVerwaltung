package ch.bbw.personenverwaltung.controller;

import ch.bbw.personenverwaltung.model.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("persons", personRepository.findAll());
        System.out.println("Hello World");
        return "index";
    }
}
