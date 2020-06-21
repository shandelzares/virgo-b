package com.virgo.exam.repository;

import com.virgo.exam.model.ExamRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamPaperRecordRepository extends MongoRepository<ExamRecord, String>{

}
