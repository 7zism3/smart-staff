package com.nvt.smartstaff.controller;

import com.nvt.smartstaff.dto.request.EmployeeRequest;
import com.nvt.smartstaff.dto.response.EmployeeResponse;
import com.nvt.smartstaff.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated EmployeeRequest employeeRequest) {
        employeeService.save(employeeRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable("id") Long id) {
        EmployeeResponse employeeResponse = employeeService.findById(id);
        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<EmployeeResponse>> pageQuery(EmployeeRequest employeeRequest, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<EmployeeResponse> employeeResponsePage = employeeService.findByCondition(employeeRequest, pageable);
        return ResponseEntity.ok(employeeResponsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated EmployeeRequest employeeRequest, @PathVariable("id") Long id) {
        employeeService.update(employeeRequest, id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------------------------------------------------------------------------- //

    @GetMapping("")
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        List<EmployeeResponse> list = employeeService.findAll();
        return ResponseEntity.ok(list);
    }


}
