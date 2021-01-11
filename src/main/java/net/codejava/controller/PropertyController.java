package net.codejava.controller;

import java.sql.Date;
import java.util.List;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private UserService userService;
    private UserDetails userDetails;
    private UserDetailsService userDetailsService;
    private HttpServletRequest request;

    @GetMapping("/propertiesauth")
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
        if (property == null) return null;
        else return property;
    }

    @GetMapping("/properties")
    public List<PropertyDto> getProperties() {
        return propertyService.getProperties();
    }

    @GetMapping("/propertiesbyuserid")
    public List<PropertyDto> getPropertiesByUserId(@RequestParam Integer userId) {
        return propertyService.getPropertiesByUserId(userId);
    }

    @DeleteMapping("property")
    public boolean deleteProperty(@RequestParam Integer propertyId) {
        return propertyService.deletePropertyByPropertyId(propertyId);
    }

    @PostMapping("property")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto) {
        return propertyService.addProperty(propertyDto);
    }

    @PutMapping("property")
    public PropertyDto updateProperty(@RequestBody PropertyDto propertyDto) {
        return propertyService.updatePropertyByPropertyId(propertyDto);
    }

    @GetMapping("filterProperties")
    public List<PropertyDto> getFilterProperties(@RequestParam(required = false) String country,
                                                 @RequestParam(required = false) Float minPrice,
                                                 @RequestParam(required = false) Float maxPrice,
                                                 @RequestParam(required = false) Date start,
                                                 @RequestParam(required = false) Date end){

    if (country!=null&&minPrice!=null&&maxPrice!=null&&start!=null&&end!=null) {
        return propertyService.getPropertiesByAvailability(country, minPrice, maxPrice, start, end);
    }
    else if(country==null&&minPrice!=null&&maxPrice!=null&&start!=null&&end!=null){
        return propertyService.getPropertiesByPricesAndDates(minPrice,maxPrice,start,end);
    }
    else if(country!=null&&minPrice!=null&&maxPrice!=null&&start==null&&end==null){
        return propertyService.getPropertiesByPriceAndCountry(country,minPrice,maxPrice);
    }
    else if(country!=null&&minPrice==null&&maxPrice==null&&start!=null&&end!=null){
        return propertyService.getPropertesByCountryAndDates(country,start,end);
    }
    else if (country==null&&minPrice==null&&maxPrice==null&&start!=null&&end!=null){
        return propertyService.getPropertiesByDates(start,end);
    }
    else if (country==null&&minPrice!=null&&maxPrice!=null&&start==null&&end==null){
        return propertyService.getPropertiesByPrices(minPrice,maxPrice);
    }
    else if (country!=null&&minPrice==null&&maxPrice==null&&start==null&&end==null){
        return propertyService.getPropertiesByCountry(country);
    }
    return propertyService.getProperties();
    }

}


