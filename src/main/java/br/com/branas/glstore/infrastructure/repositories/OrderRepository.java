package br.com.branas.glstore.infrastructure.repositories;

import br.com.branas.glstore.application.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
