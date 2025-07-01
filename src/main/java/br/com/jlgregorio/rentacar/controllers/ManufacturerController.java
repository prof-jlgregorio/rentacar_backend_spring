package br.com.jlgregorio.rentacar.controllers;

import br.com.jlgregorio.rentacar.dto.ManufacturerDto;
import br.com.jlgregorio.rentacar.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//this is a Rest Controller
@RestController
//this is the endpoint
@RequestMapping("/manufacturers")
public class ManufacturerController {

    //inject the service
    @Autowired
    private ManufacturerService manufacturerService;

    //post request
    //now we send and receive DTOs!
    @PostMapping
   //The @RequestBody annotation maps the HTTP request body to the method argument.
    public ResponseEntity<ManufacturerDto> create(@RequestBody ManufacturerDto manufacturerDto){
        ManufacturerDto dto = manufacturerService.create(manufacturerDto);
        //return the response entity with the model and HTTP status
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //get request
    @GetMapping("/{id}")
    //The @PathVariable annotation maps the HTTP request URL param to the method argument
    public ResponseEntity<ManufacturerDto> findById(@PathVariable("id") long id){
       ManufacturerDto dto = manufacturerService.findById(id);
       //return the response entity with the model and HTTP status
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ManufacturerDto> update(@RequestBody ManufacturerDto manufacturerDto){
        var dto = manufacturerService.update(manufacturerDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        manufacturerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> find(
            @RequestParam(name = "name", required = false) String name
    ){
        List<ManufacturerDto> manufacturers = null;
        if(name == null){
            manufacturers = manufacturerService.findAll();
        } else {
            manufacturers = manufacturerService.findByName(name);
        }
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

}
