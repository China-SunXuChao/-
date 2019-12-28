package com.teach.response;

import com.teach.entity.access.Permission;
import com.teach.entity.access.Role;
import com.teach.entity.access.User;
import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;

/**
 * 这是返回给前台 权限的类
 *
 *
 * 权限辅助类
 */

@Setter
@Getter
public class ProfileResult implements Serializable, AuthCachePrincipal {

    private String id;
    private String mobile; //telephone
    private String username;
    private String nickName;

    private Map<String,Object> roles = new HashMap<>();

    public ProfileResult(User user, List<Permission> list){
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickName = user.getNickName();

        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Permission permission :list){
            String code = permission.getCode();
            if (permission.getType() == 1){
                menus.add(code);
            }else if (permission.getType() == 2){
                points.add(code);
            }else{
                apis.add(code);
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);
    }

    public ProfileResult(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickName = user.getNickName();

        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Role role : roles){
            Set<Permission> perms = role.getPermissions();
            for (Permission permission:perms){
                String code = permission.getCode();
                if (permission.getType() == 1){
                    menus.add(code); //用户所拥有的菜单权限
                }else if (permission.getType() == 2){
                    points.add(code); //用户所拥有的按钮权限
                }else{
                    apis.add(code);
                }
            }
        }
        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);

    }




    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
