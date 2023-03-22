package com.br.digix.selecaofamilias.dominio.familia.regras;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;

public interface RegraPontuacaoFamilia {
    static final int MAIOR_IDADE = 18;
    static final int PONTUACAO_DEFAULT = 0;

    int pontuar(DadosCadastroFamilia dados);

}
