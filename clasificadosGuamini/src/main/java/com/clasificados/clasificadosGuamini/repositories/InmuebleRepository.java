package com.clasificados.clasificadosGuamini.repositories;

import com.clasificados.clasificadosGuamini.entities.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface InmuebleRepository extends JpaRepository<Inmueble,Long>{

}
