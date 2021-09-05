/*Author: Dennis Shaba */
package io.dennis.app.OntracTechonologiesApp.model;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity //this will map User as a table in my database
@Table(name = "User")
public class User {

    @Id //making userId a primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //setting userId as an attribute in my table and also setting it to not null and not updatable
    @Column(name="userId", nullable=false, unique = true, updatable=false)
    private long userId;


    @Column(nullable = false) //ensuring notnull
    @Size(min = 6, max = 50) //setting min and max constraint on attribute
    private String fullName;

    @Column(nullable=false, updatable=false)//mapping phone as an attribute, setting it to notnull
    @Size(min = 11, max = 11, message = "Phone number must be 11 digits") //setting attribute to have a min value of 11 and max of 13
    @Pattern(regexp="(0+[789]{1}[01]{1}[0-9]{8})", message = "Enter a valid phone number. example:08031254121") //setting pattern for phone
    private String phone;

    @Column(name="email", nullable=false, unique = true, updatable=false)//mapping email as an attribute, setting it to not null
    @Pattern(regexp = "(^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$)", message = "Enter a valid email. example: dennis@gmail.com")//setting pattern for email
    private String email;


    //bellow are getters and setter to enable access.
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
