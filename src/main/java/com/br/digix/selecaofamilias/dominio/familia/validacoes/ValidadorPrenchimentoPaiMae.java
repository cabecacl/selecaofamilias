package com.br.digix.selecaofamilias.dominio.familia.validacoes;

import com.br.digix.selecaofamilias.dominio.familia.DadosCadastroFamilia;
import com.br.digix.selecaofamilias.dominio.pessoa.RepositorioPessoa;
import com.br.digix.selecaofamilias.infra.erros.ValidacaoException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPrenchimentoPaiMae implements ValidadorFamilia {

    @Autowired
    private RepositorioPessoa repositorioPessoa;

    public void validar(DadosCadastroFamilia dados) {

        if(dados.pai() == null || Strings.isBlank(dados.pai().cpf())){
            if(dados.mae() == null || Strings.isBlank(dados.mae().cpf())){
                throw new ValidacaoException("Um cônjuge deve ser informado(pai ou mãe)!");
            }
        }

        if(dados.pai() != null && Strings.isNotBlank(dados.pai().cpf()) &&
                repositorioPessoa.existsByCpf(dados.pai().cpf())){
            throw new ValidacaoException("CPF do Pai já cadastrado!");
        }

        if(dados.mae() != null && Strings.isNotBlank(dados.mae().cpf()) &&
                repositorioPessoa.existsByCpf(dados.mae().cpf())){
            throw new ValidacaoException("CPF da Mãe já cadastrado!");
        }

    }

}
