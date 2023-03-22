package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.DadosCadastroDependente;
import com.br.digix.selecaofamilias.dominio.pessoa.DadosCadastroPessoa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroFamilia(DadosCadastroPessoa pai,
                                   DadosCadastroPessoa mae,
                                   @NotNull @Valid List<DadosCadastroDependente> listaDependentes) {
}
