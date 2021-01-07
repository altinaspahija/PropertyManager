package net.codejava.repository;

import net.codejava.model.Property;
import net.codejava.model.Role;
import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.username = :username")

	public User getUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.userId = :user_id")

	public User getUserByUserId(@Param("user_id") Integer userId);

	@Query("SELECT u FROM User u")
	public List<User> getUsers();


	@Query("UPDATE User u SET " +
			"u.username = :username , " +
			"u.fullname = :fullname , " +
			"u.password = :password , " +
			"u.phoneNumber = :phonenumber , " +
			"u.enabled = :enabled  " +
			" WHERE u.userId = :user_id")
	public User updateUserByUserId(@Param("username") String username,
											   @Param("fullname") String fullname,
											   @Param("password") String password,
											   @Param("phonenumber") String phoneNumber,
											   @Param("enabled") boolean enabled);

	@Query("DELETE User u WHERE u.userId = :user_id")
	public User deleteUserByUserId(@Param("user_id") Integer userId);

	@Modifying
	@Query(value = "INSERT INTO User(userId,username,fullname,password,phoneNumber,enabled) " +
			"VALUES (:user_id,:username,:fullname,:password,:phonenumber,:enabled) " +
			"WHERE u.userId = :user_id", nativeQuery = true)
	Property addUserByUserId(@Param("user_id")Integer userId, @Param("username")String username, @Param("fullname")String fullname
			, @Param("password")String password, @Param("phonenumber")String phoneNumber, @Param("enabled")boolean enabled);


}
