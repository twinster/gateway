package com.example.gateways.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "gateways")
@DynamicUpdate
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique=true)
    private String serial_number;

    @Column(nullable = false)
    @Pattern(regexp="(\\d{0,3}\\.){3}\\d{0,3}",message="length must be 3")
    private String ipv4_address;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "gateway")
    //@JoinColumn(name = "company_id", nullable=false)
    private Set<Device> devices;

    @PrePersist
    public void prePersist(){
        if (devices != null) devices.forEach(device -> device.setGateway(this));
    }

    public Gateway() {
    }

    public Gateway(String name, String serial_number, String ipv4_address) {
        this.name = name;
        this.serial_number = serial_number;
        this.ipv4_address = ipv4_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serial_number;
    }

    public void setSerialNumber(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getIpv4Address() {
        return ipv4_address;
    }

    public void setIpv4Address(String ipv4_address) {
        this.ipv4_address = ipv4_address;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setMenus(Set<Device> devices) {
        this.devices = devices;
    }
}
