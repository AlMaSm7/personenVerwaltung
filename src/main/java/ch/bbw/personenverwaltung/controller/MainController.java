package ch.bbw.personenverwaltung.controller;

import ch.bbw.personenverwaltung.functions.DateConverter;
import ch.bbw.personenverwaltung.model.Person;
import ch.bbw.personenverwaltung.model.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/delete/{id}")
    public String delUser(@PathVariable("id") long personId, RedirectAttributes redirectAttributes) {
        System.out.println(personId);
        try {
            personRepository.deleteById(personId);
            redirectAttributes.addFlashAttribute("success", "Person deleted");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("fail", "Person could not be deleted");
        }

        return "redirect:/";
    }

    private List<Person> getPersons() {
        List<Person> persons = personRepository.findAll();
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

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("person", new Person());
        return "add_person";
    }

    @PostMapping("/addperson")
    public String addPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            person.setBirthdate(DateConverter.dbDate(person.getBirthdate()));
            personRepository.save(person);
            redirectAttributes.addFlashAttribute("success", "Person added");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("fail", "Person could not be added");
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/editperson/{id}")
    public String editPerson(@PathVariable long id, @ModelAttribute Person person, RedirectAttributes attributes) {
        try {
            person.setBirthdate(DateConverter.dbDate(person.getBirthdate()));
            person.setId(id);
            personRepository.save(person);
            attributes.addFlashAttribute("success", "Person" + person.getFirstname()
                    + "was successfully edited");
        } catch (Exception e) {
            attributes.addFlashAttribute("fail", "Person could not be edited");
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/editform/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("person", personRepository.findById(id).get());
        return "edit_person";
    }
}
