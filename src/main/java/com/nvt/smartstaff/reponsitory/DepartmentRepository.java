package com.nvt.smartstaff.reponsitory;

import com.nvt.smartstaff.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Slice<Department> findAllByName(String name, Pageable pageable);

}
