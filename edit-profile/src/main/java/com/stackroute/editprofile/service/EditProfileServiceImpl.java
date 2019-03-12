package com.stackroute.editprofile.service;

import com.stackroute.editprofile.domain.UserCurrent;
import com.stackroute.editprofile.repository.EditProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EditProfileServiceImpl implements EditProfileService {

    EditProfileRepository editProfileRepository;

    @Autowired
    public EditProfileServiceImpl(EditProfileRepository editProfileRepository) {
        this.editProfileRepository = editProfileRepository;
    }


    @Override
    public UserCurrent updateUserDetails(UserCurrent user) {
        UserCurrent userCurrent = editProfileRepository.save(user);
        return userCurrent;
    }
}
