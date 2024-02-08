package com.rocketseat.nlw_expert_certification.modules.students.dto;

import java.util.List;

public record StudentCertificationAnswerDTO(
  String email,
  String technology,
  List<QuestionAnswerDTO> questionsAnswers
) {
  
} 
