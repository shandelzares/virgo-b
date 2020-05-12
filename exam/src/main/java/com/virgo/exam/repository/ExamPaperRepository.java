package com.virgo.exam.repository;

import com.virgo.exam.model.ExamPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExamPaperRepository extends JpaRepository<ExamPaper,Long> , JpaSpecificationExecutor<ExamPaper> {
}
