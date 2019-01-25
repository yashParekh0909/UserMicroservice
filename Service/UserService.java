package com.example.UserModule.Service;

import com.example.UserModule.Entity.UserModule;
import org.json.simple.JSONObject;

public interface UserService {

    /*
     * if(password matched) i.e. passwrod matched with the email and password given.,
     * resp['status'] = true
     * */
    // Object login(String username,String password);

    public JSONObject signup(UserModule user);

    public JSONObject login(String emailId, String password);

    public String getEmail(String userId);
}
