package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.model.Property;
import net.codejava.repository.PropertyRepository;
import net.codejava.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;


    @Override
    public List<PropertyDto> getPropertiesByUserId(Integer userId) {
        List<Property> propertiesByUserId = propertyRepository.getPropertiesByUserId(userId);
        List<PropertyDto> propertyDtos = new ArrayList<>();

        propertiesByUserId.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }

    @Override
    public PropertyDto getPropertyByPropertyId(Integer propertyId) {
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if (optionProperty.isEmpty()) return null;
        return PropertyDto.getPropertyDto(optionProperty.get());
    }

    @Override
    public List<PropertyDto> getPropertiesByFilters(float price, String country, Date availableFrom, Date availableTo) {
        List<Property> propertiesByFilter = propertyRepository.getPropertiesByFilters(price, country, availableFrom, availableTo);
        List<PropertyDto> propertyDtos = new ArrayList<>();

        propertiesByFilter.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }

    @Override
    public PropertyDto updatePropertyByPropertyId(PropertyDto propertyDto, Integer propertyId) {
        Property property = PropertyDto.getProperty(propertyDto);
        Property retProperty = propertyRepository.save(property);
        return PropertyDto.getPropertyDto(retProperty);
    }

    @Override
    public boolean deletePropertyByPropertyId(Integer propertyId) {
        Property propertyOpt = propertyRepository.getPropertyByPropertyId(propertyId);
        if (propertyOpt==null) return false;
        propertyRepository.deletePropertyByPropertyId(propertyId);
        return true;
    }

    @Override
    public List<PropertyDto> getProperties() {
        List<Property> properties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();

        properties.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }

    @Override
    public PropertyDto addPropertyByUserId(Integer propertyId, String country, String address, String description, String propertyType, Date availableFrom, Date AvailableTo) {
        Property propertyById = propertyRepository.getPropertyByPropertyId(propertyId);
        Property property = PropertyDto.getProperty(PropertyDto.getPropertyDto(propertyById));
        return PropertyDto.getPropertyDto(propertyRepository.save(property));
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


}
