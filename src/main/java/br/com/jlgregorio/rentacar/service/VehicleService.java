package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.VehicleDto;
import br.com.jlgregorio.rentacar.exceptions.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.ManufacturerModel;
import br.com.jlgregorio.rentacar.model.VehicleModel;
import br.com.jlgregorio.rentacar.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleDto findById(long id){
        VehicleModel model = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        return CustomModelMapper.parseObject(vehicleRepository.save(model), VehicleDto.class);
    }

    public VehicleDto create(VehicleDto vehicleDto){
        VehicleModel vehicleModel = CustomModelMapper.parseObject(vehicleDto, VehicleModel.class);
        var result = vehicleRepository.save(vehicleModel);
        return CustomModelMapper.parseObject(result, VehicleDto.class);
    }

    public VehicleDto update(VehicleDto vehicleDto){
        VehicleModel vehicleModel = vehicleRepository.findById(vehicleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado!"));
        vehicleModel.setName(vehicleDto.getName());
        vehicleModel.setColor(vehicleDto.getColor());
        vehicleModel.setYear(vehicleDto.getYear());
        vehicleModel.setManufacturer(CustomModelMapper.parseObject(vehicleDto.getManufacturer(), ManufacturerModel.class));
        return CustomModelMapper.parseObject(vehicleRepository.save(vehicleModel), VehicleDto.class);
    }

    public void delete(long id){
        VehicleModel vehicleModel = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado!"));
        vehicleRepository.delete(vehicleModel);
    }

    public List<VehicleDto> findAll(){
        var vehicles = vehicleRepository.findAll();
        return CustomModelMapper.parseObjectList(vehicles, VehicleDto.class);
    }

    public List<VehicleDto> findByName(String name){
        var vehicles = vehicleRepository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(vehicles, VehicleDto.class);
    }




}
