package net.codejava.controller;

import java.sql.Date;
import java.util.List;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.dto.UserDto;
import net.codejava.model.Property;
import net.codejava.model.User;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import net.codejava.service.PropertyServiceOld;
import net.codejava.service.UserService;
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
import javax.transaction.Transactional;


@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyRepository repo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private UserDetails userDetails;
    private UserDetailsService userDetailsService;
    private HttpServletRequest request;

    @GetMapping("/propertiesAuth")
    public List<PropertyDto> getPropertiesAuth(@RequestParam Integer userId) {
        UserDto user = userService.getUserByUserId(userId);
        // User tempUser = UserDto.getUser(user);
        String role = user.getRoleDescription();
        if(role.equals("admin")) {
            return propertyService.getProperties();
        }
        else
        {
            return null;
        }
    }


    @GetMapping("/property")
    public PropertyDto getPropertyByPropertyId(@RequestParam Integer propertyId) {
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

    @GetMapping("/propertiesbyuserid")
    public List<PropertyDto> getPropertiesByUserId(@RequestParam Integer userId) {
        List<PropertyDto> propertiesByUserId = propertyService.getPropertiesByUserId(userId);
        return propertiesByUserId;
    }

    @GetMapping("getPropertiesByFilter")
    public List<PropertyDto> getPropertiesByFilter(@RequestParam float price,
                                                   @RequestParam String country,
                                                   @RequestParam Date availableFrom,
                                                   @RequestParam Date availableTo) {
        List<PropertyDto> propertiesByFilter = propertyService.getPropertiesByFilters(price,country,availableFrom,availableTo);
        return propertiesByFilter;
    }

    @DeleteMapping("deleteproperty")
    public boolean deleteProperty(@RequestParam Integer propertyId) {
        return propertyService.deletePropertyByPropertyId(propertyId);
    }


    //Testing Something
    @GetMapping("/getPropertiesByPriceAndLocation")
    public List<PropertyDto> getPropertiesByPriceAndLocation(@RequestParam float price,@RequestParam String country) {
        return propertyService.getPropertyByPriceAndCountry(price,country);
    }

    @PostMapping("/addproperty")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto) {
        return propertyService.addPropertyByUserId(propertyDto);
    }

    @PutMapping("/property/{propertyId}")
    public PropertyDto updateProperty(@PathVariable int propertyId, @RequestBody PropertyDto propertyDto) {
        return propertyService.updatePropertyByPropertyId(propertyDto, propertyId);
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


