package com.br.digix.selecaofamilias.dominio.dependente;

import com.br.digix.selecaofamilias.dominio.pessoa.DadosListagemPessoa;

public record DadosListagemDependente(Long id, DadosListagemPessoa dependente) {

    public DadosListagemDependente(Dependente dependente){
        this(dependente.getId(), new DadosListagemPessoa(dependente.getDependente()));
    }
}
