package br.com.jlgregorio.rentacar.exceptions;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CustomExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

}
