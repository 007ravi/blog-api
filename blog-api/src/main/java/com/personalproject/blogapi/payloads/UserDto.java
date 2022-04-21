package com.personalproject.blogapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty//check the balnk validation also
    @Size(min=4,message = "Username nust be of 4 min characters")
    private String name;

    @Email(message = "Invalid email address")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min=3,max=8,message = "Password must be 3min character and 8 max character!!")
  //  @Pattern() to set combination of value which is necessary in password use regex for desired patter
    private String password;

    @NotEmpty
    private String about;
}
