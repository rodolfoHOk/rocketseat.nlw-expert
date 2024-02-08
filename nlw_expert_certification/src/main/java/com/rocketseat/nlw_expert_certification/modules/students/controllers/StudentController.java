package com.rocketseat.nlw_expert_certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.nlw_expert_certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.nlw_expert_certification.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.nlw_expert_certification.modules.students.usecases.StudentCertificationAnswersUseCase;
import com.rocketseat.nlw_expert_certification.modules.students.usecases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @Autowired
  private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

  @PostMapping("/verifyIfHasCertification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
    if (verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO)) {
      return "Usuário já fez a prova";
    }
    return "Usuário pode fazer a prova";
  }

  @PostMapping("/certification/answer")
  public ResponseEntity<Object> certificationAnswer(
      @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
    try {
      var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  
}
