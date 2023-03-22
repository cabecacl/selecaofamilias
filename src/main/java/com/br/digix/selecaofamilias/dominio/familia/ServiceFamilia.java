package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.RepositorioDependente;
import com.br.digix.selecaofamilias.dominio.familia.regras.RegraPontuacaoFamilia;
import com.br.digix.selecaofamilias.dominio.familia.validacoes.ValidadorFamilia;
import com.br.digix.selecaofamilias.dominio.pessoa.RepositorioPessoa;
import com.br.digix.selecaofamilias.infra.erros.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFamilia {
    @Autowired
    private RepositorioPessoa repositorioPessoa;
    @Autowired
    private RepositorioDependente repositorioDependente;
    @Autowired
    private RepositorioFamilia repositorioFamilia;

    @Autowired
    private List<RegraPontuacaoFamilia> regrasPontuacao;

    @Autowired
    private List<ValidadorFamilia> validadores;

    public DadosListagemFamilia cadastrarFamilia(DadosCadastroFamilia dados){

        var pontuacaoTotal = 0;

        validadores.forEach(v -> v.validar(dados));

        Integer pontuacao = regrasPontuacao.stream()
                .map(regra -> regra.pontuar(dados))
                .reduce(0, Integer::sum);

        var familia = new Familia(dados);
        familia.setPontuacaoFamilia(pontuacao);

        repositorioFamilia.save(familia);

        return new DadosListagemFamilia(familia);
    }

}
