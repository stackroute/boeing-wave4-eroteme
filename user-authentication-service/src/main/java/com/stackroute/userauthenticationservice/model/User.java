package com.stackroute.userauthenticationservice.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
    //credentials present in mysql database with  table named users.
    @Entity
    @Table(name = "users", uniqueConstraints = {
            @UniqueConstraint(columnNames = {
                    "email"
            }),
            @UniqueConstraint(columnNames = {
                    "password"
            })
    })
    //user object having email and password.
    public class User{

        @Id
        @Email
        private String email;
        private String password;



        public User() {}



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public User(String email, String password) {

            this.email = email;
            this.password = password;
        }


}
