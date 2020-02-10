package com.example.gateways.repository;

import com.example.gateways.model.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Integer> {
    DeviceStatus findDeviceStatusById(Integer status_id);
}
