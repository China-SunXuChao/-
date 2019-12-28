package com.bdqn.contacts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teach.entity.contacts.Company;
import com.teach.entity.contacts.ContactVo;
import com.teach.entity.contacts.Man;
import com.teach.entity.contacts.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContactsMapper extends BaseMapper<Man> {

    @Select("SELECT count(*) \n" +
            "FROM `company` c,`man` m,`type` t,`pe_user` u \n" +
            "WHERE m.`companyOid` = c.`cId` AND m.`manTypeId` = t.`tId` AND m.`userId` = u.id\n" +
            "AND u.id = #{id} ")
    long getTotal(String id);

    @Select("SELECT m.*,c.*,t.* \n" +
            "FROM `company` c,`man` m,`type` t,`pe_user` u \n" +
            "WHERE m.`companyOid` = c.`cId` AND m.`manTypeId` = t.`tId` AND m.`userId` = u.id\n" +
            "AND u.id = #{id} limit #{i},#{size} ")
    List<ContactVo> getListByUsers( int i,Integer size, String id);

    @Select("select * from company")
    List<Company> selectCompany();
    @Select("select * from type")
    List<Type> selectType();
}
