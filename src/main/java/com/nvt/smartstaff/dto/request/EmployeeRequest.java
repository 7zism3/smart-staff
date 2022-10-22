package com.nvt.smartstaff.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.nvt.smartstaff.entity.Department} entity
 */
@Data
@NoArgsConstructor
public class EmployeeRequest implements Serializable {

    private String name;
    private LocalDate birthday;

}
