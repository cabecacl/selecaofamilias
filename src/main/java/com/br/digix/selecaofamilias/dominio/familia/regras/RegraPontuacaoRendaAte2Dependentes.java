package com.br.digix.selecaofamilias.dominio.familia.regras;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import org.springframework.stereotype.Component;

@Component
public class RegraPontuacaoRendaAte2Dependentes implements RegraPontuacaoFamilia {
    private static final int PONTUACAO_REGRA = 2;
    private static final int QTD_DEPENDENTES_MAXIMO = 2;
    private static final int QTD_DEPENDENTES_MINIMO = 1;


    @Override
    public int pontuar(DadosCadastroFamilia dados) {

        long qtdDependentesValidos = dados.listaDependentes().stream()
                .filter(dep -> dep.dependente().idade() <= MAIOR_IDADE).count();

        if(qtdDependentesValidos >= QTD_DEPENDENTES_MINIMO && qtdDependentesValidos <= QTD_DEPENDENTES_MAXIMO){
            return PONTUACAO_REGRA;
        }

        return PONTUACAO_DEFAULT;
    }

}
