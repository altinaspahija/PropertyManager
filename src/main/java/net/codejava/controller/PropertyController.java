package net.codejava.controller;

import java.sql.Date;
import java.util.List;
import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;
import net.codejava.exception.InvalidParameterException;
import net.codejava.exception.PropertyNotFoundException;
import net.codejava.exception.UserNotFoundException;
import net.codejava.service.PropertyService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private UserService userService;

    @GetMapping("/propertiesauth")
    public List<PropertyDto> getPropertiesAuth(@RequestParam Integer userId) throws UserNotFoundException, PropertyNotFoundException {
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


    //http://localhost:9090/property/propertyId GET
    @GetMapping("property/{propertyId}")
    public PropertyDto getPropertyByPropertyId(@PathVariable Integer propertyId) throws PropertyNotFoundException {
        PropertyDto property = propertyService.getPropertyByPropertyId(propertyId);
        if (property == null) return null;
        else return property;
    }

    //http://localhost:9090/properties GET
    @GetMapping("properties")
    public List<PropertyDto> getProperties() throws PropertyNotFoundException {
        return propertyService.getProperties();
    }

    //http://localhost:9090/property/userId GET
    @GetMapping("properties/{userId}")
    public List<PropertyDto> getPropertiesByUserId(@PathVariable Integer userId) throws PropertyNotFoundException {
        return propertyService.getPropertiesByUserId(userId);
    }

    //http://localhost:9090/property/propertyId DELETE
    @DeleteMapping("property/{propertyId}")
    public boolean deleteProperty(@PathVariable Integer propertyId) throws PropertyNotFoundException {
        return propertyService.deletePropertyByPropertyId(propertyId);
    }

    //http://localhost:9090/property POST
    @PostMapping("property")
    public PropertyDto addProperty(@RequestBody PropertyDto propertyDto) {
        return propertyService.addProperty(propertyDto);
    }

    //http://localhost:9090/property PUT
    @PutMapping("property")
    public PropertyDto updateProperty(@RequestParam int propertyId, @RequestBody PropertyDto propertyDto) throws PropertyNotFoundException {
        return propertyService.updatePropertyByPropertyId(propertyId, propertyDto);
    }

    //http://localhost:9090/filterProperties GET
    @GetMapping("filterProperties")
    public List<PropertyDto> getFilterProperties(@RequestParam(required = false) String country,
                                                 @RequestParam(required = false) Float minPrice,
                                                 @RequestParam(required = false) Float maxPrice,
                                                 @RequestParam(required = false) Date start,
                                                 @RequestParam(required = false) Date end) throws PropertyNotFoundException, InvalidParameterException {

    if (country!=null&&minPrice!=null&&maxPrice!=null&&start!=null&&end!=null) {
        return propertyService.getPropertiesByAvailability(country, minPrice, maxPrice, start, end);
    }
    else if(country==null&&minPrice==null&&maxPrice!=null&&start!=null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice!=null&&maxPrice==null&&start!=null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice!=null&&maxPrice!=null&&start==null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice!=null&&maxPrice!=null&&start!=null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice==null&&maxPrice!=null&&start==null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice==null&&maxPrice!=null&&start!=null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice==null&&maxPrice!=null&&start==null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice!=null&&maxPrice==null&&start==null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice==null&&maxPrice==null&&start!=null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice==null&&maxPrice==null&&start==null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice!=null&&maxPrice!=null&&start==null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice!=null&&maxPrice==null&&start!=null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice==null&&maxPrice!=null&&start!=null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country!=null&&minPrice!=null&&maxPrice!=null&&start!=null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice==null&&maxPrice==null&&start==null&&end!=null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice==null&&maxPrice==null&&start!=null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice!=null&&maxPrice==null&&start==null&&end==null){
        throw new InvalidParameterException();
    }
    else if(country==null&&minPrice==null&&maxPrice!=null&&start==null&&end==null){
        throw new InvalidParameterException();
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
    else{
    return propertyService.getProperties();
    }
    }

}


