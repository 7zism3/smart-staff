package com.nvt.smartstaff.service;


import com.nvt.smartstaff.dto.request.DepartmentRequest;
import com.nvt.smartstaff.dto.response.DepartmentResponse;
import com.nvt.smartstaff.entity.Department;
import com.nvt.smartstaff.mapper.DepartmentRequestMapper;
import com.nvt.smartstaff.mapper.DepartmentResponseMapper;
import com.nvt.smartstaff.reponsitory.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentRequestMapper departmentRequestMapper;
    private final DepartmentResponseMapper departmentResponseMapper;

    public DepartmentResponse save(DepartmentRequest departmentRequest) {
        Department entity = departmentRequestMapper.toEntity(departmentRequest);
        return departmentResponseMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DepartmentResponse findById(Long id) {
        return departmentResponseMapper.toDto(repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có phòng ban này")));
    }

    public Page<DepartmentResponse> findByCondition(DepartmentRequest departmentRequest, Pageable pageable) {
        Department department = departmentRequestMapper.toEntity(departmentRequest);
        Page<Department> entityPage = repository.findAll(pageable);
        List<Department> entities = entityPage.getContent();
        return new PageImpl<>(departmentResponseMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DepartmentResponse update(DepartmentRequest departmentRequest, Long id) {
        Department data = repository.findById(id).get();
        BeanUtils.copyProperties(departmentRequest, data);
        return departmentResponseMapper.toDto(repository.save(data));
    }

    // ------------------------------------------------------------------------------------------------------------- //

    public List<DepartmentResponse> findAll() {
        List<Department> list = repository.findAll();
        if (list.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có phòng ban nào");
        return departmentResponseMapper.toDto(list);
    }


}