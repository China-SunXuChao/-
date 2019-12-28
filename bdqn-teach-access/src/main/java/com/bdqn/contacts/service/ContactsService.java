package com.bdqn.contacts.service;

import com.teach.entity.contacts.ContactVo;
import com.teach.response.Result;

import java.util.Map;

public interface ContactsService {
    Result getListByUsers(Map<String, Object> map);

    Result getCopmanyData();

    Result getTypeData();

    Result saveContacts(ContactVo contactVo);

    Result updateContacts(ContactVo contactVo);

    Result deleteContacts(ContactVo contactVo);
}
