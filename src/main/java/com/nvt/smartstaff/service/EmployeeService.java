package com.nvt.smartstaff.service;


import com.nvt.smartstaff.dto.request.EmployeeRequest;
import com.nvt.smartstaff.dto.response.EmployeeResponse;
import com.nvt.smartstaff.entity.Employee;
import com.nvt.smartstaff.mapper.EmployeeRequestMapper;
import com.nvt.smartstaff.mapper.EmployeeResponseMapper;
import com.nvt.smartstaff.reponsitory.EmployeeRepository;
import com.nvt.smartstaff.reponsitory.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;

    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee entity = employeeRequestMapper.toEntity(employeeRequest);
        return employeeResponseMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public EmployeeResponse findById(Long id) {
        return employeeResponseMapper.toDto(repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có nhân viên này")));
    }

    public Page<EmployeeResponse> findByCondition(EmployeeRequest employeeRequest, Pageable pageable) {
        Employee employee = employeeRequestMapper.toEntity(employeeRequest);
        Page<Employee> entityPage = repository.findAll(pageable);
        List<Employee> entities = entityPage.getContent();
        return new PageImpl<>(employeeResponseMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public EmployeeResponse update(EmployeeRequest employeeRequest, Long id) {
        Employee data = repository.findById(id).get();
        BeanUtils.copyProperties(employeeRequest, data);
        return employeeResponseMapper.toDto(repository.save(data));
    }

    // ------------------------------------------------------------------------------------------------------------- //

    public List<EmployeeResponse> findAll() {
        List<Employee> list = repository.findAll();

        CollectionUtils.isEmpty(list);
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có nhân viên nào");
        return employeeResponseMapper.toDto(list);
    }


}