package com.rocketseat.nlw_expert_certification.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.nlw_expert_certification.modules.certifications.usecases.Top10RankingUseCase;
import com.rocketseat.nlw_expert_certification.modules.students.entities.CertificationStudentEntity;


@RestController
@RequestMapping("/ranking")
public class RankingController {

  @Autowired
  private Top10RankingUseCase top10RankingUseCase;

  @GetMapping()
  public List<CertificationStudentEntity> top10() {
    return top10RankingUseCase.execute();
  }

}
