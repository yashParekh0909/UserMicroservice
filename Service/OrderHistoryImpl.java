package com.example.UserModule.Service;

import com.example.UserModule.Entity.OrderHistory;
import com.example.UserModule.Repository.OrderHistoryRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
public class OrderHistoryImpl implements OrderHistoryService {

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<OrderHistory> getHistory(String userId) {

        List<OrderHistory> orderHistories = orderHistoryRepository.getHistory(userId);
        List<OrderHistory> orderHistories1 = new ArrayList<>();

        for(OrderHistory orderHistory: orderHistories){
            orderHistory.setUserModule(null);
            orderHistories1.add(orderHistory);
        }
        return orderHistories1;
    }

    @Override
    @Transactional(readOnly = false)
    public JSONObject insertHistory(List<OrderHistory> list) {

        JSONObject object = new JSONObject();

        try {
            Iterator<OrderHistory> iterator = list.iterator();

            for (OrderHistory orderHistory : list) {

                orderHistoryRepository.save(orderHistory);
            }

            object.put("status", true);
            object.put("msg", "Data saved successfully.");
        } catch (Exception ex){
            object.put("status", false);
            object.put("msg", "Unable to save data. Please contact the administrator.");

        }

        return object;
    }

    @Override
    @Transactional(readOnly = false)
    public JSONObject updateHistory(JSONObject object) {

        JSONObject jsonObject = new JSONObject();
        try {
            String userId = (String) object.get("userId");
            String productId = (String) object.get("productId");
            String merchantId = (String) object.get("merchantId");
            Integer rating = (Integer) object.get("rating");
            orderHistoryRepository.updateRating(userId, productId, merchantId, rating);
            jsonObject.put("status", true);
        } catch (Exception ex){

            jsonObject.put("status", false);

        }

        return jsonObject;

    }
}
