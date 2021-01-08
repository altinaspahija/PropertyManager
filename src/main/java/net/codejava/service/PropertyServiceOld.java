package net.codejava.service;

import net.codejava.model.Property;
import net.codejava.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceOld {
    @Autowired
    private PropertyRepository repo;

    /*public List<Property> listAll() {
        return repo.findAll();
    }*/

    public List<Property> listAllByUserId(Integer userId) {
        return repo.getPropertiesByUserId(userId);
    }

    public void save(Property property) {
        repo.save(property);
    }

    public Property get(Integer property_id) {
        return repo.findById(property_id).get();
    }

    public void delete(Integer property_id) {
        repo.deleteById(property_id);
    }
}
