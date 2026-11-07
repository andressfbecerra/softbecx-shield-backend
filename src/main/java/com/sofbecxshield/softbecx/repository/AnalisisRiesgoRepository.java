
package com.sofbecxshield.softbecx.repository;

import com.sofbecxshield.softbecx.model.AnalisisRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisRiesgoRepository extends JpaRepository<AnalisisRiesgo, Integer> {

}
