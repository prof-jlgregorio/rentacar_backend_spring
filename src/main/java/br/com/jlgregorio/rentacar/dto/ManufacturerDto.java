package br.com.jlgregorio.rentacar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ManufacturerDto{
    private long id;
    private String name;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
