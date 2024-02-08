package com.rocketseat.nlw_expert_certification.modules.students.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rocketseat.nlw_expert_certification.modules.questions.repositories.QuestionRepository;
import com.rocketseat.nlw_expert_certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.nlw_expert_certification.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.nlw_expert_certification.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.nlw_expert_certification.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.nlw_expert_certification.modules.students.entities.StudentEntity;
import com.rocketseat.nlw_expert_certification.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.nlw_expert_certification.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;
  
  @Transactional
  public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {
    var hasCertification = verifyIfHasCertificationUseCase
      .execute(new VerifyHasCertificationDTO(dto.email(), dto.technology()));

    if (hasCertification) {
      throw new Exception("Você já tirou sua certificação!");
    }
    
    var optionalStudent = studentRepository.findByEmail(dto.email());
    StudentEntity student;
    if (optionalStudent.isEmpty()) {
      var newStudent = StudentEntity.builder()
        .email(dto.email())
        .build();
      student = studentRepository.save(newStudent);
    } else {
      student = optionalStudent.get();
    }

    var questionsEntity = questionRepository.findByTechnology(dto.technology());

    AtomicInteger correctAnswers = new AtomicInteger(0);
    List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
    dto.questionsAnswers().stream().forEach(questionAnswer -> {
      var question = questionsEntity.stream()
        .filter(questionEntity -> questionEntity.getId().equals(questionAnswer.questionID()))
        .findFirst().get();
      
      var findCorrectAlternative = question.getAlternatives().stream()
        .filter(alternative -> alternative.isCorrect()).findFirst().get();

      if (findCorrectAlternative.getId().equals(questionAnswer.alternativeID())) {
        var answersCertificationsEntity = AnswersCertificationsEntity.builder()
          .answerId(questionAnswer.alternativeID())
          .questionId(questionAnswer.questionID())
          .studentId(student.getId())
          .isCorrect(true)
          .build();
        correctAnswers.incrementAndGet();
        answersCertifications.add(answersCertificationsEntity);
      } else {
        var answersCertificationsEntity = AnswersCertificationsEntity.builder()
          .answerId(questionAnswer.alternativeID())
          .questionId(questionAnswer.questionID())
          .studentId(student.getId())
          .isCorrect(false)
          .build();
        answersCertifications.add(answersCertificationsEntity);
      }
    });

    var certificationStudentEntity = CertificationStudentEntity.builder()
      .technology(dto.technology())
      .student(student)
      .grade(correctAnswers.get())
      .build();
    var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

    answersCertifications.stream().forEach(answerCertification -> {
      answerCertification.setCertificationId(certificationStudentCreated.getId());
    });

    certificationStudentEntity.setAnswers(answersCertifications);
    var certificationStudentUpdated = certificationStudentRepository.save(certificationStudentEntity);

    return certificationStudentUpdated;
  }

}
