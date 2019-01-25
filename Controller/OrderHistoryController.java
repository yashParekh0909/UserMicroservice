package com.example.UserModule.Controller;

import com.example.UserModule.Dto.OrderHistoryDTO;
import com.example.UserModule.Dto.OrderHistoryWrapper;
import com.example.UserModule.Entity.OrderHistory;
import com.example.UserModule.Entity.UserModule;
import com.example.UserModule.Service.OrderHistoryService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class OrderHistoryController {

    @Autowired
    OrderHistoryService orderHistoryService;

    @RequestMapping(value = "/getOrderHistory", method = RequestMethod.POST)
    public JSONObject getOrderHistory(@RequestBody JSONObject object){

        JSONObject jsonObject = new JSONObject();
        String userId = (String) object.get("userId");

        if(userId == "" || userId.isEmpty()){
            jsonObject.put("status", false);
            jsonObject.put("msg", "Please login to continue.");
        }
        try {
            List<OrderHistory> list = orderHistoryService.getHistory(userId);


            jsonObject.put("status", true);
            jsonObject.put("list", list);
        } catch (Exception ex){
            jsonObject.put("status", false);
            jsonObject.put("msg", "Error occured. Please contact administrator.");
        }
        return jsonObject;

    }

    @RequestMapping(value = "/updateRating", method = RequestMethod.POST)
    public JSONObject updateRating(@RequestBody JSONObject jsonObject){
        JSONObject object;
        System.out.println(jsonObject.toJSONString());
        object = orderHistoryService.updateHistory(jsonObject);

        return object;
    }

    @RequestMapping(value = "/insertOrderHistory", method = RequestMethod.POST)
    public JSONObject insertOrderHistory(@RequestBody OrderHistoryWrapper orderHistoryWrapper){

        JSONObject orderHistoryJsonResponse = new JSONObject();
        List<OrderHistoryDTO> orderHistoryDTOList = orderHistoryWrapper.getOrderHistoryDTOList();
        List<OrderHistory> orderHistories = new ArrayList<>();

        try {
            // Convert dto to entity.
            for (OrderHistoryDTO orderHistoryDTO : orderHistoryDTOList) {
                OrderHistory orderHistory = new OrderHistory();
                UserModule userModule = new UserModule();
                BeanUtils.copyProperties(orderHistoryDTO, orderHistory);

                userModule.setUserId(orderHistoryDTO.getUserId());
                orderHistory.setUserModule(userModule);
                orderHistories.add(orderHistory);
            }

            orderHistoryJsonResponse = orderHistoryService.insertHistory(orderHistories);
        } catch (Exception ex){
            orderHistoryJsonResponse.put("status", false);
            orderHistoryJsonResponse.put("msg", "Error in updating order history. Please contact administrator.");
        }
        return orderHistoryJsonResponse;
    }


}
