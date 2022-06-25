package ch.bbw.personenverwaltung.controller;

import ch.bbw.personenverwaltung.functions.DateConverter;
import ch.bbw.personenverwaltung.model.Person;
import ch.bbw.personenverwaltung.model.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.text.ParseException;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private PersonRepository personRepository;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("persons", getPersons());
        return "index";
    }

    @PostMapping("/delete/{personId}")
    public String delUser(@PathVariable("personId") int personId) {
        System.out.println(personId);
        personRepository.deleteById((long) personId);
        return "redirect:/";
    }

    private List<Person> getPersons() {
        List<Person> persons = (List<Person>) personRepository.findAll();
        persons.stream().forEach(person -> {
            try {
                person.setOfficialDate(
                        DateConverter.convertDate(person.getBirthdate())
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return persons;
    }
}
