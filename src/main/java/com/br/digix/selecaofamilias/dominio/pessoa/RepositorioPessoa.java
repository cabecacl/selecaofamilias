package com.br.digix.selecaofamilias.dominio.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPessoa extends JpaRepository<Pessoa, Long> {

    boolean existsByCpf(String cpf);

}
