package com.nvt.smartstaff.mapper;

import com.nvt.smartstaff.dto.response.EmployeeResponse;
import com.nvt.smartstaff.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.crypto.Data;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public abstract class EmployeeResponseMapper implements EntityMapper<EmployeeResponse, Employee> {

    @Override
    @Mapping(target = "age", source = "birthday")
    public abstract EmployeeResponse toDto(Employee entity);

    Integer mapAge(LocalDate birthday) {
        int age = LocalDate.now().getYear() - birthday.getYear();
        return age;
    }
}