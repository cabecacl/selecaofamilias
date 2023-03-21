package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.DadosListagemDependente;
import com.br.digix.selecaofamilias.dominio.pessoa.DadosListagemPessoa;

import java.math.BigDecimal;
import java.util.List;

public record DadosListagemFamilia(Long id, DadosListagemPessoa pai, DadosListagemPessoa mae,
                                   int pontuacaoFamilia, List<DadosListagemDependente> listaDependentes) {
    public DadosListagemFamilia(Familia familia){
        this(familia.getId(), new DadosListagemPessoa(familia.getPai()), new DadosListagemPessoa(familia.getMae()),
                familia.getPontuacaoFamilia(), familia.getListaDependentes().stream().map(DadosListagemDependente::new).toList());
    }
}
