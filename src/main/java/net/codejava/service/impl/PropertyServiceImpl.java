package net.codejava.service.impl;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.PropertyDto;
import net.codejava.dto.ReservationDto;
import net.codejava.exception.PropertyNotFoundException;
import net.codejava.model.PaymentDetails;
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
import org.springframework.security.core.parameters.P;
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
    public List<PropertyDto> getPropertiesByUserId(Integer userId) throws PropertyNotFoundException {
        List<PropertyDto> retProperties = new ArrayList<>();
        List<Property> allProperties = propertyRepository.findAll();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();
        allProperties.forEach(property -> {
            if (property.getUserId().equals(userId)){
                retProperties.add(PropertyDto.getPropertyDto(property));
            }
        });
        return retProperties;
    }

    @Override
    public PropertyDto getPropertyByPropertyId(Integer propertyId) throws PropertyNotFoundException{
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if (optionProperty.isEmpty()) throw new PropertyNotFoundException();
        Property tempProperty = optionProperty.get();
        return PropertyDto.getPropertyDto(tempProperty);
    }


    @Override
    public PropertyDto updatePropertyByPropertyId(int propertyId, PropertyDto propertyDto) throws PropertyNotFoundException{
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if(optionProperty.isEmpty()) throw new PropertyNotFoundException();
        Property property = optionProperty.get();
        property.setAddress(propertyDto.getAddress());
        property.setPropertyType(propertyDto.getPropertyType());
        property.setAddress(propertyDto.getAddress());
        property.setCountry(propertyDto.getCountry());
        property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());

        propertyRepository.save(property);
        return PropertyDto.getPropertyDto(property);
    }


    @Override
    public boolean deletePropertyByPropertyId(Integer propertyId) {
        Optional<Property> optionProperty = propertyRepository.findById(propertyId);
        if (optionProperty.isEmpty()){
            return false;
        }
        Property tempProperty = optionProperty.get();
        List<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(reservation -> {
            if (reservation.getPropertyId().equals(propertyId)) {
                reservationRepository.delete(reservation);
            }});
        propertyRepository.deleteById(tempProperty.getPropertyId());
        return true;

    }

    @Override
    public List<PropertyDto> getProperties() throws PropertyNotFoundException{
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();

        allProperties.forEach(property -> propertyDtos.add(PropertyDto.getPropertyDto(property)));
        return propertyDtos;
    }



    //testing something
    public List<PropertyDto> getPropertyByPriceAndCountry(float price, String country) throws PropertyNotFoundException {
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();
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
        Optional<User> user = userRepository.findById(propertyDto.getUserId());

        //you have to be a host in order to make a property
        if (user.get().getRoleDescription().equals("host")) {
            propertyRepository.save(property);
        }
        return PropertyDto.getPropertyDto(property);
    }

    @Override
    public List<PropertyDto> getPropertiesByAvailability(String country,float minPrice,float maxPrice, Date start, Date end) throws PropertyNotFoundException{


        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();

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
            if (property.getReservation().isEmpty()||filteredList.contains(property.getPropertyId())){
                results.add(PropertyDto.getPropertyDto(property));
            }
        });
        List<PropertyDto> returnPropertyDto = new ArrayList<>();
        results.forEach(result -> {
            if (PropertyDto.getProperty(result).getPrice()<=maxPrice&&PropertyDto.getProperty(result).getPrice()>=minPrice&&PropertyDto.getProperty(result).getCountry().equals(country)){
                returnPropertyDto.add(result);
            }
        });
        if (returnPropertyDto.isEmpty()) throw new PropertyNotFoundException();
        return returnPropertyDto;

    }

    @Override
    public List<PropertyDto> getPropertiesByPricesAndDates(float minPrice, float maxPrice, Date start, Date end) throws PropertyNotFoundException{
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();

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
            if (property.getReservation().isEmpty()||filteredList.contains(property.getPropertyId())){
                results.add(PropertyDto.getPropertyDto(property));
            }
        });
        List<PropertyDto> returnPropertyDto = new ArrayList<>();
        results.forEach(result -> {
            if (PropertyDto.getProperty(result).getPrice()<=maxPrice&&PropertyDto.getProperty(result).getPrice()>=minPrice){
                returnPropertyDto.add(result);
            }
        });
        if (returnPropertyDto.isEmpty()) throw new PropertyNotFoundException();
        return returnPropertyDto;
    }

    @Override
    public List<PropertyDto> getPropertiesByPriceAndCountry(String country, float minPrice, float maxPrice) {
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        allProperties.forEach(property -> {
            if (property.getCountry().toLowerCase().equals(country.toLowerCase())&&(property.getPrice() <= maxPrice&&property.getPrice() >= minPrice))
            {
                retPropertyDtos.add(PropertyDto.getPropertyDto(property));
            }
        });
        return retPropertyDtos;
    }

    @Override
    public List<PropertyDto> getPropertesByCountryAndDates(String country, Date start, Date end) throws PropertyNotFoundException {
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();

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
            if (property.getReservation().isEmpty()||filteredList.contains(property.getPropertyId())){
                results.add(PropertyDto.getPropertyDto(property));
            }
        });
        List<PropertyDto> returnPropertyDto = new ArrayList<>();
        results.forEach(result -> {
            if (PropertyDto.getProperty(result).getCountry().equals(country)){
                returnPropertyDto.add(result);
            }
        });
        if (returnPropertyDto.isEmpty()) throw new PropertyNotFoundException();
        return returnPropertyDto;
    }

    @Override
    public List<PropertyDto> getPropertiesByDates(Date start, Date end) throws PropertyNotFoundException{
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        if (allProperties.isEmpty()) throw new PropertyNotFoundException();

        List<Reservation> allReservations = reservationRepository.findAll();
        List<ReservationDto> retReservationDtos = new ArrayList<>();
        allReservations.forEach(reservation -> {
            if ((reservation.getCheckOut().after(start)&&reservation.getCheckOut().after(end)) ||
                    (reservation.getCheckIn().before(start)&&reservation.getCheckIn().before(end)))
                retReservationDtos.add(ReservationDto.getReservationDto(reservation));
        });
        ArrayList<Integer> filteredList = new ArrayList<Integer>();

        for (ReservationDto retReservation : retReservationDtos) {
            filteredList.add(retReservation.getPropertyId());
        }



        List<PropertyDto> results = retPropertyDtos.stream()
                .filter(p -> filteredList.contains(p.getPropertyId()))
                .distinct()
                .collect(Collectors.toList());


        allProperties.forEach(property -> {
            if (property.getReservation().isEmpty()||filteredList.contains(property.getPropertyId())){
                results.add(PropertyDto.getPropertyDto(property));
            }
        });
        if (results.isEmpty()) throw new PropertyNotFoundException();
        return results;
    }

    @Override
    public List<PropertyDto> getPropertiesByPrices(float minPrice, float maxPrice) throws PropertyNotFoundException{
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        allProperties.forEach(property -> {
            if (property.getPrice() <= maxPrice&&property.getPrice() >= minPrice)
            {
                retPropertyDtos.add(PropertyDto.getPropertyDto(property));
            }
        });
        if (retPropertyDtos.isEmpty()) throw new PropertyNotFoundException();
        return retPropertyDtos;
    }

    @Override
    public List<PropertyDto> getPropertiesByCountry(String country) throws PropertyNotFoundException{
        List<Property> allProperties = propertyRepository.findAll();
        List<PropertyDto> retPropertyDtos = new ArrayList<>();
        allProperties.forEach(property -> {
            if (property.getCountry().toLowerCase().equals(country.toLowerCase()))
            {
                retPropertyDtos.add(PropertyDto.getPropertyDto(property));
            }
        });
        if (retPropertyDtos.isEmpty()) throw new PropertyNotFoundException();
        return retPropertyDtos;
    }
}
