package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.pessoa.Pessoa;
import com.br.digix.selecaofamilias.dominio.dependente.Dependente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "familia")
@Entity(name = "Familia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_pai_id")
    private Pessoa pai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_mae_id")
    private Pessoa mae;

    private int pontuacaoFamilia;

    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Dependente> listaDependentes;

}
