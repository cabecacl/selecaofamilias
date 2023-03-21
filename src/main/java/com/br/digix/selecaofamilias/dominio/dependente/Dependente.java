package com.br.digix.selecaofamilias.dominio.dependente;

import com.br.digix.selecaofamilias.dominio.familia.Familia;
import com.br.digix.selecaofamilias.dominio.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "dependentes")
@Entity(name = "Dependentes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private Pessoa dependente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "familia_id", nullable = false)
    private Familia familia;

    public Dependente(DadosCadastroDependente dependente, Familia familia) {
        this.dependente = new Pessoa(dependente.dependente());
        this.familia = familia;
    }
}
