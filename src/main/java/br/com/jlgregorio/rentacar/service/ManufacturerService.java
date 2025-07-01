package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.ManufacturerDto;
import br.com.jlgregorio.rentacar.exceptions.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.ManufacturerModel;
import br.com.jlgregorio.rentacar.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    //now, we have to send DTOs and receive DTOs!!!!
    public ManufacturerDto findById(long id){
        //find the model
        ManufacturerModel model = manufacturerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        //return the dto
        return CustomModelMapper.parseObject(model, ManufacturerDto.class);
    }

    public ManufacturerDto create(ManufacturerDto manufacturerDto){
        //maps to model
        ManufacturerModel model = CustomModelMapper.parseObject(manufacturerDto, ManufacturerModel.class);
        //persists the model and set the return variable
        var result = manufacturerRepository.save(model);
        return CustomModelMapper.parseObject(result, ManufacturerDto.class);
    }

    public ManufacturerDto update(ManufacturerDto manufacturerDto){
        //find the object to update
        ManufacturerModel model = manufacturerRepository.findById(manufacturerDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        //update the attributes
        model.setName(manufacturerDto.getName());
        model.setCountry(manufacturerDto.getCountry());
        //persists the model and returns the DTO
        return CustomModelMapper.parseObject(manufacturerRepository.save(model), ManufacturerDto.class);
    }

    public void delete(long id){
        //verify if the object/entity exists
        ManufacturerModel model = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        //delete
        manufacturerRepository.delete(model);
    }

    public List<ManufacturerDto> findAll(){
        //using the inference type (var)
        var manufacturers = manufacturerRepository.findAll();
        //convert and return DTO list
        return CustomModelMapper.parseObjectList(manufacturers, ManufacturerDto.class);
    }

    public List<ManufacturerDto> findByName(String name){
        //invoke the Query Method, using the inference type (var)
        var manufacturers = manufacturerRepository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(manufacturers, ManufacturerDto.class);
    }



}