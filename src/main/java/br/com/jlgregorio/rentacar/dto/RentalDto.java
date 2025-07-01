package br.com.jlgregorio.rentacar.dto;

import lombok.Data;

import java.math.BigDecimal;

import java.util.Date;

@Data
public class RentalDto {

    private long id;

    private Date rentalDate;

    private Date begin;

    private Date end;

    private long startKm;

    private long endKm;

    private BigDecimal hourlyRate;

    private String observations;

    private CustomerDto customer;

    private VehicleDto vehicle;


}
