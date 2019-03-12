package com.stackroute.editprofile.service;

import com.stackroute.editprofile.domain.UserCurrent;

public interface EditProfileService {

    UserCurrent updateUserDetails(UserCurrent userCurrent);

    void produceEditedUser(UserCurrent userCurrent);


}
