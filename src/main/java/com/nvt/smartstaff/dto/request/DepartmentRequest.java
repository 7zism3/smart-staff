package com.nvt.smartstaff.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nvt.smartstaff.entity.Department} entity
 */
@Data
@NoArgsConstructor
public class DepartmentRequest implements Serializable {

    private String name;

}
