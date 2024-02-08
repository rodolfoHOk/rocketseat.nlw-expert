package com.rocketseat.nlw_expert_certification.modules.certifications.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.nlw_expert_certification.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.nlw_expert_certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public List<CertificationStudentEntity> execute() {
    return certificationStudentRepository.findTop10ByOrderByGradeDesc();
  }
  
}
