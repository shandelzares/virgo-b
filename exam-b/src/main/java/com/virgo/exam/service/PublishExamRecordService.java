package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import com.virgo.exam.repository.ExamPaperRecordRepository;
import com.virgo.exam.vo.ExamRecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublishExamRecordService {
    @Resource
    private ExamPaperRecordRepository examPaperRecordRepository;

    public List<ExamRecordVO> findByPublishExamPaperId(String publishExamPaperId) {
        return examPaperRecordRepository.findByPublishExamPaperId(publishExamPaperId).stream().map(examRecord -> {
            return BeanUtil.copyProperties(examRecord, ExamRecordVO.class);
        }).collect(Collectors.toList());
    }
}
