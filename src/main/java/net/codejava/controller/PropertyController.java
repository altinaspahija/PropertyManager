package net.codejava.controller;

import java.util.Date;
import java.util.List;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.model.Property;
import net.codejava.model.User;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import net.codejava.service.PropertyServiceOld;
import net.codejava.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;


@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyRepository repo;
    @Autowired
    private UserRepository userRepository;

    private UserDetails userDetails;
    private UserDetailsService userDetailsService;
    private HttpServletRequest request;




    @GetMapping("property/{propertyId}")
    public PropertyDto getPropertyByPropertyId(@PathVariable Integer propertyId) {
        PropertyDto property = propertyService.getPropertyByPropertyId(propertyId);
        if (property == null)
        {
            return null;
        }
        else{
            return property;
        }
    }

    @GetMapping("/properties")
    public List<PropertyDto> getProperties() {
        return propertyService.getProperties();
    }

    @GetMapping("/propertiesbyuserid/{userId}")
    public List<PropertyDto> getPropertiesByUserId(@PathVariable Integer userId) {
        List<PropertyDto> propertiesByUserId = propertyService.getPropertiesByUserId(userId);
        return propertiesByUserId;
    }

    @RequestMapping(path = "propertyfilter/{price}/{country}/{availableFrom}/{availableTo}",method = RequestMethod.GET)
    public List<PropertyDto> getPropertiesByFilter(@PathVariable("price") float price,
                                                   @PathVariable("country") String country,
                                                   @PathVariable("availableFrom") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  Date availableFrom,
                                                   @PathVariable("availableTo") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date availableTo) {
        List<PropertyDto> propertiesByFilter = propertyService.getPropertiesByFilters(price,country,availableFrom,availableTo);
        return propertiesByFilter;
    }

    @DeleteMapping("property/{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId) {
        return propertyService.deletePropertyByPropertyId(propertyId);
    }
   /* @RequestMapping("/new")
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

    @RequestMapping("/")
    public String viewHomePage(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user_repo = userRepository.getUserByUsername(username);
        Integer user_id = user_repo.getUserId();
        //List<Property> listProperties = service.listAllByUserId(user_id);

        //model.addAttribute("listProperties", listProperties);

        return "index";
    }*/

}


