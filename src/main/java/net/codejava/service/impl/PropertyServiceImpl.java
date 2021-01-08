package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.model.Property;
import net.codejava.model.User;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PropertyDto> getPropertiesByUserId(Integer userId) {
        List<PropertyDto> retProperties = new ArrayList<>();
        List<Property> propertiesByUserId = propertyRepository.findAll();

        propertiesByUserId.forEach(property -> {
            if (property.getUserId().equals(userId)){
                retProperties.add(PropertyDto.getPropertyDto(property));
            }
        });
        return retProperties;
    }

    @Override
    public PropertyDto getPropertyByPropertyId(Integer propertyId) {
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if (optionProperty.isEmpty()) return null;
        Property tempProperty = optionProperty.get();
        return PropertyDto.getPropertyDto(tempProperty);
    }

    @Override
    public List<PropertyDto> getPropertiesByFilters(float price, String country, Date availableFrom, Date availableTo) {
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        allProperties.forEach(property -> {
            if (property.getCountry().toLowerCase().equals(country.toLowerCase()))
            {
                if (property.getPrice()==price) {
                    if (property.getAvailableFrom().equals(availableFrom)){
                        if (property.getAvailableTo().equals(availableTo)){
                            retPropertyDtos.add(PropertyDto.getPropertyDto(property));
                        }
                    }
                }
            }
        });
        return retPropertyDtos;
    }

    @Override
    public PropertyDto updatePropertyByPropertyId(PropertyDto propertyDto, Integer propertyId) {
        Property property = PropertyDto.getProperty(propertyDto);
        Property retProperty = propertyRepository.save(property);
        return PropertyDto.getPropertyDto(retProperty);
    }

    @Override
    public boolean deletePropertyByPropertyId(Integer propertyId) {
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if (optionProperty.isEmpty()){
            return false;
        }
        Property tempProperty = optionProperty.get();
        propertyRepository.deleteById(tempProperty.getPropertyId());
        return true;
    }

    @Override
    public List<PropertyDto> getProperties() {
        List<Property> properties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();

        properties.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }



    //testing something
    public List<PropertyDto> getPropertyByPriceAndCountry(float price, String country) {
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        allProperties.forEach(property -> {
            if (property.getCountry().toLowerCase().equals(country.toLowerCase()))
            {
                if (property.getPrice() == price) retPropertyDtos.add(PropertyDto.getPropertyDto(property));
            }
        });
        return retPropertyDtos;
    }

    @Override
    public PropertyDto addPropertyByUserId(PropertyDto propertyDto) {
        Property property = PropertyDto.getProperty(propertyDto);
        propertyRepository.save(property);
        return PropertyDto.getPropertyDto(property);
    }
}
