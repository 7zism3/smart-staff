package com.nvt.smartstaff.mapper;

import com.nvt.smartstaff.dto.request.DepartmentRequest;
import com.nvt.smartstaff.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentRequestMapper extends EntityMapper<DepartmentRequest, Department> {
}