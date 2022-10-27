package com.nvt.smartstaff.service;


import com.nvt.smartstaff.dto.request.DepartmentRequest;
import com.nvt.smartstaff.dto.response.DepartmentResponse;
import com.nvt.smartstaff.entity.Department;
import com.nvt.smartstaff.mapper.DepartmentRequestMapper;
import com.nvt.smartstaff.mapper.DepartmentResponseMapper;
import com.nvt.smartstaff.reponsitory.DepartmentRepository;
import com.nvt.smartstaff.utils.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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
        //List<Department> entities2 = repository.findAllByName("test_3452273aee7b", pageable);

        Page<Department> entityPage = repository.findAll(pageable);
        Slice<Department> entityPage3 = repository.findAllByName("test_3452273aee7b", pageable);
        List<Department> entities = entityPage.getContent();
        PageImpl temp = new PageImpl<>(departmentResponseMapper.toDto(entities), pageable, entityPage.getTotalElements());
        return temp;
    }

    public List<DepartmentResponse> findAllPage(Pageable pageable, String[] aStr) {
        Page<Department> entityPage = repository.findAll(pageable);
        List<Department> entities = entityPage.getContent();
        List<String> data = new ArrayList<>();
        String str = "";
        // = {"a", "b", "c"};
        int a = aStr.length;
        if (a == 1) {
            data.add(str);
            data.add(str);
            data.addAll(Arrays.asList(aStr));
        }
        if (a == 2) {
            data.add(str);
            data.addAll(Arrays.asList(aStr));
        }
        if (a == 3) {
            data.addAll(Arrays.asList(aStr));
        }
        if (a > 3) {
            data.add(str);
            data.add(str);
            data.addAll(Arrays.asList(aStr));
        }

        return departmentResponseMapper.toDto(entities);
    }

    public DepartmentResponse update(DepartmentRequest departmentRequest, Long id) {
        Department data = repository.findById(id).get();
        BeanUtils.copyProperties(departmentRequest, data);
        return departmentResponseMapper.toDto(repository.save(data));
    }

    public DepartmentResponse updatePatch(DepartmentRequest departmentRequest, Long id) {
        Department data = repository.findById(id).get();
        BeanUtils.copyProperties(departmentRequest, data, BeanUtil.getNullPropertyNames(departmentRequest));
        return departmentResponseMapper.toDto(repository.save(data));
    }

    // ------------------------------------------------------------------------------------------------------------- //

    public List<DepartmentResponse> findAll() {
        List<Department> list = repository.findAll();
        if (CollectionUtils.isEmpty(list))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có phòng ban nào");
        return departmentResponseMapper.toDto(list);
    }


}