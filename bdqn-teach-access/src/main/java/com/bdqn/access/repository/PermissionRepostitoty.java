package com.bdqn.access.repository;

import com.teach.entity.access.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepostitoty extends JpaRepository<Permission,String>, JpaSpecificationExecutor<Permission> {

    @Query(nativeQuery = true,value = "SELECT COUNT(r.id) FROM pe_role r,pe_permission p ,pe_role_permission rp WHERE p.id = rp.permission_id AND rp.role_id = r.id AND p.id = ?")
    int getRolesByPermissionId(String id);

    List<Permission> findByPidAndType(String pid, Integer type);
}
