package com.stackroute.editprofile.controller;

import com.stackroute.editprofile.domain.UserCurrent;
import com.stackroute.editprofile.service.EditProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class EditProfileController {


    //Recommendation Command Service



    private EditProfileService editProfileService;

    @Autowired
    public EditProfileController(EditProfileService editProfileService) {
        this.editProfileService = editProfileService;

    }


    @PutMapping("updateDetails")
    public ResponseEntity<UserCurrent> saveUserDetails(@RequestBody UserCurrent userCurrent) {
        editProfileService.produceEditedUser(userCurrent);
        return new ResponseEntity<UserCurrent>(editProfileService.updateUserDetails(userCurrent), HttpStatus.OK);
    }


}
