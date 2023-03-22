package com.br.digix.selecaofamilias.dominio.familia.regras;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RegraPontuacaoRendaTotaleEntre901E1500 implements RegraPontuacaoFamilia {

    private static final int PONTUACAO_REGRA = 3;
    private static final int VALOR_COMPARACAO_INICIAL = 901;
    private static final int VALOR_COMPARACAO_FINAL = 1500;

    @Override
    public int pontuar(DadosCadastroFamilia dados) {

        BigDecimal rendaTotal = BigDecimal.ZERO;

        if(dados.pai() != null && dados.pai().renda() != null && dados.pai().renda().compareTo(BigDecimal.ZERO) == 1){
            rendaTotal = rendaTotal.add(dados.pai().renda());
        }

        if(dados.mae() != null && dados.mae().renda() != null && dados.mae().renda().compareTo(BigDecimal.ZERO) == 1){
            rendaTotal = rendaTotal.add(dados.mae().renda());
        }

        if(rendaTotal.compareTo(new BigDecimal(VALOR_COMPARACAO_INICIAL)) >= 0 &&
                rendaTotal.compareTo(new BigDecimal(VALOR_COMPARACAO_FINAL)) <= 0){
            return PONTUACAO_REGRA;
        }

        return PONTUACAO_DEFAULT;
    }
    
}
