package com.sarathe.expense.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Persistent {

    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    @Column(name = "created_timestamp")
    private LocalDateTime createdTs;

    @LastModifiedDate
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTs;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy ;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
