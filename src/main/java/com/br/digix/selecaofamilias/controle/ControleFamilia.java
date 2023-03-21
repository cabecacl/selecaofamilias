package com.br.digix.selecaofamilias.controle;

import com.br.digix.selecaofamilias.dominio.familia.DadosListagemFamilia;
import com.br.digix.selecaofamilias.dominio.familia.Familia;
import com.br.digix.selecaofamilias.dominio.familia.RepositorioFamilia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
