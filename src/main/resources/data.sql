INSERT INTO TB_USUARIO_CLIENTE(cd_usuario_cliente, nm_usuario_cliente, vl_email_cliente, tx_senha_cliente, ds_localizacao_cliente, ds_tipo_usuario) VALUES (SQ_USUARIO.nextval, 'Pedro', 'email@email', '322', 'aqui', 0);
INSERT INTO TB_USUARIO_COMERCIAL(cd_usuario_comercial, nm_usuario_comercial, nr_cnpj, tx_senha, ds_tipo_usuario, ds_horario_funcionamento, fl_foto, ds_cep, nm_contato, nr_telefone, ds_email) VALUES (SQ_USUARIO_COMERCIAL.nextval, 'PedroPetShop', '3re3', '21desete', 1, '8hrs', 'http.pic', '0349-33', 'PedroNe', '11545', 'pedroehfoda@mail.com');
INSERT INTO TB_USUARIO_COMERCIAL(cd_usuario_comercial, nm_usuario_comercial, nr_cnpj, tx_senha, ds_tipo_usuario, ds_horario_funcionamento, fl_foto, ds_cep, nm_contato, nr_telefone, ds_email) VALUES (SQ_USUARIO_COMERCIAL.nextval, 'PedroPetShop', '3re3', '21desete', 1, '8hrs', 'http.pic', '0349-33', 'PedroNe', '11545', 'troll@mail.com');
INSERT INTO TB_PRODUTO(cd_produto, ft_produto, nm_produto, ds_produto, ds_preco, ds_preco_antigo, ds_tipo_produto, ds_tipo_animal, ds_tipo_fisico, cd_usuario_comercial) VALUES (SQ_PRODUTO.nextval, 'https://i.imgur.com/cV7o5lD.png', 'Comida para cães1', 'Pro Plan Optiderma Sensitive Skin Alimento Seco para Perro adulto razas mediana/grande receta Salmón y Arroz', 32.3, 39, 'FISICO', 'CACHORRO', 'COMIDA', 1);
INSERT INTO TB_PRODUTO(cd_produto, ft_produto, nm_produto, ds_produto, ds_preco, ds_preco_antigo, ds_tipo_produto, ds_tipo_animal, ds_tipo_fisico, cd_usuario_comercial) VALUES (SQ_PRODUTO.nextval, 'https://i.imgur.com/cV7o5lD.png', 'Comida para cães2', 'Pro Plan Optiderma Sensitive Skin Alimento Seco para Perro adulto razas mediana/grande receta Salmón y Arroz', 45.3, 49, 'FISICO', 'CACHORRO', 'COMIDA', 1);
INSERT INTO TB_PRODUTO(cd_produto, ft_produto, nm_produto, ds_produto, ds_preco, ds_preco_antigo, ds_tipo_produto, ds_tipo_animal, ds_tipo_fisico, cd_usuario_comercial) VALUES (SQ_PRODUTO.nextval, 'https://i.imgur.com/cV7o5lD.png', 'Comida para cães3', 'Pro Plan Optiderma Sensitive Skin Alimento Seco para Perro adulto razas mediana/grande receta Salmón y Arroz', 12.0, 23.5, 'FISICO', 'CACHORRO', 'COMIDA', 1);
INSERT INTO TB_USUARIO_ADMIN(cd_usuario_admin, ds_email, tx_senha, ds_role) VALUES (SQ_USUARIO_ADMIN.nextval, 'thandyadmin@hotmail.com', '$2a$10$D2pdSJ.Ne8voho8bCd01k.CN94.8JgScVFCFPSr9KLCZRX60MzelG', 3);
INSERT INTO TB_USUARIO_COLAB(cd_usuario_colab, ds_email, tx_senha, ds_role) VALUES (SQ_USUARIO_COLAB.nextval, 'thandycolab@hotmail.com', '$2a$10$GqN0ZGaWsdpfV78VsmgkEespwwwYUVZgIu2I6w6MdwEZgCHkoHylK', 4);