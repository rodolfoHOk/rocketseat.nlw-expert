package com.rocketseat.nlw_expert_certification.modules.students.usecases;

import org.springframework.stereotype.Service;

import com.rocketseat.nlw_expert_certification.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {
  public boolean execute(VerifyHasCertificationDTO dto) {
    if (dto.getEmail().equals("rudolfhiok@email.com") 
        && dto.getTechnology().equals("JAVA")) {
      return true;
    }
    return false;
  }
}
