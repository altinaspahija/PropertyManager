package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.model.Property;
import net.codejava.model.Reservation;
import net.codejava.model.User;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.ReservationRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public List<PropertyDto> getPropertiesByUserId(Integer userId) {
        List<PropertyDto> retProperties = new ArrayList<>();
        List<Property> allProperties = propertyRepository.findAll();

        allProperties.forEach(property -> {
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
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if(optionProperty.isEmpty()){
            return null;
        }

        Property retProperty = optionProperty.get();
        retProperty.setCountry(propertyDto.getCountry());
        retProperty.setAddress(propertyDto.getAddress());
        retProperty.setPrice(propertyDto.getPrice());
        retProperty.setDescription(propertyDto.getDescription());
        retProperty.setPropertyType(propertyDto.getPropertyType());
        retProperty.setAvailableFrom(propertyDto.getAvailableFrom());
        retProperty.setAvailableTo(propertyDto.getAvailableTo());

        return PropertyDto.getPropertyDto(propertyRepository.save(retProperty));
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
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();

        allProperties.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
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
    public PropertyDto addProperty(PropertyDto propertyDto) {
        Property property = PropertyDto.getProperty(propertyDto);
        propertyRepository.save(property);
        return PropertyDto.getPropertyDto(property);
    }

    @Override
    public List<PropertyDto> getPropertiesByAvailability(String country,float minPrice,float maxPrice, Date start, Date end) {


        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        /*allProperties.forEach(property -> {
            if (property.getCountry().toLowerCase().equals(country.toLowerCase()))
            {
                if (property.getPrice()==price) {
                    retPropertyDtos.add(PropertyDto.getPropertyDto(property));
                }
            }
        });*/

        List<Reservation> allReservations = reservationRepository.findAll();
        List<ReservationDto> retReservationDtos = new ArrayList<>();
        allReservations.forEach(reservation -> {
            if ((reservation.getCheckOut().after(start)&&reservation.getCheckOut().after(end)) ||
                    (reservation.getCheckIn().before(start)&&reservation.getCheckIn().before(end)))
                retReservationDtos.add(ReservationDto.getReservationDto(reservation));
        });



        //
        ArrayList<Integer> filteredList = new ArrayList<Integer>();

        for (ReservationDto retReservation : retReservationDtos) {
            filteredList.add(retReservation.getPropertyId());
        }



        List<PropertyDto> results = retPropertyDtos.stream()
                .filter(p -> filteredList.contains(p.getPropertyId()))
                .distinct()
                .collect(Collectors.toList());


        allProperties.forEach(property -> {
            if (property.getReservation().isEmpty()){
                results.add(PropertyDto.getPropertyDto(property));
            }
        });
        List<PropertyDto> returnPropertyDto = new ArrayList<>();
        results.forEach(result -> {
            if (PropertyDto.getProperty(result).getPrice()<=maxPrice&&PropertyDto.getProperty(result).getPrice()>=minPrice&&PropertyDto.getProperty(result).getCountry().equals(country)){
                returnPropertyDto.add(result);
            }
        });
/*

List<PropertyDto> result = new ArrayList<>();
for (PropertyDto retPropertyDto : retPropertyDtos) {
       for (ReservationDto retReservationDto : retReservationDtos) {
           if (!retReservationDtos.isEmpty()) {
               if (retPropertyDto.getPropertyId() == retReservationDto.getPropertyId())
                   result.add(retPropertyDto);
           } else
               result.add(retPropertyDto);
       }
   }*/


        return returnPropertyDto;

    }
}
