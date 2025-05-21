package com.example.FinTrack_Api.controller;

import com.example.FinTrack_Api.dto.request.categoria.*;
import com.example.FinTrack_Api.model.Categoria;
import com.example.FinTrack_Api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroCategoria dados){
        categoriaRepository.save(new Categoria(dados));
    }

    @GetMapping
    public List<DadosListagemCategoria> listar(){
        return categoriaRepository.findByExcluidoFalse()
                .stream().map(DadosListagemCategoria::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizarCategoria dados){
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.atualizarInformacoes(dados);
    }

    @PutMapping("/remover")
    @Transactional
    public void remover(@RequestBody DadosRemoverCategoria dados){
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.remover(dados);
    }

    @PutMapping("/restaurar")
    @Transactional
    public void restaurar(@RequestBody DadosRestaurarCategoria dados){
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.restaurar(dados);
    }
}
