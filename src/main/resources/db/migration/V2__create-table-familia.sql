create table familia(

    id bigint not null auto_increment,
    pessoa_pai_id bigint null,
    pessoa_mae_id bigint null,
    pontuacao_familia int not null default 0,

    primary key(id),
    constraint fk_pessoa_pai_id foreign key(pessoa_pai_id) references pessoa(id),
    constraint fk_pessoa_mae_id foreign key(pessoa_mae_id) references pessoa(id)

);