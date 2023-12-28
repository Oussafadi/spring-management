package com.example.springbootdepartement.Repositories;

import com.example.springbootdepartement.Models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Integer> {

    Optional<Employe> findById(int id);

    @Query("SELECT e FROM Employe e WHERE e.nom LIKE CONCAT('%',:query,'%') ")
    List<Employe> searchEmploye(String query);
}
