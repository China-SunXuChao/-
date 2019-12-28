package com.bdqn.contacts.controller;

import com.bdqn.contacts.service.ContactsService;
import com.sun.org.apache.regexp.internal.RE;
import com.teach.entity.contacts.ContactVo;
import com.teach.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired(required = false)
    private ContactsService contactsService;


    @RequestMapping(value = "/getListByUsers",method = RequestMethod.POST)
    public Result getListByUsers(@RequestBody Map<String,Object> map){
        return contactsService.getListByUsers(map);
    }

    @RequestMapping(value = "/getCopmanyData",method = RequestMethod.GET)
    public Result getCopmanyData(){
        return contactsService.getCopmanyData();
    }


    @RequestMapping(value = "/getTypeData",method = RequestMethod.GET)
    public Result getTypeData(){
        return contactsService.getTypeData();
    }


    @RequestMapping(value = "/saveContacts",method = RequestMethod.POST)
    public Result saveContacts(@RequestBody ContactVo contactVo){
        return contactsService.saveContacts(contactVo);
    }

    @RequestMapping(value = "/updateContacts",method = RequestMethod.PUT)
    public Result updateContacts(@RequestBody ContactVo contactVo){
        return contactsService.updateContacts(contactVo);
    }

    @RequestMapping(value = "/deleteContacts",method = RequestMethod.DELETE)
    public Result deleteContacts(@RequestBody ContactVo contactVo){
        return contactsService.deleteContacts(contactVo);
    }




}
