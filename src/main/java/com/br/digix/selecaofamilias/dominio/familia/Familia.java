package com.br.digix.selecaofamilias.dominio.familia;

import com.br.digix.selecaofamilias.dominio.dependente.DadosCadastroDependente;
import com.br.digix.selecaofamilias.dominio.pessoa.Pessoa;
import com.br.digix.selecaofamilias.dominio.dependente.Dependente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Set;

@Table(name = "familia")
@Entity(name = "Familia")
@Getter
@Setter
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
        if(dados.pai() != null && Strings.isBlank(dados.pai().cpf())){
            this.pai = null;
        }else{
            this.pai = new Pessoa(dados.pai());
        }

        if(dados.mae() != null && Strings.isBlank(dados.mae().cpf())){
            this.mae = null;
        }else{
            this.mae = new Pessoa(dados.mae());
        }

        this.listaDependentes = dados.listaDependentes().stream().map(dependente -> new Dependente(dependente, this)).toList();
    }
}
