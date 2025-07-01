package br.com.jlgregorio.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

import java.time.LocalDateTime;

//the @Data annotation includes all we need! getters, setters, constructors, equalsAndHashCode
@Data
//This class is an entity, that is, it represents a database table.
@Entity
//The table name
@Table(name = "manufacturers")
public class ManufacturerModel {
    //define the primary key
    @Id
    //the id will be generated using an auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //define the attribute name: not null and length of 50 chars
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    //define the attribute country: not null and length of 50 chars
    @Column(nullable = false, length = 50)
    private String country;
    //define the attributes to monitoring or auditing
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
