package com.nvt.smartstaff.mapper;

import com.nvt.smartstaff.dto.request.EmployeeRequest;
import com.nvt.smartstaff.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper extends EntityMapper<EmployeeRequest, Employee> {
}