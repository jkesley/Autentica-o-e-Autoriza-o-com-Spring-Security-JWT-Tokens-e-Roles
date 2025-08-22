package com.seguranca.aula_seguranca.controller;


import com.seguranca.aula_seguranca.busines_service.CadastroService;
import com.seguranca.aula_seguranca.infrastructure.entitys.Cadastro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    private final CadastroService cadastroService; // 🔹 apenas uma variável

    // Construtor para injeção de dependência
    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    // Endpoint POST para salvar um novo cadastro via JSON
    @PostMapping
    public ResponseEntity<Cadastro> salvar(@RequestBody Cadastro cadastro) {
        Cadastro salvo = cadastroService.salvar(cadastro);
        return ResponseEntity.ok(salvo);
    }

    // Endpoint GET para listar todos os cadastros
    @GetMapping
    public ResponseEntity<List<Cadastro>> listarTodos() {
        List<Cadastro> lista = cadastroService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Endpoint GET para cadastro rápido via URL (para navegador)
    @GetMapping("/add")
    public String addCadastro(@RequestParam String nome,
                              @RequestParam Integer idade) {
        Cadastro c = new Cadastro();
        c.setNome(nome);
        c.setIdade(idade);
        cadastroService.salvar(c);
        return "Cadastro salvo: " + c.getNome() + ", idade: " + c.getIdade();
    }
}
