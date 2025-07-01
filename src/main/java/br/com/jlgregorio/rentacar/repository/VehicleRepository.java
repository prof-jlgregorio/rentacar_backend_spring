package br.com.jlgregorio.rentacar.repository;

import br.com.jlgregorio.rentacar.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {

    public List<VehicleModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
