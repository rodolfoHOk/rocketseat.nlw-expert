package com.rocketseat.nlw_expert_certification.modules.students.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.nlw_expert_certification.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.nlw_expert_certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public boolean execute(VerifyHasCertificationDTO dto) {
    var certifications = this.certificationStudentRepository
      .findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
    if (certifications.isEmpty()) {
      return false;
    }
    return true;
  }
}
