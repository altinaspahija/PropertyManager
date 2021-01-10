package net.codejava.service;

import net.codejava.dto.PropertyDto;

import java.sql.Date;
import java.util.List;

public interface PropertyService {
    List<PropertyDto> getPropertiesByUserId(Integer userId);

    PropertyDto getPropertyByPropertyId(Integer PropertyId);

    List<PropertyDto> getPropertiesByFilters (float price,
                                              String country,
                                              Date availableFrom,
                                              Date availableTo);

    PropertyDto updatePropertyByPropertyId(PropertyDto propertyDto, Integer propertyId);

    boolean deletePropertyByPropertyId(Integer propertyId);
    List<PropertyDto> getProperties();

    //testing something new.
    List<PropertyDto> getPropertyByPriceAndCountry(float price, String country);

    PropertyDto addProperty(PropertyDto propertyDto);
}
