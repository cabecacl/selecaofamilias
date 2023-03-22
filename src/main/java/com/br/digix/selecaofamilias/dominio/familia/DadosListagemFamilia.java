package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.DadosListagemDependente;
import com.br.digix.selecaofamilias.dominio.pessoa.DadosListagemPessoa;

import java.math.BigDecimal;
import java.util.List;

public record DadosListagemFamilia(Long id, DadosListagemPessoa pai, DadosListagemPessoa mae,
                                   int pontuacaoFamilia, List<DadosListagemDependente> listaDependentes) {
    public DadosListagemFamilia(Familia familia){
        this(familia.getId(),
                familia.getPai() != null ? new DadosListagemPessoa(familia.getPai()) : null,
                familia.getMae() != null ? new DadosListagemPessoa(familia.getMae()) : null,
                familia.getPontuacaoFamilia(), familia.getListaDependentes().stream().map(DadosListagemDependente::new).toList());
    }
}
