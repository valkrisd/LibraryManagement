package org.vlad.springcourse.Library21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.repositories.AdministratorsRepository;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

@Service
public class RegistrationService {
    private  final AdministratorsRepository administratorsRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(AdministratorsRepository administratorsRepository, PasswordEncoder passwordEncoder) {
        this.administratorsRepository = administratorsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Происходит изменение в БД, поэтому используем аннотацию @Transactional
    @Transactional
    public void register(Administrator administrator) {
        // Шифруем пароль пришедший из формы и сохраняем его в БД
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        administratorsRepository.save(administrator);
    }
}
