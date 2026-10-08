package com.clasificados.clasificadosGuamini.repositories;

import com.clasificados.clasificadosGuamini.entities.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AvisoRepository extends JpaRepository<Aviso,Long>{

}
