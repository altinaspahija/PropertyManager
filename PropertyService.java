package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository repo;

    public List<Property> listAll() {
        return repo.findAll();
    }

    public List<Property> listAllByUserId(Integer userId) {
        return repo.getPropertiesByUser_id(userId);
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
