package net.codejava.repository;

import net.codejava.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p WHERE p.userId = :user_id")
    public List<Property> getPropertiesByUserId(@Param("user_id") Integer userId);

    @Query("SELECT p FROM Property p WHERE p.propertyId = :property_id")
    public Property getPropertyByPropertyId (@Param("property_id") Integer propertyId);

    @Query("SELECT p FROM Property p WHERE (:price IS NULL OR p.price = :price) AND" +
            " (:country IS NULL OR p.country = :country) AND" +
            " (:available_from_date is NULL OR p.availableFrom = :available_from_date) AND" +
            " (:available_to_date is NULL OR p.availableTo = :available_to_date)")
    public List<Property> getPropertiesByFilters (@Param("price") float price,
                                                  @Param("country")  String country,
                                                  @Param("available_from_date") Date availableFrom,
                                                  @Param("available_to_date") Date availableTo);

    @Query("UPDATE Property p SET " +
            "p.country = :country , " +
            "p.address = :address , " +
            "p.price = :price , " +
            "p.description = :description , " +
            "p.propertyType = :propertytype " +
            " WHERE p.propertyId = :property_id")
    public Property updatePropertyByPropertyId(@Param("country") String country,
                                               @Param("address") String address,
                                               @Param("price") float price,
                                               @Param("description") String description,
                                               @Param("propertytype") String propertyType,
                                               @Param("property_id") Integer propertyId
    );
    @Modifying
    @Query("DELETE Property WHERE propertyId = :property_id")
    public boolean deletePropertyByPropertyId(@Param("property_id") Integer propertyId);

    @Query("SELECT p FROM Property p")
    public List<Property> getProperties();

    @Modifying
    @Query(value = "INSERT INTO Property(propertyId,country,address,price,description,propertyType,available_from_date,available_to_date) " +
            "VALUES (:property_id,:country,:address,:price,:description,:property_type,:available_from_date, :available_to_date) " +
            "WHERE p.userId = :user_id", nativeQuery = true)
    Property addPropertyByUserId(@Param("property_id")Integer propertyId,
                                 @Param("country")String country,
                                 @Param("address")String address,
                                 @Param("price")float price,
                                 @Param("description")String description,
                                 @Param("property_type")String propertyType,
                                 @Param("available_from_date")Date availableFrom,
                                 @Param("available_to_date")Date availableTo);
}
