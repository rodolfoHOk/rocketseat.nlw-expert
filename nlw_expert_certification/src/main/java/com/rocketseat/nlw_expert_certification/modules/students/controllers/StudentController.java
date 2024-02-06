package com.rocketseat.nlw_expert_certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.nlw_expert_certification.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.nlw_expert_certification.modules.students.usecases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @PostMapping("/verifyIfHasCertification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
    if (verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO)) {
      return "Usuário já fez a prova";
    }
    return "Usuário pode fazer a prova";
  }
  
}
