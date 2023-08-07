package org.vlad.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vlad.springcourse.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
