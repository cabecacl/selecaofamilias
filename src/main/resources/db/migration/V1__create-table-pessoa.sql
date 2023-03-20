create table pessoa(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(14) not null unique,
    idade int not null,
    tipo_pessoa varchar(10) not null,
    renda decimal(15,2) null default '0.00',
    email varchar(100) null,
    telefone varchar(15) null,

    primary key(id)

);