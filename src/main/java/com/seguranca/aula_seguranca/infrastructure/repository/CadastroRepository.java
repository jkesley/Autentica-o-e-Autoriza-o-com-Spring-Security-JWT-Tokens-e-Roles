package com.seguranca.aula_seguranca.infrastructure.repository;


import com.seguranca.aula_seguranca.infrastructure.entitys.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    // JpaRepository já fornece métodos como save(), findAll(), findById()...
}
