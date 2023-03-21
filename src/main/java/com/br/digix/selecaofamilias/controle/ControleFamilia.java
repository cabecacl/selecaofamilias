package com.br.digix.selecaofamilias.controle;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import com.br.digix.selecaofamilias.dominio.familia.DadosListagemFamilia;
import com.br.digix.selecaofamilias.dominio.familia.Familia;
import com.br.digix.selecaofamilias.dominio.familia.RepositorioFamilia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/familia")
public class ControleFamilia {

    @Autowired
    private RepositorioFamilia repositorioFamilia;

    @GetMapping
    public ResponseEntity<Page<DadosListagemFamilia>> listar(@PageableDefault(size = 100, sort = {"pontuacaoFamilia"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = repositorioFamilia.findAll(paginacao).map(DadosListagemFamilia::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarFamilia(@RequestBody @Valid DadosCadastroFamilia dados, UriComponentsBuilder uriBuilder) {
        var familia = new Familia(dados);
        repositorioFamilia.save(familia);

        var uri = uriBuilder.path("/familia/{id}").buildAndExpand(familia.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemFamilia(familia));
    }

}