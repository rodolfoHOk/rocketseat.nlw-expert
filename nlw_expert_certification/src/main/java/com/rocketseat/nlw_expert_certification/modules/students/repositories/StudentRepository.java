package com.rocketseat.nlw_expert_certification.modules.students.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.nlw_expert_certification.modules.students.entities.StudentEntity;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
  
  Optional<StudentEntity> findByEmail(String email);

}
