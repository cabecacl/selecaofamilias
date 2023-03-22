package com.br.digix.selecaofamilias.dominio.pessoa;

import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;

public record DadosListagemPessoa(Long id, String nome, String cpf, int idade,
                                  TipoPessoa tipoPessoa, BigDecimal renda, String email, String telefone) {
    public DadosListagemPessoa(Pessoa pessoa){
            this(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getIdade(), pessoa.getTipoPessoa(),
                    pessoa.getRenda(), pessoa.getEmail(), pessoa.getTelefone());
    }
}
