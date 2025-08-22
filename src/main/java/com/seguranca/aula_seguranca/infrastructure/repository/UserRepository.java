package com.seguranca.aula_seguranca.infrastructure.repository;

import com.seguranca.aula_seguranca.infrastructure.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
