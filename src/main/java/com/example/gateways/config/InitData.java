package com.example.gateways.config;

import com.example.gateways.model.DeviceStatus;
import com.example.gateways.repository.DeviceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData {
    private final DeviceStatusRepository deviceStatusRepository;

    @Autowired
    public InitData(DeviceStatusRepository deviceStatusRepository){
        this.deviceStatusRepository = deviceStatusRepository;
    }

    @Bean
    public boolean insertTestData(){
        try{
            deviceStatusRepository.save(new DeviceStatus("Online", "online"));
            deviceStatusRepository.save(new DeviceStatus("Offline", "offline"));
            return true;
        }
        catch (Exception e){
            System.out.println("error while inserting data " + e.toString());
            return false;
        }
    }
}
