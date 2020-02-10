package com.example.gateways.service;

import com.example.gateways.model.Device;
import com.example.gateways.model.Gateway;
import com.example.gateways.repository.DeviceRepository;
import com.example.gateways.repository.DeviceStatusRepository;
import com.example.gateways.repository.GatewayRepository;
import org.apache.tomcat.jni.Local;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DevicesService {
    final
    DeviceRepository deviceRepository;

    final
    GatewayRepository gatewayRepository;

    final
    DeviceStatusRepository deviceStatusRepository;

    public DevicesService(DeviceRepository deviceRepository, GatewayRepository gatewayRepository, DeviceStatusRepository deviceStatusRepository) {
        this.deviceRepository = deviceRepository;
        this.gatewayRepository = gatewayRepository;
        this.deviceStatusRepository = deviceStatusRepository;
    }


    public List<Device> getAllDevices(){
        List<Device> devices = new ArrayList<>();
        deviceRepository.findAll().forEach(devices::add);
        return devices;
    }

    public Device getDevice(long id) throws ResourceNotFoundException {
        try {
            return deviceRepository.findDeviceById(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Device not found with id ::" + id);
        }
    }

    public Device addOrUpdateDevice(Device device) throws Exception {
        Gateway gateway = gatewayRepository.findGatewayById(device.getGatewayObjId());
        if (deviceRepository.findAllByGatewayId(gateway.getId()).size() + 1 > 10)
            throw new Exception("More then 10 peripheral devices for each gateway");
        device.setUid(GatewaysService.get_unique_number());
        device.setDvStatus(deviceStatusRepository.findDeviceStatusById(device.getStatus_id()));
        device.setGateway(gateway);
        return deviceRepository.save(device);
    }

    public boolean deleteDevice(long id){
        try{
            deviceRepository.deleteDeviceById(id);
            return true;
        }catch (Exception e){
            throw e;
        }
    }
}
