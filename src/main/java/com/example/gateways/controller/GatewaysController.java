package com.example.gateways.controller;

import com.example.gateways.model.Gateway;
import com.example.gateways.service.GatewaysService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/gateways")
public class GatewaysController {
    private final GatewaysService gatewaysService;

    public GatewaysController(GatewaysService gatewaysService) {
        this.gatewaysService = gatewaysService;
    }

    @RequestMapping(method= RequestMethod.GET, value="/list")
    public List<Gateway> getAllGateways() {
        return gatewaysService.getAllGateways();
    }

    @RequestMapping(method=RequestMethod.POST, value="/")
    public ResponseEntity<Gateway> createGateway(@RequestBody Gateway gateway) throws Exception {
        try{
            return ResponseEntity.ok().body(gatewaysService.addOrUpdateGateway(gateway));
        }catch (ConstraintViolationException e)
        {
            throw new DataIntegrityViolationException("wrong input");
        }catch (Exception e)
        {
            throw new Exception(e.toString());
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}/edit")
    public Gateway getGateway(@PathVariable long id) throws ResourceNotFoundException {
        return gatewaysService.getGateway(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<Gateway> updateGateway(@Valid @RequestBody Gateway gateway, @PathVariable(value = "id") long id) throws Exception {
        try{
            return  ResponseEntity.ok().body(gatewaysService.addOrUpdateGateway(gateway));
        }catch (ConstraintViolationException e)
        {
            throw new DataIntegrityViolationException("wrong input");
        }catch (Exception e)
        {
            throw new Exception(e.toString());
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<?> deleteGateway(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        try{
            gatewaysService.deleteGateway(id);
            return ResponseEntity.ok().build();
        }catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityViolationException("Cant delete Gateway");
        }
    }
}
