package org.vlad.springcourse.Library21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.repositories.AdministratorsRepository;

import java.util.Optional;

@Service
public class AdministratorsService {
    private final AdministratorsRepository administratorsRepository;
    @Autowired
    public AdministratorsService(AdministratorsRepository administratorsRepository) {
        this.administratorsRepository = administratorsRepository;
    }

    public Optional<Administrator> findByUsername(String userName) {
        return administratorsRepository.findByUsername(userName);
    }
}
