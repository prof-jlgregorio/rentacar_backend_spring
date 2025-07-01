package br.com.jlgregorio.rentacar.repository;

import br.com.jlgregorio.rentacar.model.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {

    public List<RentalModel> findByCustomerIdAndRentalDateBetween(long customerId, Date begin, Date end);

}
