package com.aula126.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aula126.project.models.Contact;
import com.aula126.project.models.Group;
import com.aula126.project.models.User;
import com.aula126.project.repositories.ContactRepository;
import com.aula126.project.repositories.GroupRepository;
import com.aula126.project.repositories.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ContactRepository contactRepository;


    //localhost:8080/api/groups - POST
    @PostMapping("/groups")
    //Documentação do Swagger
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "OK"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Criando novo grupo", consumes = "application/json", produces = "application/json")

    public ResponseEntity<Group> createGroup(@RequestBody Group group){

        Group _group = groupRepository.save(group);

        return new ResponseEntity<Group>(_group, HttpStatus.OK);
    }

    @PostMapping("/groups/{id}/contacts/")
    //localhost:8080/api/groups/1/contacts/?contactId=1
    public ResponseEntity<?> insertUserToGroup(@RequestParam String contactId, @PathVariable("id") long id){

        Group _group = groupRepository.getById(id);

        System.out.println("-----------------------------\n" +  _group.getName() +"\n-----------------------------");

        Contact _contact = contactRepository.getById(id);

        System.out.println("-----------------------------\n" +  _contact.getName() +"\n-----------------------------");

        List<Group> _groups = new ArrayList<Group>();

        _groups.add(_group);

        _contact.setGroups(_groups);

        return new ResponseEntity<>(_group, HttpStatus.OK);
    }
    
}
