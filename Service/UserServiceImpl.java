package com.example.UserModule.Service;

import com.example.UserModule.Entity.UserModule;
import com.example.UserModule.Repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.GeneratedValue;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static java.util.Objects.hash;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private static final int iterations = 20*1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    @Override
    public  JSONObject signup(UserModule user)  {

        JSONObject jsonObject = new JSONObject();

        //Hash the password.
        try {
            String plainTextPassword = user.getPassword();
            user.setPassword(getSaltedHash(plainTextPassword));

            userRepository.save(user);

            jsonObject.put("status", true);
            jsonObject.put("msg", "User signed up successfully.");
            jsonObject.put("userId", user.getUserId());

            //Generate string to check the user is logged in.


        }catch (DataIntegrityViolationException ex){
            jsonObject.put("status", false);
            jsonObject.put("msg", "The user already exists. Please login.");
        }catch (Exception ex){
            jsonObject.put("status", false);
            jsonObject.put("msg", "Error in saving user. Please contact administrator.");
        }
        return jsonObject;
    }

    @Override
    public JSONObject login(String emailId, String password) {

        JSONObject jsonObject = new JSONObject();
        String hashPassword = userRepository.getInventoryList(emailId);

        try {
            Boolean op = check(password, hashPassword);
            if (check(password, hashPassword)) {
                jsonObject.put("status", true);
                jsonObject.put("userId", userRepository.getUserId(emailId));

            } else{
                jsonObject.put("status", false);
                jsonObject.put("msg", "Please enter correct password.");
            }
        }catch (Exception ex){

            jsonObject.put("status", false);
            jsonObject.put("msg", "Unable to login.");

        }
        return jsonObject;

    }

    @Override
    public String getEmail(String userId) {

        String email = userRepository.getEmailId(userId);
        return email;
    }

    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }

}
