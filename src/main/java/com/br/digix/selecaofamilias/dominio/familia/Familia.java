package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.DadosCadastroDependente;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_pai_id")
    private Pessoa pai;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_mae_id")
    private Pessoa mae;

    private int pontuacaoFamilia;

    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dependente> listaDependentes;

    public Familia(DadosCadastroFamilia dados) {
        this.pai = new Pessoa(dados.pai());
        this.mae = new Pessoa(dados.mae());
        this.listaDependentes = dados.listaDependentes().stream().map(dependente -> new Dependente(dependente, this)).toList();
    }
}
