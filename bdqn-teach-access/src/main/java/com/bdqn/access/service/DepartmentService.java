package com.bdqn.access.service;

import com.bdqn.access.repository.DepartmentRepository;
import com.bdqn.access.repository.TeacherRepository;
import com.teach.entity.access.Department;
import com.teach.entity.access.Teacher;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import com.teach.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("all")
public class DepartmentService {

    @Autowired(required = false)
    private DepartmentRepository departmentRepository;

    @Autowired(required = false)
    private TeacherRepository teacherRepository;
    @Autowired(required = false)
    private IdWorker idWorker;


    public Result findAll() {
        List<Department> all = departmentRepository.findAll();
        return  new Result(ResultCode.SUCCESS,all);
    }


    public Result removeDepartment(String id) {
        List<Teacher> list = teacherRepository.findByDepartmentId(id);

        if(list != null && list.size() > 0) return new Result(ResultCode.DEPARTMENT_HAS_USERS);
        departmentRepository.deleteById(id);
        return Result.SUCCESS();
    }


    public Result saveDepartment(Department department) {
        department.setId(idWorker.nextId()+"");
        departmentRepository.save(department);
        return Result.SUCCESS();
    }

    public Result updateDepartment(Department department) {
        Department target = departmentRepository.findById(department.getId()).get();
        BeanUtils.copyProperties(department,target);
        departmentRepository.save(target);
        return Result.SUCCESS();
    }
}
