package com.feibai.spring.study.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mazhq
 * @Title: UserAuthEntity
 * @ProjectName: zeus
 * @Description: TODO
 * @date 2019/7/30 16:41
 */
@Entity
@Data
@Table(name = "USER_AUTH", uniqueConstraints = {@UniqueConstraint(name = "USER_AUTH_PHONE", columnNames = {"PHONE"}),
@UniqueConstraint(name = "USER_AUTH_EMAIL", columnNames = {"EMAIL"})})
public class UserAuthEntity implements Serializable {
    private static final long serialVersionUID = 7230052310725727465L;
    @Id
    private Long userId;
    @Column(name = "PHONE", length = 16)
    private String phone;
    @Column(name = "EMAIL", length = 16)
    private String email;
    private String password;
    @Column(name = "REMARK",length = 16)
    private String remark;
    @Column(name = "ADD_DATE", nullable = false, columnDefinition = "datetime default now()")
    private Date addDate;
}
