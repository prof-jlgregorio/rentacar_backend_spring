package br.com.jlgregorio.rentacar.repository;

import br.com.jlgregorio.rentacar.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    public CustomerModel findByEmail(String email);

    public List<CustomerModel> findByFullNameContainsIgnoreCaseOrderByFullName(String name);

}
