package com.br.digix.selecaofamilias.dominio.familia.regras;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RegraPontuacaoRendaMaisDe3Dependentes implements RegraPontuacaoFamilia {
    private static final int PONTUACAO_REGRA = 3;
    private static final int QTD_DEPENDENTES = 3;

    @Override
    public int pontuar(DadosCadastroFamilia dados) {

        long qtdDependentesValidos = dados.listaDependentes().stream()
                .filter(dep -> dep.dependente().idade() <= MAIOR_IDADE).count();

        if(qtdDependentesValidos >= QTD_DEPENDENTES){
            return PONTUACAO_REGRA;
        }

        return PONTUACAO_DEFAULT;
    }

}
