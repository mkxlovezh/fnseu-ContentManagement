package com.min.dao;

import kafka.tools.ConsoleConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Repository;

import java.util.Properties;
import java.util.logging.Logger;
@Repository
public class KafkaUpdateDao {

    private static final  String Topic="";
    private static final String BROKER_LIST="";
    private static KafkaConsumer<String,String> consumer=null;

    public  Properties UpDateConsumer(){
        Properties properties=new Properties();
        properties.put("bootstrap.servers",BROKER_LIST);
        properties.put("group.id","0");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        return properties;
    }
    public String topicMessage(){
        String msg="";
        while(true){
            ConsumerRecords<String,String> records=consumer.poll(1);
            for(ConsumerRecord<String,String> record:records){
                msg+=record.value();
            }
            consumer.close();
            return msg;
        }
    }
}
