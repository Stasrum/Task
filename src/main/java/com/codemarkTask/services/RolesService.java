package com.codemarkTask.services;

import com.codemarkTask.entities.Role;
import com.codemarkTask.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Role> getRolesByIds(List<Long> ids) {
        return rolesRepository.findAllById(ids);
    }
}
