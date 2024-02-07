package com.rocketseat.nlw_expert_certification.modules.questions.dto;

import java.util.List;
import java.util.UUID;

import com.rocketseat.nlw_expert_certification.modules.questions.entities.QuestionEntity;

public record QuestionDTO(
  UUID id,
  String technology,
  String description,
  List<AlternativeDTO> alternatives
) {

  public static QuestionDTO mapToDTO(QuestionEntity entity) {
    var alternatives = entity.getAlternatives().stream()
      .map(alternative -> AlternativeDTO.mapToDTO(alternative)).toList();
    return new QuestionDTO(
      entity.getId(),
      entity.getTechnology(),
      entity.getDescription(),
      alternatives
    );
  }
  
} 
