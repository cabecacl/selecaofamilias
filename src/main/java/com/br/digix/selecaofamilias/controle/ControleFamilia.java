package com.br.digix.selecaofamilias.controle;

import com.br.digix.selecaofamilias.dominio.familia.*;
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
    @Autowired
    private ServiceFamilia serviceFamilia;

    @GetMapping
    public ResponseEntity<Page<DadosListagemFamilia>> listarFamiliasPontuacao(@PageableDefault(size = 100, sort = {"pontuacaoFamilia"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = repositorioFamilia.findAll(paginacao).map(DadosListagemFamilia::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarFamilia(@RequestBody @Valid DadosCadastroFamilia dados, UriComponentsBuilder uriBuilder) {

        var familia =  serviceFamilia.cadastrarFamilia(dados);

        var uri = uriBuilder.path("/familia/{id}").buildAndExpand(familia.id()).toUri();

        return ResponseEntity.created(uri).body(familia);
    }

}
