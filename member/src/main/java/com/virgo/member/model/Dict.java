package com.virgo.member.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Dict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;

    private Type type;

    private String value;
    private FormatType formatType;

    @CreatedBy
    private String creator;
    @LastModifiedBy
    private String revisor;
    private LocalDateTime updateTime;
    @CreatedDate
    private LocalDateTime createTime;

//    @OneToMany
//    private List<DictItem> dictItems;

    @Version
    private Long version;

    public static enum Type {
        SYSTEM, BUSINESS
    }

    public static enum FormatType {
        /**
         * 字符串
         */
        STRING,
        /**
         * boolean 类型， true,false
         */
        BOOLEAN,
        /**
         * json、map类型
         * [
         * {
         * "value": "",
         * "sort": 12,
         * "name": "",
         * "desc": "",
         * "label": "",
         * "enable": true
         * }
         * ]
         */
        JSON,
        /**
         * list 类型
         * ["1","3"]
         */
        LIST
    }
}
