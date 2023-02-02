create table forma_pag (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
	id bigint not null auto_increment,
	nome varchar(60) not null,

	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table permissao (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	nome varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo_permissoes (
	grupo_id bigint not null,
	permissao_id bigint not null,

	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante (
	id bigint not null auto_increment,
	kitchen_id bigint not null,
	nome varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	data_atualizacao datetime not null,
	data_cadastro datetime not null,

	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),

	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
	id bigint not null auto_increment,
	restaurante_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante_forma_pag (
	restaurante_id bigint not null,
	forma_pag_id bigint not null,

	primary key (restaurante_id, forma_pag_id)
) engine=InnoDB default charset=utf8mb4;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,

	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8mb4;




alter table grupo_permissoes add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissoes add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pag add constraint fk_rest_forma_pagto_forma_pag
foreign key (forma_pag_id) references forma_pag (id);

alter table restaurante_forma_pag add constraint fk_rest_forma_pag_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);