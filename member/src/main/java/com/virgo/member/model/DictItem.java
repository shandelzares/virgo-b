//package com.virgo.member.model;
//
//import lombok.Data;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//
//@Data
//@Entity
//@Table
//@EntityListeners(AuditingEntityListener.class)
//public class DictItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String code;
//    private String name;
//    private String desc;
//
//    private Integer sort;
//    private String value;
//    private Type type;
//
//    public static enum Type {
//        STRING, JSON
//    }
//}
