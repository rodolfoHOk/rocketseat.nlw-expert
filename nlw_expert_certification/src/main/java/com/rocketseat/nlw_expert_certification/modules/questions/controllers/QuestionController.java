package com.rocketseat.nlw_expert_certification.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.nlw_expert_certification.modules.questions.dto.QuestionDTO;
import com.rocketseat.nlw_expert_certification.modules.questions.repositories.QuestionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/questions")
public class QuestionController {
  
  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("/technology/{technology}")
  public List<QuestionDTO> listByTechnology(@PathVariable String technology) {
    var questions = this.questionRepository.findByTechnology(technology);
    return questions.stream()
      .map(question -> QuestionDTO.mapToDTO(question)).toList();
  }
  
}
