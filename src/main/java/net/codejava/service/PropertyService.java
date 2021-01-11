package net.codejava.service;

import net.codejava.dto.PropertyDto;

import java.sql.Date;
import java.util.List;

public interface PropertyService {
    List<PropertyDto> getPropertiesByUserId(Integer userId);
    PropertyDto getPropertyByPropertyId(Integer PropertyId);
    PropertyDto updatePropertyByPropertyId(PropertyDto propertyDto);
    boolean deletePropertyByPropertyId(Integer propertyId);
    List<PropertyDto> getProperties();
    List<PropertyDto> getPropertyByPriceAndCountry(float price, String country);
    PropertyDto addProperty(PropertyDto propertyDto);
    List<PropertyDto> getPropertiesByAvailability(String country, float minPrice, float maxPrice,Date start, Date end);

    List<PropertyDto> getPropertiesByPricesAndDates(float minPrice, float maxPrice, Date start, Date end);

    List<PropertyDto> getPropertiesByPriceAndCountry(String country, float minPrice, float maxPrice);

    List<PropertyDto> getPropertesByCountryAndDates(String country, Date start, Date end);

    List<PropertyDto> getPropertiesByDates(Date start, Date end);

    List<PropertyDto> getPropertiesByPrices(float minPrice, float maxPrice);

    List<PropertyDto> getPropertiesByCountry(String country);
}
