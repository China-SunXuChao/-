package com.bdqn.contacts.service.impl;

import com.bdqn.contacts.mapper.ContactsMapper;
import com.bdqn.contacts.service.ContactsService;
import com.teach.entity.contacts.Company;
import com.teach.entity.contacts.ContactVo;
import com.teach.entity.contacts.Man;
import com.teach.entity.contacts.Type;
import com.teach.response.PageResult;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {

    @Autowired(required = false)
    private ContactsMapper contactsMapper;


    @Override
    public Result getListByUsers(Map<String, Object> map) {
        String id = (String) map.get("id");
        long total = contactsMapper.getTotal(id);
        Integer page = Integer.parseInt(map.get("page").toString());
        Integer size = Integer.parseInt(map.get("size").toString());
        List<ContactVo> list = contactsMapper.getListByUsers((page-1) * size,size,id);
        PageResult<ContactVo> contactVoPageResult = new PageResult<>(total,list);
        return new Result(ResultCode.SUCCESS,contactVoPageResult);
    }

    @Override
    public Result getCopmanyData() {
        List<Company> list = contactsMapper.selectCompany();
        return new Result(ResultCode.SUCCESS,list);
    }

    @Override
    public Result getTypeData() {
        List<Type> list = contactsMapper.selectType();
        return new Result(ResultCode.SUCCESS,list);
    }

    @Override
    public Result saveContacts(ContactVo contactVo) {
        Man man = new Man();
        man.setCompanyoid(contactVo.getCompanyOid());
        man.setHot(contactVo.getHot());
        man.setManaddress(contactVo.getManAddress());
        man.setManstation(contactVo.getManStation());
        man.setManname(contactVo.getManName());
        man.setMantel(contactVo.getManTel());
        man.setMantypeid(contactVo.getTId());
        man.setCompanyoid(contactVo.getCId());
        man.setOperatedatetime(new Date());
        man.setUserid(contactVo.getUserId());
        man.setOperatename("sunxuchao");
        contactsMapper.insert(man);
        return Result.SUCCESS();
    }

    @Override
    public Result updateContacts(ContactVo contactVo) {
        Man man = new Man();
        man.setSerialid(contactVo.getSerialID());
        man.setCompanyoid(contactVo.getCompanyOid());
        man.setHot(contactVo.getHot());
        man.setManaddress(contactVo.getManAddress());
        man.setManstation(contactVo.getManStation());
        man.setManname(contactVo.getManName());
        man.setMantel(contactVo.getManTel());
        man.setMantypeid(contactVo.getTId());
        man.setCompanyoid(contactVo.getCId());
        man.setOperatedatetime(new Date());
        man.setUserid(contactVo.getUserId());
        man.setOperatename("sunxuchao");
        Man man1 = contactsMapper.selectById(man.getSerialid());
        BeanUtils.copyProperties(man,man1);
        contactsMapper.updateById(man);
        return Result.SUCCESS();
    }

    @Override
    public Result deleteContacts(ContactVo contactVo) {
        contactsMapper.deleteById(contactVo.getSerialID());
        return Result.SUCCESS();
    }


}
