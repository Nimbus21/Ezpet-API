INSERT INTO TB_USUARIO_COMERCIAL(cd_usuario_comercial, nm_usuario_comercial, ds_tipo_usuario) VALUES (SQ_USUARIO_COMERCIAL.nextval, 'Petshop Pedrao', 'LOJA');
INSERT INTO TB_PRODUTO(cd_produto, ft_produto, nm_produto, ds_produto, ds_preco, ds_tipo_produto, ds_tipo_animal, ds_tipo_fisico, cd_usuario_comercial) VALUES (SQ_PRODUTO.nextval, 'https://i.imgur.com/AvpViOx.png', 'COMIDA MASSA', 'Um excelente produto mano, pode confiar', 32.3, 'FISICO', 'CACHORRO', 'COMIDA', 1);
INSERT INTO TB_PRODUTO(cd_produto, ft_produto, nm_produto, ds_produto, ds_preco, ds_tipo_produto, ds_tipo_animal, ds_tipo_fisico, cd_usuario_comercial) VALUES (SQ_PRODUTO.nextval, 'https://i.imgur.com/AvpViOx.png', 'COMIDA MASSA2', 'Um excelente produto mano, pode confiar!', 323.3, 'FISICO', 'CACHORRO', 'COMIDA', 1);