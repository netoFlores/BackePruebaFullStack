CREATE
DATABASE db_logistica;
CREATE
USER 'logistica'@'%' IDENTIFIED BY 'super$eguro040322$';
GRANT ALL PRIVILEGES ON db_logistica . * TO
'logistica'@'%';

use
prueba_1;

CREATE TABLE db_logistica.tc_pais
(
    id_tc_pais  int not null primary key auto_increment,
    descripcion char(100),
    enable      boolean
);


CREATE TABLE db_logistica.tc_tipo_bodega
(
    id_tc_tipo_bodega int not null primary key auto_increment,
    descripcion       char(50),
    enable            boolean
);


CREATE TABLE db_logistica.tc_tipo_producto
(
    id_tc_tipo_producto int not null primary key auto_increment,
    descripcion         char(50),
    enable              boolean
);


CREATE TABLE db_logistica.ts_bodega
(
    id_ts_bodega      int not null primary key auto_increment,
    id_tc_tipo_bodega int not null,
    id_tc_pais        int not null,
    descripcion       char(50),
    direccion         char(100),
    enable            boolean
);

ALTER TABLE db_logistica.ts_bodega
    ADD CONSTRAINT fk_tcTipobodega_tsbodega FOREIGN KEY (id_tc_tipo_bodega) REFERENCES db_logistica.tc_tipo_bodega (id_tc_tipo_bodega);
ALTER TABLE db_logistica.ts_bodega
    ADD CONSTRAINT fk_tcpais_tsbodega FOREIGN KEY (id_tc_pais) REFERENCES db_logistica.tc_pais (id_tc_pais);

CREATE TABLE db_logistica.ts_usuario
(
    id_ts_usuario int       not null primary key auto_increment,
    nombre        char(100) not null,
    apellido      char(100) not null,
    email         char(100) not null,
    clave         char(100) not null,
    direccion     char(100) not null,
    contacto      char(12),
    enable        boolean
);

CREATE TABLE db_logistica.tt_order_logistica
(
    id_tt_order_logistica int            not null primary key auto_increment,
    id_tc_tipo_producto   int            not null,
    id_ts_usuario         int            not null,
    cantidad_producto     int            not null,
    fecha_registro        date           not null,
    fecha_entrega         date           not null,
    id_ts_bodega          int            not null,
    precio_envio          decimal(10, 2) not null,
    precio_descuento      decimal(10, 2),
    porcentaje_descuento  int,
    placa                 varchar(8)     not null,
    numero_guia           varchar(10)    not null
);

ALTER TABLE db_logistica.tt_order_logistica
    ADD CONSTRAINT fK_tcTipoProducto_ttOrderLogistica FOREIGN KEY (id_tc_tipo_producto) REFERENCES db_logistica.tc_tipo_producto (id_tc_tipo_producto);
ALTER TABLE db_logistica.tt_order_logistica
    ADD CONSTRAINT fk_tsusuario_ttorderlogistica FOREIGN KEY (id_ts_usuario) REFERENCES db_logistica.ts_usuario (id_ts_usuario);
ALTER TABLE db_logistica.tt_order_logistica
    ADD CONSTRAINT fk_tsbodega_ttorderlogistica FOREIGN KEY (id_ts_bodega) REFERENCES db_logistica.ts_bodega (id_ts_bodega);



create table db_logistica.roles(
                                   id int not null primary key auto_increment,
                                   name varchar(20)
);

create table db_logistica.user_roles(
                                        user_id int not null,
                                        role_id int not null
);

INSERT INTO db_logistica.roles(name) VALUES('ROLE_USER');
INSERT INTO db_logistica.roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO db_logistica.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO db_logistica.ts_usuario
VALUES (null, 'Mario',	'Flores',	'em@fl.com',	'$2a$10$VcdzH8Q.o4KEo6df.XesdOmXdXQwT5ugNQvu1Pl0390rmfOeA1bhS',	'ajja',	'naa',	1);

INSERT INTO db_logistica.user_roles VALUES (1,1);
commit;