package com.seguranca.aula_seguranca.busines_service;


import com.seguranca.aula_seguranca.infrastructure.entitys.Cadastro;
import com.seguranca.aula_seguranca.infrastructure.repository.CadastroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {

    private final CadastroRepository repository;

    public CadastroService(CadastroRepository repository) {
        this.repository = repository;
    }

    // Salvar um novo cadastro
    public Cadastro salvar(Cadastro cadastro) {
        return repository.save(cadastro);
    }

    // Trazer todos os cadastros
    public List<Cadastro> listarTodos() {
        return repository.findAll();
    }
}
