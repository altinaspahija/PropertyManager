package net.codejava.service;

import net.codejava.dto.PropertyDto;

import java.util.Date;
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
    PropertyDto addPropertyByUserId(Integer propertyId,
                                          String country,
                                          String address,
                                          String description,
                                          String propertyType,
                                          Date availableFrom,
                                          Date AvailableTo);
}
