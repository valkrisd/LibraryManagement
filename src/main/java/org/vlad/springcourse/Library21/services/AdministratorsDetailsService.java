package org.vlad.springcourse.Library21.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vlad.springcourse.Library21.models.Administrator;
import org.vlad.springcourse.Library21.repositories.AdministratorsRepository;
import org.vlad.springcourse.Library21.security.AdministratorDetails;

import java.util.Optional;

@Service
// Имплементация UserDetailsService нужна чтобы сократить количество кода в будущем
public class AdministratorsDetailsService implements UserDetailsService {

    private final AdministratorsRepository administratorsRepository;

    public AdministratorsDetailsService(AdministratorsRepository administratorsRepository) {
        this.administratorsRepository = administratorsRepository;
    }

    // загрузка пользователя по имени пользователя, полученного из формы
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administrator> administrator = administratorsRepository.findByUsername(username);
        if (administrator.isEmpty()) throw new UsernameNotFoundException("User not found!");

        return new AdministratorDetails(administrator.get());
    }
}
