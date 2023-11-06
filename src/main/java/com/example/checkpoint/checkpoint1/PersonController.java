package com.example.checkpoint.checkpoint1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/checkpoint")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewPerson(@RequestParam String taxcode, @RequestParam String fullname, @RequestParam String birthday) {
        Person p = new Person();
        p.setTaxcode(taxcode);
        p.setFullname(fullname);
        p.setBirthday(birthday);
        personRepository.save(p);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deletePerson(@RequestParam Integer id) {
        personRepository.deleteById(id);
        return "Removed";
    }

    @PutMapping(path = "/put")
    public @ResponseBody String modifyPerson (@RequestParam Integer id, @RequestParam String taxcode, @RequestParam String fullname, @RequestParam String birthday) throws Exception {
        Person personToModify = personRepository.findById(id).orElseThrow(() -> new Exception("Person not exist with id: " + id));
        personToModify.setTaxcode(taxcode);
        personToModify.setFullname(fullname);
        personToModify.setBirthday(birthday);

        personRepository.save(personToModify);
        return "Modified";
    }
}
