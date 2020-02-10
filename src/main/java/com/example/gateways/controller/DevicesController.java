package com.example.gateways.controller;

import com.example.gateways.model.Device;
import com.example.gateways.service.DevicesService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/devices")
public class DevicesController {
    private final DevicesService devicesService;

    public DevicesController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @RequestMapping(method= RequestMethod.GET, value="/list")
    public List<Device> getAllDevices() {
        return devicesService.getAllDevices();
    }

    @RequestMapping(method=RequestMethod.POST, value="/")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) throws Exception {
        try{
            return ResponseEntity.ok().body(devicesService.addOrUpdateDevice(device));
        }catch (Exception e)
        {
            throw new Exception(e.toString());
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/edit")
    public Device getDevice(@PathVariable long id) throws ResourceNotFoundException {
        return devicesService.getDevice(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<Device> updateDevice(@RequestBody Device device, @PathVariable Long id) throws Exception {
        try{
            return ResponseEntity.ok().body(devicesService.addOrUpdateDevice(device));
        }catch (Exception e)
        {
            throw new Exception(e.toString());
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(devicesService.deleteDevice(id));
    }

}
