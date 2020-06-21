package com.virgo.member.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(indexes = {@Index(columnList = "memberId", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String phone;
    private String username;
    private String nickName;
    private String name;
    private Sex sex;
    private LocalDate birthday;
    private String password;
    private String idCard;
    private String invitationCode;
    private Status status;
    private Boolean deleted;
    @CreatedBy
    private String creator;
    @LastModifiedBy
    private String revisor;
    private LocalDateTime updateTime;
    @CreatedDate
    private LocalDateTime createTime;
    @Version
    private Long version;

    private String avatarUrl;//头像地址

    private Type type;
    /**
     * 所属组织，默认享有组织所属的数据权限
     */
    private Long organizationId;

    /**
     * 享有数据权限
     */
    @ManyToMany
    private List<Organization> dataAccess;

    @ManyToMany
    private List<Role> roles;

    private String companyCode;

    public enum Status {
        REGULAR,
        LOCKED,
    }

    public enum Type {
        SUPER_ADMIN, ADMIN, TEACHER, TEACHING_ASSISTANT, STUDENTS, VISITOR, MEMBER, VIP, S_VIP
    }

    public enum Sex {
        MAN, WOMAN
    }
}
