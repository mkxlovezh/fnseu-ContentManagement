package com.min.service;


import com.min.dao.WebSocketDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import java.io.IOException;


public class UpdateService implements MessageListener<String,String>{

    public void onMessage(ConsumerRecord<String,String> consumerRecord){
        if (relatedOrNot()){
            sendMessage(consumerRecord.value());
        }
    }
    public Boolean relatedOrNot(){
        return true;
    }
    public void sendMessage(String message){
        try{
            System.out.println("mess:"+message);
            for(WebSocketDao webSocketDao:WebSocketDao.wbSockets){
                webSocketDao.sendMessageAll(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
