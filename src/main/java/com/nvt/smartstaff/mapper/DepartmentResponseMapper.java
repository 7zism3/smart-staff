package com.nvt.smartstaff.mapper;

import com.nvt.smartstaff.dto.response.DepartmentResponse;
import com.nvt.smartstaff.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentResponseMapper extends EntityMapper<DepartmentResponse, Department> {
}