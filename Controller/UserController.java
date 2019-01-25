package com.example.UserModule.Controller;

import com.example.UserModule.Entity.UserModule;
import com.example.UserModule.Service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public JSONObject signup(@RequestBody JSONObject jsonObject){

        System.out.println(jsonObject.toJSONString());
        UserModule user = new UserModule();
        user.setName((String) jsonObject.get("name"));
        user.setEmailId((String) jsonObject.get("emailId"));
        user.setPassword((String) jsonObject.get("password"));

        JSONObject jsonObject1 = userService.signup(user);



        return jsonObject1;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject jsonObject) {

        String emailId = (String) jsonObject.get("emailId");
        String password = (String) jsonObject.get("password");

        jsonObject = userService.login(emailId, password);

        return jsonObject;
    }

    @RequestMapping(value = "/getemail", method = RequestMethod.POST)
    public String getEmail(@RequestBody String userId){
        String email = userService.getEmail(userId);
        return email;
    }




}
