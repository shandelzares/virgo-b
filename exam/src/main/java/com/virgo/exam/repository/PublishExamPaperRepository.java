package com.virgo.exam.repository;

import com.virgo.exam.model.PublishExamPaper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublishExamPaperRepository extends MongoRepository<PublishExamPaper, String>{

}
