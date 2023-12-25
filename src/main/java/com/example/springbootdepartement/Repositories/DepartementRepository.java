package com.example.springbootdepartement.Repositories;

import com.example.springbootdepartement.Models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Double> {

    Optional<Departement> findById(Double id);
   /*
    @Query("SELECT d from Departement d where d.id_dept=:id")
    Optional<Departement> findDepartementById(Double id);
    */
}
