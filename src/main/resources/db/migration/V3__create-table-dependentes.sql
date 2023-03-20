create table dependentes(

    id bigint not null auto_increment,
    pessoa_id bigint not null,
    familia_id bigint not null,

    primary key(id),
    constraint fk_pessoa_id foreign key(pessoa_id) references pessoa(id),
    constraint fk_familia_id foreign key(familia_id) references familia(id)

);