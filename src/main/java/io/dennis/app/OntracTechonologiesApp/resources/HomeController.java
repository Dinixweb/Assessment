/*Author: Dennis Shaba */
package io.dennis.app.OntracTechonologiesApp.resources;

import io.dennis.app.OntracTechonologiesApp.model.User;
import io.dennis.app.OntracTechonologiesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    @Autowired //Registering Service class
    UserService userService;


    @GetMapping("/")
    public String homePage(Model model, User user) {
        model.addAttribute("User", user);
        return "index";
    }

    @PostMapping("/newAccount")
    public String processData(Model model, @ModelAttribute("User") @Valid User user, BindingResult bindingResult) throws UsernameNotFoundException {
        if (bindingResult.hasErrors()) { //checking for validation errors
            model.addAttribute("User", user);
            return "index";
        }
        try {
            userService.addUser(user); //adding data
        } catch (UsernameNotFoundException e) {
            //check if phone number exist
            if(userService.phoneExist(user.getPhone())) {
                bindingResult.rejectValue("phone", null, "user with phone number: " + user.getPhone() + " already exist");
                model.addAttribute("User", user);
                return "index";
            }
            //check if email exist
            if(userService.emailExist(user.getEmail())){
                bindingResult.rejectValue("email", null, "user with with address: " + user.getEmail() + " already exist");
                model.addAttribute("User", user);
                return "index";
            }

        }
        //redirect to same page
        return "redirect:/?successful";
    }
}
