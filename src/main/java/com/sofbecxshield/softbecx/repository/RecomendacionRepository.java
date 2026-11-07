
package com.sofbecxshield.softbecx.repository;

import com.sofbecxshield.softbecx.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

}
