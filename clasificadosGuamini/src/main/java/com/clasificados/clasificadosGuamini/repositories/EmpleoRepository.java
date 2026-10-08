package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import org.springframework.stereotype.Repository;

@Repository 
public interface EmpleoRepository extends JpaRepository<Empleo,Long> {

}
