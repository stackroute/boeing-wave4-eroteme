package com.stackroute.userauthenticationservice.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

    @Entity
    @Table(name = "users", uniqueConstraints = {
            @UniqueConstraint(columnNames = {
                    "email"
            }),
            @UniqueConstraint(columnNames = {
                    "password"
            })
    })
    public class User{
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//        @NaturalId
        @Id
        @Email
        private String email;
        private String password;



        public User() {}

//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }

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
