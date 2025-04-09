package ru.vcarstein.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vcarstein.books.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
