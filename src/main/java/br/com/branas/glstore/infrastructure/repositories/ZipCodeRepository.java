package br.com.branas.glstore.infrastructure.repositories;

import br.com.branas.glstore.domain.entities.Order;
import br.com.branas.glstore.domain.entities.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, String> {
}
