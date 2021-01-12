package net.codejava.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "username")
	private String username;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "password")
	private String password;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "role_description")
	private String roleDescription;

	@Column(name = "enabled", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean enabled = true;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();
}

