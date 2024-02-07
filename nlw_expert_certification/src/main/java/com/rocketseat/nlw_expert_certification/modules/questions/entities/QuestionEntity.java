package com.rocketseat.nlw_expert_certification.modules.questions.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 50)
  private String technology;

  @Column
  private String description;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "questionId")
  private List<AlternativesEntity> alternatives;

}