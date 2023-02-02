set foreign_key_checks = 0;

delete from cidade;
delete from kitchen;
delete from estado;
delete from forma_pag;
delete from grupo;
delete from grupo_permissoes;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pag;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pag auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into kitchen (nome) values ('Japonesa');
insert into kitchen (nome) values ('Mexicana');

insert into estado (nome) values ('Goiás');
insert into estado (nome) values ('São Paulo');

insert into cidade (nome, estado_id) values ('Orizona',1);
insert into cidade (nome, estado_id) values ('São Paulo',2);

insert into restaurante (nome, taxa_frete, kitchen_id,data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Ichiraku',3.50,1, utc_timestamp, utc_timestamp,1,'75280000','rua 1','N/A','Centro');
insert into restaurante (nome, taxa_frete, kitchen_id,data_cadastro, data_atualizacao) values ('Macarena',5.50,2,utc_timestamp,utc_timestamp);

insert into forma_pag (descricao) values ('Cartão');
insert into forma_pag (descricao) values ('Pix');

insert into permissao (nome,descricao) values ('Comprar','Comprar um prato do menu');
insert into permissao (nome,descricao) values ('Cadastrar prato','Adicionar um novo prato ao menu');

insert into restaurante_forma_pag (restaurante_id, forma_pag_id) values (1, 1), (1, 2), (2, 1), (2, 2);

insert into produto (nome, restaurante_id, descricao, preco, ativo) values ('Roscovo', 1, 'Arroz com Ovo', 10, 1);
insert into produto (nome, restaurante_id, descricao, preco, ativo) values ('Pave', 1, 'e Pave ou Pacume', 10, 0);