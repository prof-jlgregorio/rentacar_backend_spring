package br.com.jlgregorio.rentacar.controllers;

import br.com.jlgregorio.rentacar.dto.RentalDto;
import br.com.jlgregorio.rentacar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> findById(@PathVariable("id") long id){
        RentalDto dto = rentalService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalDto> create(@RequestBody RentalDto rentalDto){
        RentalDto dto = rentalService.create(rentalDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RentalDto> update(@RequestBody RentalDto rentalDto){
        RentalDto dto = rentalService.update(rentalDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        rentalService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> findAll(){
        var rentals = rentalService.findAll();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

}
