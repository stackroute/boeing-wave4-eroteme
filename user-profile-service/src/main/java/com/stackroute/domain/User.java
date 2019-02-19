package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    /*
    assigning the primary keys
     */

    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> interests;

//    /*
//    getters and setters for parameters defined
//     */
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public List<String> getInterests() {
//        return interests;
//    }
//
//    public void setInterests(List<String> interests) {
//        this.interests = interests;
//    }
///*
//    TO string
//     */
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", interests=" + interests +
//                '}';
//    }
//    /*
//    constructors for parameters
//     */
//
//    public User(String email, String password, String firstName, String lastName, List<String> interests) {
//        this.email = email;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.interests = interests;
//    }
//
//
///*
//    default constructor
//     */
//
//    public User(){ }
}
