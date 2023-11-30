package org.vlad.springcourse.Library21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.repositories.AdministratorsRepository;

@Service
public class RegistrationService {
    private  final AdministratorsRepository administratorsRepository;
    @Autowired
    public RegistrationService(AdministratorsRepository administratorsRepository) {
        this.administratorsRepository = administratorsRepository;
    }


    // Происходит изменение в БД, поэтому используем аннотацию @Transactional
    @Transactional
    public void register(Administrator administrator) {
        administratorsRepository.save(administrator);
    }
}
