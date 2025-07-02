package br.com.jlgregorio.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "rentals")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "rental_date")
    private Date rentalDate;

    @Column(nullable = false)
    private Date rentalStart;

    @Column(nullable = false)
    private Date rentalEnd;

    @Column(nullable = false, name = "start_km")
    private long startKm;

    @Column(nullable = true, name = "end_km")
    private long endKm;

    @Column(nullable = false, name = "hourly_rate")
    private BigDecimal hourlyRate;

    @Column(length = 255)
    private String observations;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleModel vehicle;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public int getRentalDays(){
        long millisPorDia = 1000 * 60 * 60 * 24;
        long diffEmMillis = rentalEnd.getTime() - rentalStart.getTime();
        return (int) (diffEmMillis / millisPorDia);
    }

    public BigDecimal getRentalValue(){
        return hourlyRate.multiply(BigDecimal.valueOf(getRentalDays()));
    }


}
