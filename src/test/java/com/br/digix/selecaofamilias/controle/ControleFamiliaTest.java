package com.br.digix.selecaofamilias.controle;

import com.br.digix.selecaofamilias.dominio.dependente.DadosCadastroDependente;
import com.br.digix.selecaofamilias.dominio.familia.*;
import com.br.digix.selecaofamilias.dominio.familia.regras.RegraPontuacaoRendaAte2Dependentes;
import com.br.digix.selecaofamilias.dominio.familia.regras.RegraPontuacaoRendaMaisDe3Dependentes;
import com.br.digix.selecaofamilias.dominio.familia.regras.RegraPontuacaoRendaTotalAte900;
import com.br.digix.selecaofamilias.dominio.familia.regras.RegraPontuacaoRendaTotaleEntre901E1500;
import com.br.digix.selecaofamilias.dominio.pessoa.DadosCadastroPessoa;
import com.br.digix.selecaofamilias.dominio.pessoa.TipoPessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ControleFamiliaTest {

    @Autowired
    private RegraPontuacaoRendaTotalAte900 regraPontuacaoRendaTotalAte900;

    @Autowired
    private RegraPontuacaoRendaTotaleEntre901E1500 regraPontuacaoRendaTotaleEntre901E1500;

    @Autowired
    private RegraPontuacaoRendaMaisDe3Dependentes regraPontuacaoRendaMaisDe3Dependentes;

    @Autowired
    private RegraPontuacaoRendaAte2Dependentes regraPontuacaoRendaAte2Dependentes;

    @Test
    @DisplayName("Teste de regra de renda ate 900")
    void regraRendaTotalAte900() {

        DadosCadastroFamilia dadosFamilia = getDadosCadastroFamiliaPadrao(new BigDecimal(300), new BigDecimal(600));

        int pontuacao = regraPontuacaoRendaTotalAte900.pontuar(dadosFamilia);

        assertThat(pontuacao == 5);

    }

    @Test
    @DisplayName("Teste de regra de renda entre 901 e 1500")
    void regraRendaTotalEntre901e1500() {

        DadosCadastroFamilia dadosFamilia = getDadosCadastroFamiliaPadrao(new BigDecimal(900), new BigDecimal(600));

        int pontuacao = regraPontuacaoRendaTotaleEntre901E1500.pontuar(dadosFamilia);

        assertThat(pontuacao == 3);

    }

    @Test
    @DisplayName("Teste de regra para numero de dependentes 3 ou mais")
    void regraMaisDe3Dependentes() {

        DadosCadastroFamilia dadosFamilia = getDadosCadastroFamiliaMais3Dependentes(new BigDecimal(0), new BigDecimal(0));

        int pontuacao = regraPontuacaoRendaMaisDe3Dependentes.pontuar(dadosFamilia);

        assertThat(pontuacao == 3);

    }

    @Test
    @DisplayName("Teste de regra para numero de dependentes ate 2")
    void regraAte2Dependentes() {

        DadosCadastroFamilia dadosFamilia = getDadosCadastroFamiliaPadrao(new BigDecimal(0), new BigDecimal(0));

        int pontuacao = regraPontuacaoRendaAte2Dependentes.pontuar(dadosFamilia);

        assertThat(pontuacao == 2);

    }

    private static DadosCadastroFamilia getDadosCadastroFamiliaPadrao(BigDecimal rendaPai, BigDecimal rendaMae) {
        var dadosPai = new DadosCadastroPessoa("Cleiton de Aguiar","111.111.111-11",38, TipoPessoa.PAI,
                    rendaPai , null, null );
        var dadosMae = new DadosCadastroPessoa("Lisanary Paes","222.222.222-22",40, TipoPessoa.MAE,
                    rendaMae , null, null );
        var dadosDependente1 = new DadosCadastroPessoa("Humberto Paes","333.333.333-33",11, TipoPessoa.DEPENDENTE,
                null , null, null );
        var dadosDependente2 = new DadosCadastroPessoa("2Berto Paes","444.444.444-44",19, TipoPessoa.DEPENDENTE,
                null , null, null );

        List<DadosCadastroDependente> dadosListaDependentes = new ArrayList<DadosCadastroDependente>();
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente1));
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente2));

        return new DadosCadastroFamilia(dadosPai, dadosMae, dadosListaDependentes);
    }

    private static DadosCadastroFamilia getDadosCadastroFamiliaMais3Dependentes(BigDecimal rendaPai, BigDecimal rendaMae) {
        var dadosPai = new DadosCadastroPessoa("Cleiton de Aguiar","111.111.111-11",38, TipoPessoa.PAI,
                rendaPai , null, null );
        var dadosMae = new DadosCadastroPessoa("Lisanary Paes","222.222.222-22",40, TipoPessoa.MAE,
                rendaMae , null, null );
        var dadosDependente1 = new DadosCadastroPessoa("Humberto Paes","333.333.333-33",11, TipoPessoa.DEPENDENTE,
                null , null, null );
        var dadosDependente2 = new DadosCadastroPessoa("2Berto Paes","444.444.444-44",19, TipoPessoa.DEPENDENTE,
                null , null, null );
        var dadosDependente3 = new DadosCadastroPessoa("3Berto Paes","555.555.555-55",15, TipoPessoa.DEPENDENTE,
                null , null, null );

        List<DadosCadastroDependente> dadosListaDependentes = new ArrayList<DadosCadastroDependente>();
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente1));
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente2));
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente3));

        return new DadosCadastroFamilia(dadosPai, dadosMae, dadosListaDependentes);
    }

    private DadosCadastroFamilia dadosCadastroFamilia(DadosCadastroPessoa pai,
                                                      DadosCadastroPessoa mae,
                                                      List<DadosCadastroDependente> dependentes){
        return new DadosCadastroFamilia(pai, mae, dependentes);
    }

    private DadosCadastroPessoa dadosCadastroPessoa(String nome,
                                                    String cpf,
                                                    int idade,
                                                    TipoPessoa tipoPessoa,
                                                    BigDecimal renda,
                                                    String email,
                                                    String telefone){
        return new DadosCadastroPessoa(nome, cpf, idade, tipoPessoa, renda, email, telefone );
    }

    private DadosCadastroDependente dadosCadastroDependente(DadosCadastroPessoa dependente){
        return new DadosCadastroDependente(dependente);
    }

}