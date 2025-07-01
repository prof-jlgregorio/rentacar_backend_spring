package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.RentalDto;
import br.com.jlgregorio.rentacar.exceptions.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.CustomerModel;
import br.com.jlgregorio.rentacar.model.RentalModel;
import br.com.jlgregorio.rentacar.model.VehicleModel;
import br.com.jlgregorio.rentacar.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    public RentalDto findById(long id){
        RentalModel model = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado!"));
        return CustomModelMapper.parseObject(model, RentalDto.class);
    }

    public RentalDto create(RentalDto rentalDto){
        RentalModel model = CustomModelMapper.parseObject(rentalDto, RentalModel.class);
        return CustomModelMapper.parseObject(rentalRepository.save(model), RentalDto.class);
    }

    public RentalDto update(RentalDto rentalDto){
        RentalModel model = rentalRepository.findById(rentalDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado!"));
        model.setBegin(rentalDto.getBegin());
        model.setEnd(rentalDto.getEnd());
        model.setCustomer(CustomModelMapper.parseObject(rentalDto.getCustomer(), CustomerModel.class));
        model.setObservations(rentalDto.getObservations());
        model.setEndKm(rentalDto.getEndKm());
        model.setStartKm(rentalDto.getStartKm());
        model.setVehicle(CustomModelMapper.parseObject(rentalDto.getVehicle(), VehicleModel.class));
        model.setRentalDate(rentalDto.getRentalDate());
        model.setHourlyRate(rentalDto.getHourlyRate());
        return CustomModelMapper.parseObject(rentalRepository.save(model), RentalDto.class);
    }

    public void delete(long id){
        RentalModel model = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado!"));
        rentalRepository.delete(model);
    }

    public List<RentalDto> findAll(){
        var rentals = rentalRepository.findAll();
        return CustomModelMapper.parseObjectList(rentals, RentalDto.class);
    }

    public List<RentalDto> findByCustomerIdAndDateBetween(long customerId, Date start, Date end){
        var rentals = rentalRepository.findByCustomerIdAndRentalDateBetween(customerId, start, end);
        return CustomModelMapper.parseObjectList(rentals, RentalDto.class);
    }

}
