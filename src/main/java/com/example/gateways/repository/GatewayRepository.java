package com.example.gateways.repository;

import com.example.gateways.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    public Gateway findGatewayById(Long id);
}
