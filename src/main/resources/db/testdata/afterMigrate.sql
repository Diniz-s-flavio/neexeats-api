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
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;
delete from foto_produto;

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
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into kitchen (nome) values ('Japonesa');
insert into kitchen (nome) values ('Mexicana');

insert into estado (nome) values ('Goiás');
insert into estado (nome) values ('São Paulo');

insert into cidade (nome, estado_id) values ('Orizona',1);
insert into cidade (nome, estado_id) values ('São Paulo',2);

insert into restaurante (nome, taxa_frete, kitchen_id,data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro,ativo,aberto) values ('Ichiraku',3.50,1, utc_timestamp, utc_timestamp,1,'75280000','rua 1','N/A','Centro',true,true);
insert into restaurante (nome, taxa_frete, kitchen_id,data_cadastro, data_atualizacao,ativo,aberto) values ('Macarena',5.50,2,utc_timestamp,utc_timestamp,true,true);

insert into forma_pag (descricao) values ('Cartão');
insert into forma_pag (descricao) values ('Pix');

insert into permissao (nome,descricao) values ('Comprar','Comprar um prato do menu');
insert into permissao (nome,descricao) values ('Cadastrar prato','Adicionar um novo prato ao menu');
insert into permissao (nome, descricao) values ( 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ( 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pag (restaurante_id, forma_pag_id) values (1, 1), (1, 2), (2, 1), (2, 2);

insert into produto (nome, restaurante_id, descricao, preco, ativo) values ('Roscovo', 1, 'Arroz com Ovo', 10, 0);
insert into produto (nome, restaurante_id, descricao, preco, ativo) values ('Yakisoba', 1, 'testes vlangas', 10, 1);
insert into produto (nome, restaurante_id, descricao, preco, ativo) values ('Pave', 1, 'e Pave ou Pacume', 10, 0);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into grupo_permissoes (grupo_id, permissao_id) values (1, 1), (1, 2), (1,3), (1,4), (2, 1), (2, 2), (3, 1);

insert into usuario (nome,email,senha,data_cadastro) values ('Flávio Diniz de Sousa','ajayf4r@gmail.com','senha',utc_timestamp);
insert into usuario (nome,email,senha,data_cadastro) values ('Jars','josejarsbr@gmail.com','senha',utc_timestamp);
insert into usuario (nome,email,senha,data_cadastro) values ('Jão GayGriel','joaogabriel120103@gmail.com','senha',utc_timestamp);
insert into usuario (nome,email,senha,data_cadastro) values ('Filipe Canedo','hiroto444@email.com','senha',utc_timestamp);

insert into usuario_grupo (usuario_id,grupo_id) values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (4,1), (4,4);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1,1),(2,1),(2,2),(1,3);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pag_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, "2acdda8a-be91-48b7-8f72-64a1486417b1", 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pag_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, "2acdda8a-be91-48b7-8f72-64a1486417b2", 2, 3, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 2, 3, 1, 79, 79, 'Ao ponto');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pag_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (3, "2acdda8a-be91-48b7-8f72-64a1486417b3", 1, 2, 2, 2, '38400-000', 'rua 1', '500', 'Apto 801', 'Brasil',
        'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 3, 1, 1, 78.9, 78.9, "vrum vrum");

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 3, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pag_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (4, "2acdda8a-be91-48b7-8f72-64a1486417b4", 1, 2, 2, 2, '38400-000', 'rua 1', '500', 'Apto 801', 'Brasil',
        'CONFIRMADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 4, 1, 1, 78.9, 78.9, "Ran DAn dan");

insert into item_pedido ( pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values ( 4, 2, 2, 110, 220, 'Menos picante, por favor');