package com.example.gateways.repository;

import com.example.gateways.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    public Device findDeviceById(Long id);
    public int deleteDeviceById(long id);
    public List<Device> findAllByGatewayId(Long id);
}
