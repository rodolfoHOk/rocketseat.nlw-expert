package com.rocketseat.nlw_expert_certification.modules.questions.dto;

import java.util.UUID;

import com.rocketseat.nlw_expert_certification.modules.questions.entities.AlternativesEntity;

public record AlternativeDTO(
  UUID id,
  String description
) {

  public static AlternativeDTO mapToDTO(AlternativesEntity entity) {
    return new AlternativeDTO(entity.getId(), entity.getDescription());
  }
  
} 
