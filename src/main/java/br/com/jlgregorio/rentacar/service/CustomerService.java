package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.exceptions.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.CustomerModel;
import br.com.jlgregorio.rentacar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto findById(long id){
        CustomerModel customerModel = customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente não encontrado!"));
        return CustomModelMapper.parseObject(customerModel, CustomerDto.class);
    }

    public CustomerDto create(CustomerDto customerDto){
        CustomerModel customerModel = CustomModelMapper.parseObject(customerDto, CustomerModel.class);
        return CustomModelMapper.parseObject(customerRepository.save(customerModel), CustomerDto.class);
    }

    public CustomerDto update(CustomerDto customerDto){
        CustomerModel customerModel = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
        customerModel.setFullName(customerDto.getFullName());
        customerModel.setEmail(customerDto.getEmail());
        return CustomModelMapper.parseObject(customerRepository.save(customerModel), CustomerDto.class);
    }

    public void delete(long id){
        CustomerModel customerModel = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
        customerRepository.delete(customerModel);
    }

    public List<CustomerDto> findAll(){
        var customers = customerRepository.findAll();
        return CustomModelMapper.parseObjectList(customers, CustomerDto.class);
    }

    public List<CustomerDto> findByName(String name){
        var customers = customerRepository.findByFullNameContainsIgnoreCaseOrderByFullName(name);
        return CustomModelMapper.parseObjectList(customers, CustomerDto.class);
    }

}
