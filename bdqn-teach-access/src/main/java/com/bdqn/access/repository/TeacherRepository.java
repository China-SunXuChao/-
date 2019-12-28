package com.bdqn.access.repository;

import com.teach.entity.access.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,String>, JpaSpecificationExecutor<Teacher> {

    List<Teacher> findByDepartmentId(String departmentId);
}
