package com.example.gateways.model;

import javax.persistence.*;

@Entity
@Table(name = "device_status")
public class DeviceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "id_name", unique = true, nullable = false)
    private String idName;

    public DeviceStatus(){}

    public DeviceStatus(String name, String idName) {
        this.name = name;
        this.idName = idName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }
}
