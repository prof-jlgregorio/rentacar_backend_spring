package br.com.jlgregorio.rentacar.controllers;

import br.com.jlgregorio.rentacar.dto.VehicleDto;
import br.com.jlgregorio.rentacar.service.VehicleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleDto vehicleDto){
        VehicleDto dto = vehicleService.create(vehicleDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findById(@PathParam("id") long id){
        VehicleDto dto = vehicleService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VehicleDto> update(@RequestBody VehicleDto vehicleDto){
        VehicleDto dto = vehicleService.update(vehicleDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        vehicleService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find")
    public ResponseEntity<List<VehicleDto>> find(
            @RequestParam(name = "name", required = false) String name
    ){
        List<VehicleDto> vehicles = null;
        if(name == null){
            vehicles = vehicleService.findAll();
        } else {
            vehicles = vehicleService.findByName(name);
        }
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}
