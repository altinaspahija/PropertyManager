package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import net.codejava.User;
import net.codejava.PropertyService;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class PropertyController {

    @Autowired
    private PropertyService service;
    @Autowired
    private PropertyRepository repo;
    @Autowired
    private UserRepository userRepository;

    private UserDetails userDetails;
    private UserDetailsService userDetailsService;


    @RequestMapping("/")
    public String viewHomePage(Model model) {
/*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User)auth.getPrincipal();
        int userId = user.getUser_id();

        /*
        String username = userDetails.getUsername();
        User user_repo = userRepository.getUserByUsername(username);
        Integer user_id = user_repo.getUser_id();

         */

        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=myUserDetails.getUsername(); //Fetch the custom property in User class
        User user_repo = userRepository.getUserByUsername(username);
        Integer user_id = user_repo.getUser_id();
        List<Property> listProperties = service.listAllByUserId(user_id);

        model.addAttribute("listProperties", listProperties);

        return "index";
    }


    @RequestMapping("/new")
    public String showNewPropertyForm(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProperty(@ModelAttribute("property") Property property) {
        service.save(property);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPropertyForm(@PathVariable(name = "id") Integer property_id) {
        ModelAndView mav = new ModelAndView("edit_product");

        Property property = service.get(property_id);
        mav.addObject("property", property);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProperty(@PathVariable(name = "id") Integer property_id) {
        service.delete(property_id);

        return "redirect:/";
    }
    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "index";}

}


