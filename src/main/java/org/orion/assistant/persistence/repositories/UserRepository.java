package org.orion.assistant.persistence.repositories;

import org.orion.assistant.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}