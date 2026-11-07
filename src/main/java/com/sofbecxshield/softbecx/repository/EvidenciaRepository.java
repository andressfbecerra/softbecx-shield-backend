
package com.sofbecxshield.softbecx.repository;

import com.sofbecxshield.softbecx.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Integer> {

}
