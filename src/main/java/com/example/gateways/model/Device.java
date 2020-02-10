package com.example.gateways.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable=false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gateway_id", nullable = false)
    private Gateway gateway;

    @ManyToOne
    @JoinColumn(name = "device_status_id", nullable = false)
    private DeviceStatus dvStatus;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Transient
    private Long gatewayObjId;

    @Transient
    private Integer status_id;

    public Device() { }

    public Device(DeviceStatus dvStatus, Gateway gateway) {
        this.setDvStatus(dvStatus);
        this.setGateway(gateway);
    }

    public Device(DeviceStatus dvStatus) {
        this.setDvStatus(dvStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() { return uid; }

    public void setUid(Long uid) { this.uid = uid; }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DeviceStatus getDvStatus() {
        return dvStatus;
    }

    public void setDvStatus(DeviceStatus dvStatus) {
        this.dvStatus = dvStatus;
    }


    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Long getGatewayObjId() {
        return gatewayObjId;
    }

    public void setGatewayObjId(Long gatewayObjId) {
        this.gatewayObjId = gatewayObjId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
