package com.br.digix.selecaofamilias.dominio;

import com.br.digix.selecaofamilias.dominio.dependente.DadosCadastroDependente;
import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import com.br.digix.selecaofamilias.dominio.familia.Familia;
import com.br.digix.selecaofamilias.dominio.familia.RepositorioFamilia;
import com.br.digix.selecaofamilias.dominio.pessoa.DadosCadastroPessoa;
import com.br.digix.selecaofamilias.dominio.pessoa.TipoPessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RepositorioFamiliaTest {

    @Autowired
    private RepositorioFamilia repositorioFamilia;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve cadastrar uma familia com dependentes na base de dados")
    void cadastrarFamilia() {

        //Preenchimento
        var dadosPai = new DadosCadastroPessoa("Cleiton de Aguiar","111.111.111-11",38, TipoPessoa.PAI,
                new BigDecimal(1200) , null, null );
        var dadosMae = new DadosCadastroPessoa("Lisanary Paes","222.222.222-22",40, TipoPessoa.MAE,
                new BigDecimal(300) , null, null );
        var dadosDependente1 = new DadosCadastroPessoa("Humberto Paes","333.333.333-33",11, TipoPessoa.DEPENDENTE,
                null , null, null );
        var dadosDependente2 = new DadosCadastroPessoa("2Berto Paes","444.444.444-44",19, TipoPessoa.DEPENDENTE,
                null , null, null );

        List<DadosCadastroDependente> dadosListaDependentes = new ArrayList<DadosCadastroDependente>();
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente1));
        dadosListaDependentes.add(new DadosCadastroDependente(dadosDependente2));

        var dadosFamilia = new DadosCadastroFamilia(dadosPai, dadosMae, dadosListaDependentes);

        //Ação
        var familia = cadastrarFamilia(dadosFamilia, 7);

        List<Familia> listaRetorno = repositorioFamilia.findAll().stream().toList();

        assertTrue(!listaRetorno.isEmpty());

        var familiaRetorno = listaRetorno.get(0);

        assertThat(familia).isEqualTo(familiaRetorno);

    }

    @Test
    @DisplayName("Deve cadastrar uma familia com dependentes na base de dados e retornar a consulta com a pontuação do maior primeiro")
    void listarFamiliasPorPortuacao() {

        //Preenchimento
        var dadosPai1 = new DadosCadastroPessoa("Cleiton de Aguiar","111.111.111-11",38, TipoPessoa.PAI,
                new BigDecimal(1200) , null, null );
        var dadosMae1 = new DadosCadastroPessoa("Lisanary Paes","222.222.222-22",40, TipoPessoa.MAE,
                new BigDecimal(300) , null, null );
        var dadosDependente1 = new DadosCadastroPessoa("Humberto Paes","333.333.333-33",11, TipoPessoa.DEPENDENTE,
                null , null, null );

        List<DadosCadastroDependente> dadosListaDependentes1 = new ArrayList<DadosCadastroDependente>();
        dadosListaDependentes1.add(new DadosCadastroDependente(dadosDependente1));

        var dadosFamilia1 = new DadosCadastroFamilia(dadosPai1, dadosMae1, dadosListaDependentes1);


        var dadosPai2 = new DadosCadastroPessoa("Jonas Arraujo","444.444.444-44",38, TipoPessoa.PAI,
                new BigDecimal(600) , null, null );
        var dadosMae2 = new DadosCadastroPessoa("Horanna Alves","555.555.555-55",40, TipoPessoa.MAE,
                new BigDecimal(300) , null, null );
        var dadosDependente2 = new DadosCadastroPessoa("Tulio Arraujo Alves","666.666.666-66",10, TipoPessoa.DEPENDENTE,
                null , null, null );

        List<DadosCadastroDependente> dadosListaDependentes2 = new ArrayList<DadosCadastroDependente>();
        dadosListaDependentes2.add(new DadosCadastroDependente(dadosDependente2));

        var dadosFamilia2 = new DadosCadastroFamilia(dadosPai2, dadosMae2, dadosListaDependentes2);

        //Ação
        var familia1 = cadastrarFamilia(dadosFamilia1, 5);
        var familia2 = cadastrarFamilia(dadosFamilia2, 7);

        Pageable page = PageRequest.of(0, 2, Sort.by("pontuacaoFamilia").descending());

        List<Familia> listaRetorno = repositorioFamilia.findAll(page).stream().toList();

        assertTrue(!listaRetorno.isEmpty());

        var familiaRetorno = listaRetorno.get(0);

        assertThat(familia2).isEqualTo(familiaRetorno);

    }

    private Familia cadastrarFamilia(DadosCadastroFamilia dadosCadastroFamilia, int pontuacaoFamilia) {
        var familia = new Familia(dadosCadastroFamilia);
        familia.setPontuacaoFamilia(pontuacaoFamilia);
        em.persist(familia);
        return familia;
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
