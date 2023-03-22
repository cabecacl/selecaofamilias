package com.br.digix.selecaofamilias.dominio.familia.validacoes;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import com.br.digix.selecaofamilias.dominio.pessoa.RepositorioPessoa;
import com.br.digix.selecaofamilias.infra.erros.ValidacaoException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ValidadorPrenchimentoDependentes implements ValidadorFamilia {

    @Autowired
    private RepositorioPessoa repositorioPessoa;

    public void validar(DadosCadastroFamilia dados) {

        if(dados.listaDependentes().isEmpty()){
            throw new ValidacaoException("É obrigatorio pelo menos um dependente!");
        }

        dados.listaDependentes().forEach(dependente -> {
            if(Strings.isBlank(dependente.dependente().cpf())){
                throw new ValidacaoException("CPF do Dependente não preenchido!");
            }

            if(repositorioPessoa.existsByCpf(dependente.dependente().cpf())){
                throw new ValidacaoException("CPF do Dependente (" + dependente.dependente().cpf() + ") já cadastrado!");
            }
        });

    }

}
