package com.virgo.exam.repository;

import com.virgo.exam.model.PublishExamPaper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PublishExamPaperRepository extends MongoRepository<PublishExamPaper, String>{

    List<PublishExamPaper> findByExamPaperId(String id);

}
