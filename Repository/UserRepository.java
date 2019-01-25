package com.example.UserModule.Repository;

import com.example.UserModule.Entity.UserModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModule,String> {

    @Query(name = "getPwdByEmailId", value = "SELECT password from userdetails where email_id = :emailId", nativeQuery = true)
    String getInventoryList(@Param("emailId") String emailId);

    @Query(name = "getUserIdByEmailId", value = "SELECT user_id from userdetails where email_id = :emailId", nativeQuery = true)
    String getUserId(@Param("emailId") String emailId);

    @Query(name="getEmail", value = "Select email_id from userdetails where user_id = :userId", nativeQuery = true)
    String getEmailId(@Param("userId") String userId);
}
