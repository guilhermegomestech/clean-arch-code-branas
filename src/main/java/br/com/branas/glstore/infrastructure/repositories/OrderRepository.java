package br.com.branas.glstore.infrastructure.repositories;

import br.com.branas.glstore.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Boundary
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select max(o.serialNumberOrder) from Order o")
    Long getLastSerialNumberOrder();

    Order findBySerialNumberOrderEquals(Long serialNumber);
}
