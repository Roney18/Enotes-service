package com.roney.Enotes_Springboot.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseClass {
    private Boolean isActive;
    private Boolean isDeleted;
    private int createdBy;
    private Date createdDate;
    private int updateBy;
    private Date updatedDate;
}
