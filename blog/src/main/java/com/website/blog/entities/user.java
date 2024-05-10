package com.website.blog.entities;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.CascadeType;

// import org.springframework.beans.factory.annotation.Autowire;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false)
	private String name;
	private String email;
	private String password;
	private String gender;
	private String about;

	// @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    // private List<post>  posts = new ArrayList<>();
}
