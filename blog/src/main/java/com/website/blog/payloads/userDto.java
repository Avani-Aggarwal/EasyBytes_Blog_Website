package com.website.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class userDto {

    private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be minimum of 4 characters")
	private String name;

	@Email(message = "Please enter a valid email Id")
	private String email;

	@NotEmpty
	@NotBlank
    @NotNull
    @Size(min = 8, max = 128)
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    //      message = "Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String password;

	@NotEmpty
	private String gender;

	private String about;
}
