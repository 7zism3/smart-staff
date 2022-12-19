package com.nvt.smartstaff.controller;

import com.nvt.smartstaff.dto.request.DepartmentRequest;
import com.nvt.smartstaff.dto.response.DepartmentResponse;
import com.nvt.smartstaff.service.DepartmentService;
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
@RequestMapping("/api/v1/department")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated DepartmentRequest departmentRequest) {
        departmentService.save(departmentRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable("id") Long id) {
        DepartmentResponse departmentResponse = departmentService.findById(id);
        return ResponseEntity.ok(departmentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<DepartmentResponse>> pageQuery(DepartmentRequest departmentRequest, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DepartmentResponse> departmentResponsePage = departmentService.findByCondition(departmentRequest, pageable);
        return ResponseEntity.ok(departmentResponsePage);
    }

//    @GetMapping("/find-all-page")
//    public ResponseEntity<List<DepartmentResponse>> findAllPage(@PageableDefault(sort = "id",
//            direction = Sort.Direction.DESC) Pageable pageable) {
//        //List<DepartmentResponse> departmentResponsePage = departmentService.findAllPage(pageable);
//        return ResponseEntity.ok(departmentResponsePage);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated DepartmentRequest departmentRequest, @PathVariable("id") Long id) {
        departmentService.update(departmentRequest, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update2/{id}")
    public ResponseEntity<Void> updatePatch(@RequestBody @Validated DepartmentRequest departmentRequest, @PathVariable("id") Long id) {
        departmentService.updatePatch(departmentRequest, id);
        return ResponseEntity.ok().build();
    }
    // ------------------------------------------------------------------------------------------------------------- //

    @GetMapping("")
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        List<DepartmentResponse> list = departmentService.findAll();
        return ResponseEntity.ok(list);
    }


}
