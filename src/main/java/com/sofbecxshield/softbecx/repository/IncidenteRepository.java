
package com.sofbecxshield.softbecx.repository;

import com.sofbecxshield.softbecx.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

}
