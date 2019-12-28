package com.bdqn.access.repository;

import com.teach.entity.access.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {
}
