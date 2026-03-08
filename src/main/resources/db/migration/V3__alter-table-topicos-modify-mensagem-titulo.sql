alter table topicos modify column mensagem varchar(255) not null unique;
alter table topicos modify column titulo varchar(100) not null unique;
