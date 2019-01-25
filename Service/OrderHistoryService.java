package com.example.UserModule.Service;

import com.example.UserModule.Entity.OrderHistory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderHistoryService {

    public List<OrderHistory> getHistory(String userId);

    public JSONObject insertHistory(List<OrderHistory> list);

    public JSONObject updateHistory(JSONObject object);
}
