package com.br.digix.selecaofamilias.dominio.dependente;

import com.br.digix.selecaofamilias.dominio.pessoa.DadosCadastroPessoa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDependente(@NotNull DadosCadastroPessoa dependente) {
}
