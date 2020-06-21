package com.virgo.exam.repository;

import com.virgo.exam.model.ExamPaper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamPaperRepository extends MongoRepository<ExamPaper, String> {
}
