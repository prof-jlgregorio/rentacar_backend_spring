package br.com.jlgregorio.rentacar.repository;

import br.com.jlgregorio.rentacar.model.ManufacturerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerModel, Long> {

    public List<ManufacturerModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
