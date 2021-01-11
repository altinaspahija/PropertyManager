package net.codejava.service;

import net.codejava.dto.PropertyDto;
import net.codejava.exception.PropertyNotFoundException;

import java.sql.Date;
import java.util.List;

public interface PropertyService {
    List<PropertyDto> getPropertiesByUserId(Integer userId) throws PropertyNotFoundException;
    PropertyDto getPropertyByPropertyId(Integer PropertyId) throws PropertyNotFoundException;
    PropertyDto updatePropertyByPropertyId(int propertyId, PropertyDto propertyDto) throws PropertyNotFoundException;
    boolean deletePropertyByPropertyId(Integer propertyId) throws PropertyNotFoundException;
    List<PropertyDto> getProperties() throws PropertyNotFoundException;
    List<PropertyDto> getPropertyByPriceAndCountry(float price, String country) throws PropertyNotFoundException;
    PropertyDto addProperty(PropertyDto propertyDto);
    List<PropertyDto> getPropertiesByAvailability(String country, float minPrice, float maxPrice,Date start, Date end) throws PropertyNotFoundException;

    List<PropertyDto> getPropertiesByPricesAndDates(float minPrice, float maxPrice, Date start, Date end) throws PropertyNotFoundException;

    List<PropertyDto> getPropertiesByPriceAndCountry(String country, float minPrice, float maxPrice);

    List<PropertyDto> getPropertesByCountryAndDates(String country, Date start, Date end) throws PropertyNotFoundException;

    List<PropertyDto> getPropertiesByDates(Date start, Date end) throws PropertyNotFoundException;

    List<PropertyDto> getPropertiesByPrices(float minPrice, float maxPrice) throws PropertyNotFoundException;

    List<PropertyDto> getPropertiesByCountry(String country) throws PropertyNotFoundException;
}
