package com.kashuo.architecture.util;

import com.kashuo.architecture.bean.JustTestObj;
import com.kashuo.architecture.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  LiXiao
 * Email:   lixiao@kashuo.com
 * Date:    2017/4/24
 * Description:
 */

public class DataCreateUtil {

    public static List<User> careteMutiUsersList() {
        List<User> users = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            User user = new User();
            user.setUsername("Name : " + i);
            user.setUserAddress("Address : " + i);
            user.setUserSex("Sex : " + i + "Male");

            users.add(user);
        }

        return users;
    }

    public static JustTestObj createJustParams() {
        JustTestObj mJustTestObj = new JustTestObj();
        mJustTestObj.setJustName("JustName");
        mJustTestObj.setJustNumber(1001);
        User mUser = new User();
        mUser.setUserSex("male");
        mUser.setUserAddress("china");
        mUser.setUsername("SuperJ");
        mJustTestObj.setJustUser(mUser);
        mJustTestObj.setJustTag(false);

        return mJustTestObj;
    }

}
