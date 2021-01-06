package net.codejava.repository;

import net.codejava.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p WHERE p.userId = :user_id")
    public List<Property> getPropertiesByUser_id (@Param("user_id") Integer userId);

}
