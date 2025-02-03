package com.akila.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class InitializationInfo {
    public static class ColumnName
    {

        private ColumnName()
        {
        }

        public static final String CREATED_DATE = "created_date";
    }

    @CreatedDate
    @Column(name = ColumnName.CREATED_DATE, updatable = false)
    private Timestamp createdDate;

}
