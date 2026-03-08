create table cursos(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    categoria varchar(100) not null,

    primary key(id)

);

create table perfis(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,

    primary key(id)

);

create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    primary key(id)

);

create table usuarios_perfis(

    usuario_id bigint not null,
    perfil_id bigint not null,
    primary key(usuario_id, perfil_id),
    foreign key(usuario_id) references usuarios(id),
    foreign key(perfil_id) references perfis(id)

);

create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(255) not null,
    data_criacao date not null,
    status varchar(100) not null,
    curso_id bigint not null,
    autor_id bigint not null,

    primary key(id),
    foreign key(autor_id) references usuarios(id),
    foreign key(curso_id) references cursos(id)

);

create table respostas(

    id bigint not null auto_increment,
    mensagem varchar(200) not null,
    data_criacao date not null,
    topico_id bigint not null,
    autor_id bigint not null,
    solucao tinyint not null,

    primary key(id),
    foreign key(topico_id) references topicos(id),
    foreign key(autor_id) references usuarios(id)

);