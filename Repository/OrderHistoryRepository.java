package com.example.UserModule.Repository;

import com.example.UserModule.Entity.OrderHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory, String> {

    @Query(value = "SELECT * FROM order_history WHERE user_id = :userId", nativeQuery = true)
    List<OrderHistory> getHistory(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query(value = "Update order_history set rating_given = :ratingGiven, is_rated = true where merchant_id = :merchantId and product_id = :productId and user_id = :user_id", nativeQuery = true)
    void updateRating(@Param("user_id") String userId, @Param("productId") String productId, @Param("merchantId") String merchantId, @Param("ratingGiven") Integer ratingGiven);

}
