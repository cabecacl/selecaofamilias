package com.br.digix.selecaofamilias.dominio.familia.regras;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import com.br.digix.selecaofamilias.dominio.pessoa.RepositorioPessoa;
import com.br.digix.selecaofamilias.infra.erros.ValidacaoException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RegraPontuacaoRendaTotalAte900 implements RegraPontuacaoFamilia {

    private static final int PONTUACAO_REGRA = 5;
    private static final int VALOR_COMPARACAO = 900;


    @Override
    public int pontuar(DadosCadastroFamilia dados) {

        BigDecimal rendaTotal = BigDecimal.ZERO;

        if(dados.pai() != null && dados.pai().renda() != null && dados.pai().renda().compareTo(BigDecimal.ZERO) == 1){
            rendaTotal = rendaTotal.add(dados.pai().renda());
        }

        if(dados.mae() != null && dados.mae().renda() != null && dados.mae().renda().compareTo(BigDecimal.ZERO) == 1){
            rendaTotal = rendaTotal.add(dados.mae().renda());
        }

        if(rendaTotal.compareTo(new BigDecimal(VALOR_COMPARACAO)) <= 0 ){
            return PONTUACAO_REGRA;
        }

        return PONTUACAO_DEFAULT;
    }

}
