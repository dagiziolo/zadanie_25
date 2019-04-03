package pl.ds.legoapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegoRepository extends JpaRepository<Lego, Long> {
}
