package com.nvt.smartstaff.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nvt.smartstaff.entity.Department} entity
 */
@Data
@NoArgsConstructor
public class EmployeeResponse implements Serializable {
    private String name;
    private Integer age;
}
