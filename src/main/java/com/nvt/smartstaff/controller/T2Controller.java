package com.nvt.smartstaff.controller;

import com.nvt.smartstaff.service.T2Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nvt.smartstaff.dto.t2.Root;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/t2")
@RestController
public class T2Controller {

    private final T2Service t2Service;

    @GetMapping("")
    public ResponseEntity<Root [] > getRequest() {
        Root [] roots = t2Service.getRequest();
        return ResponseEntity.ok(roots);
    }

    @GetMapping("/v2")
    public ResponseEntity<Root [] > getRequest2() {
        Root [] roots = t2Service.getRequest2();
        return ResponseEntity.ok(roots);
    }

    @GetMapping("/v3")
    public ResponseEntity<List<Root>> getRequest3() {
        List<Root> roots = t2Service.getRequest3();
        return ResponseEntity.ok(roots);
    }
}
