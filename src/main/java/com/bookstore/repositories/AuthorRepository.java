package com.bookstore.repositories;

import com.bookstore.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

    Optional findByName(String name);
}
