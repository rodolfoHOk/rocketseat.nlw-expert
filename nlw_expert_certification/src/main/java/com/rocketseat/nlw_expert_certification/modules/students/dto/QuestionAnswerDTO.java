package com.rocketseat.nlw_expert_certification.modules.students.dto;

import java.util.UUID;

public record QuestionAnswerDTO(
  UUID questionID,
  UUID alternativeID
) {

}
