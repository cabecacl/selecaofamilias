package com.br.digix.selecaofamilias.dominio.pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record DadosCadastroPessoa(@NotBlank String nome,
                                  @NotBlank @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
                                  String cpf,
                                  @NotBlank int idade,
                                  @NotBlank TipoPessoa tipoPessoa,
                                  BigDecimal renda,
                                  @Email String email,
                                  @Pattern(regexp = "\\(\\d{2}\\) \\d{5} \\d{4}")
                                  String telefone) {

}
