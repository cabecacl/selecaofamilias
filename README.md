<h1 align="center"> Seleção de Familias </h1>

<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
<img src="http://img.shields.io/static/v1?label=framework&message=SPRITN%20BOOT&color=GREEN&style=for-the-badge"/>
</p>


Projeto de desafio técnico. Consiste na seleção de famílias para ganhar uma casa popular do governo, tendo assim uma pontuação por critérios predefinidos.

Tecnologias

O projeto é uma Api Rest, desenvolvida com as seguintes tecnologias/frameworks:
Java
Spring Boot
Lombok
JUnit
Spring Data JPA
Flywaydb
Spring Doc

Estrutura

Mantendo o padrão de camadas e organização foram criadas as seguintes camadas:
controle
dominio
infra

Controle: Nesta camada fica centralizado os controles que disponibilizam os verbos rest para realização dos processos de cadastro de família e listagem por pontuação.
Domínio: O domínio tem as classes de mapeamento para o banco de dados, como também, as classes de transferência de dados pela API (classes records) e as classes de validação/regras.
Infra: Aqui está as configurações de documentação para swagger e tratamentos de erros gerais

OBS: Com a utilização do Flywaydb é necessário criar uma pasta para armazenar as migrations(arquivos de evolução do banco de dados)


Documentação

A documentação da api foi realizada pela Open Api, onde disponibiliza o swagger como interface de documentação e execução.


Testes

Os testes foram focados em dois pontos, o primeiro foi o cadastro de família com seus dependentes e o segundo na validação de regras de pontuação.
Não foram realizados testes unitários para classes de domínio, pois, os testes de cadastro no banco de dados e recuperação desses dados, informa a integridade das classes mapeadas e seus relacionamentos.

Conclusão

O desafio aparentemente simples, exigiu um nível de complexidade maior que o analisado inicialmente, tendo tanto regras de preenchimento quanto de interpretação. Fazendo assim a criação de um projeto mais robusto e mais criterioso.
Outro ponto que gostaria de colocar, seria a escolha de fazer testes automatizados, interagindo com o banco de dados real, trazendo uma maior segurança na execução das atividades.


Obrigado pelo desafio.

P.S. ainda existe muita coisa a melhorar, espero que gostem…


