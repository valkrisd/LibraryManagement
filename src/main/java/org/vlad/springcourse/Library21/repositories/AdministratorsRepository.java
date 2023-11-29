package org.vlad.springcourse.Library21.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vlad.springcourse.Library21.models.Administrator;

import java.util.Optional;

@Repository
public interface AdministratorsRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByUsername(String username);
}
