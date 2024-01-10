package com.pncbank.registration.repository;

import com.pncbank.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link User} entities in the database.
 * Extends the Spring Data JPA interface
 * providing CRUD operations and additional query methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
