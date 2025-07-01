package br.com.jlgregorio.rentacar.dto;

import lombok.Data;

@Data
public class VehicleDto {

    private long id;
    private String name;
    private int year;
    private String color;
    private ManufacturerDto manufacturer;

}
