package br.com.jlgregorio.rentacar.controllers;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") long id){
        CustomerDto dto = customerService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        var result = customerService.create(customerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto){
        var result = customerService.update(customerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        customerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find")
    public ResponseEntity<List<CustomerDto>> find(
            @RequestParam(name = "name", required = false) String name
    ){
        List<CustomerDto> customers = null;
        if(name == null){
            customers = customerService.findAll();
        } else {
            customers = customerService.findByName(name);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


}
