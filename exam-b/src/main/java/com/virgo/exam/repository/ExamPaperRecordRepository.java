package com.virgo.exam.repository;

import com.virgo.exam.model.ExamRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExamPaperRecordRepository extends MongoRepository<ExamRecord, String>{

    List<ExamRecord> findByPublishExamPaperId(String publishExamPaperId);

}
