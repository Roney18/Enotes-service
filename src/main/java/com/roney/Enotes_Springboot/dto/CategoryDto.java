package com.roney.Enotes_Springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
    private int createdBy;
    private Date createdDate;
    private int updateBy;
    private Date updatedDate;
}
