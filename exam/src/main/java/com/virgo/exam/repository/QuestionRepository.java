package com.virgo.exam.repository;

import com.virgo.exam.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<Question,Long> , JpaSpecificationExecutor<Question> {
}
