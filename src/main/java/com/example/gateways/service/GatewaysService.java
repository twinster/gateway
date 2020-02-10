package com.example.gateways.service;

import com.example.gateways.model.DeviceStatus;
import com.example.gateways.model.Gateway;
import com.example.gateways.repository.DeviceRepository;
import com.example.gateways.repository.DeviceStatusRepository;
import com.example.gateways.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.velocity.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class GatewaysService {
    private final GatewayRepository gatewayRepository;

    private final DeviceStatusRepository deviceStatusRepository;
    private final DeviceRepository deviceRepository;

    public GatewaysService(GatewayRepository gatewayRepository, DeviceRepository deviceRepository, DeviceStatusRepository deviceStatusRepository) {
        this.gatewayRepository = gatewayRepository;
        this.deviceStatusRepository = deviceStatusRepository;
        this.deviceRepository = deviceRepository;
    }

    static Long get_unique_number(){
        return new Date().getTime() + new Random().nextInt(1000);
    }

    public List<Gateway> getAllGateways() {
        List<Gateway> gateways = new ArrayList<>(gatewayRepository.findAll());
        return gateways;
    }

    public Gateway getGateway(Long id) throws ResourceNotFoundException {
        try{
            Gateway gateway = gatewayRepository.findGatewayById(id);
            return gateway;
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Gateway not found for this id :: " + id);
        }
    }

    public Gateway addOrUpdateGateway(Gateway gateway) throws Exception {
        if (deviceRepository.findAllByGatewayId(gateway.getId()).size() + gateway.getDevices().size() > 10)
            throw new Exception("More then 10 peripheral devices for each gateway");
        gateway.setSerialNumber(get_unique_number().toString());
        gateway.getDevices().forEach(device -> {
            device.setDvStatus(deviceStatusRepository.findDeviceStatusById(device.getStatus_id()));
            device.setUid(get_unique_number());
        });
        return gatewayRepository.save(gateway);
    }

    public boolean deleteGateway(long id) throws ResourceNotFoundException {
        try{
            gatewayRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw e;
        }
    }
}
