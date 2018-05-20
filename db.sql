spool db.log
grant all privileges to tor identified by root;
conn tor/root

set LINESIZE 32000;
set PAGESIZE 40000;
set LONG 50000;

DROP TABLE carritos cascade constraint;
DROP TABLE productos cascade constraint;
DROP TABLE usuarios cascade constraint;

CREATE TABLE productos(
	id int,
	nombre varchar(30),
	imageUri varchar(30),
	precio float,
	cantidad int,
	CONSTRAINT pkproductos PRIMARY KEY(id)
);

CREATE TABLE usuarios(
	id int,
	nombre varchar(30),
	imageUri varchar(30),
	email varchar(50),
	password varchar(30),
	tipo int,
	CONSTRAINT pkusuarios PRIMARY KEY(id)
);

CREATE TABLE carritos(
	usuario int,
	producto int,
	cantidad int, 
	CONSTRAINT pkcarritos PRIMARY KEY(usuario, producto),
	CONSTRAINT fkcarritos1 FOREIGN KEY (usuario) REFERENCES usuarios(id),
	CONSTRAINT fkcarritos2 FOREIGN KEY (producto) REFERENCES productos(id)
);
--CURSOR
create or replace package types
as
type ref_cursor is ref cursor;
end;
/

--PROCEDIMIENTO PARA INSERTAR PRODUCTOS
create or replace procedure insertarProducto(PId in int, PNombre in varchar, PImage in varchar,
PPrecio in float, PCantidad in int) is
begin
insert into productos(id,nombre,imageUri,precio,cantidad) 
values (PId,PNombre,PImage,PPrecio,PCantidad); 
commit;
end insertarProducto;
/
show error

--PROCEDIMIENTO PARA MODIFICAR PRODUCTOS
create or replace procedure modificarProductos(PId in int, PNombre in varchar, PImage in varchar,
PPrecio in float, PCantidad in int) is
begin 
update productos
set id=PId, nombre=PNombre,imageUri=PImage,
precio=PPrecio, cantidad=PCantidad
where id=PId;
END modificarProductos;
/
show error

--PROCEDIMIENTO PARA ELIMIAR PRODUCTOS
create or replace procedure eliminarProductos(PId in int) is
begin 
delete 
from productos
where id=PId;
end eliminarProductos;
/
show error 

--FUNCION PARA MOSTRAR TODOS LOS PRODUCTOS
create or replace function listaProducto
return Types.ref_cursor
as
producto_cursor types.ref_cursor;
begin
open producto_cursor for
select p.id, p.nombre, p.imageUri,
p.precio, p.cantidad
from productos p;
return producto_cursor;
end listaProducto;
/
show error

--FUNCION PARA BUSCAR UN PRODUCTO POR ID
create or replace function buscarProducto(PId in int)
return Types.ref_cursor
as
producto_cursor types.ref_cursor;
begin 
open producto_cursor for 
select p.id, p.nombre, p.imageUri,
p.precio, p.cantidad
from productos p
where id=PId;
return producto_cursor;
end buscarProducto;
/
show error

--PROCEDIMIENTO PARA INSERTAR USUARIOS
create or replace procedure insertarUsuario(PId in int, PNombre in varchar, PImage in varchar,
PEmail in varchar, PPassword in varchar, PTipo in int) is
begin
insert into usuarios(id,nombre,imageUri,email,password,tipo)
values(PId,PNombre,PImage,PEmail,PPassword,PTipo);
commit;
end insertarUsuario;
/
show error 

--PROCEDIMIENTO PARA MODIFICAR USUARIOS
create or replace procedure modificarUsuarios(PId in int, PNombre in varchar, PImage in varchar,
PEmail in varchar, PPassword in varchar, PTipo in int) is
begin 
update usuarios
set id=PId, nombre=PNombre, imageUri=PImage,
email=PEmail, password=PPassword,tipo=PTipo
where id=PId;
commit;
end modificarUsuarios;
/
show error

--PROCEDIMIENTO PARA ELIMIAR USUARIOS
create or replace procedure eliminarUsuarios(PId in int) is
begin 
delete 
from usuarios
where id=PId;
end eliminarUsuarios;
/
show error

--FUNCION PARA MOSTRAR USUARIOS
create or replace function listaUsuario
return Types.ref_cursor
as
usuario_cursor types.ref_cursor;
begin 
open usuario_cursor for
select u.id,u.nombre,u.imageUri,u.email,u.password,u.tipo 
from usuarios u;
return usuario_cursor;
end listaUsuario;
/
show error

--FUNCION PARA BUSCAR USUARIOS POR NOMBRE
create or replace function buscarUsuario(PId in int)
return Types.ref_cursor
as
usuario_cursor types.ref_cursor;
begin 
open usuario_cursor for 
select u.id, u.nombre, u.imageUri,
u.email, u.password, u.tipo
from usuarios u
where id=PId;
return usuario_cursor;
end buscarUsuario;
/
show error

--PROCEDIMIENTO PARA INSERTAR CARRITOS
create or replace procedure insertarCarrito(PUsuario in int, PProducto in int, PCantidad in int) is
begin
insert into carritos(usuario,producto,cantidad)
values (PUsuario,PProducto,PCantidad);
commit;
end;
/
show error

--PROCEDIMIENTO PARA MODIFICAR CARRITOS
create or replace procedure modificarCarrito(PUsuario in int, PProducto in int, PCantidad in int) is
begin
update carritos
set usuario=PUsuario, producto=PProducto, cantidad=PCantidad
where usuario=PUsuario;
end modificarCarrito;
/
show error

--PROCEDIMIENTO PARA ELIMINAR CARRITOS
create or replace procedure eliminarCarrito(PUsuario in int) is
begin 
delete 
from carritos 
where usuario=PUsuario;
end eliminarCarrito;
/
show error

--FUNCION PARA LISTAR CARRITOS
create or replace function listaCarrito
return Types.ref_cursor
as
carrito_cursor types.ref_cursor;
begin 
open carrito_cursor for
select c.usuario, c.producto, c.cantidad
from carritos c;
return carrito_cursor;
end listaCarrito;
/
show error

--FUNCION PARA BUSCAR CARRITO
create or replace function buscarCarrito(PUsuario in int)
return Types.ref_cursor
as
carrito_cursor types.ref_cursor;
begin 
open carrito_cursor for 
select c.usuario,c.producto, c.cantidad
from carritos c
where usuario=PUsuario;
return carrito_cursor;
end buscarCarrito;
/
show error

--INSERTANDO USUARIOS
exec insertarUsuario(813156487,'Angel Yvanes Gerardo','persona1','angel.yvanes.gerardo@una.ac.cr','angel',1);
exec insertarUsuario(908069482,'Carlos Asencio Ysidro','persona2','carlos.asencio.ysidro@una.ac.cr','carlos',1);
exec insertarUsuario(118510669,'Emiliano Sepulbeda Troche','persona3','emiliano.sepulbeda.troche@una.ac.cr','emiliano',1);
exec insertarUsuario(494658212,'Santino Peredo Dongu','persona4','santino.peredo.dongu@una.ac.cr','santino',1);
exec insertarUsuario(673424513,'Tomas Velasco Clemente','persona5','tomas.velasco.clemente@una.ac.cr','tomas',1);
exec insertarUsuario(876415060,'Benjamin Covos Brusiaga','persona6','benjamin.covos.brusiaga@una.ac.cr','benjamin',1);
exec insertarUsuario(984357664,'Gael Mancilla Moia','persona7','gael.mancilla.moia@una.ac.cr','gael',1);
exec insertarUsuario(964465378,'Emmanuel Canchola Espejo','persona8','emmanuel.canchola.espejo@una.ac.cr','emmanuel',1);
exec insertarUsuario(769438762,'Dylan Montecillo Balderas','persona9','dylan.montecillo.balderas@una.ac.cr','dylan',1);
exec insertarUsuario(368377663,'Emiliano Aguado Sifuentes','persona10','emiliano.aguado.sifuentes@una.ac.cr','emiliano',1);
exec insertarUsuario(304830405,'Esteban Montero Fonseca','esteban','esteban.montero.fonseca@est.una.ac.cr','esteban',0);
exec insertarUsuario(114830575,'Kevin Calderon Rodriguez','kevin','kevin.calderon.rodriguez@est.una.ac.cr','kevin',0);
exec insertarUsuario(604140420,'Adrian prendas Araya','adrian','adrian.prendas.araya@est.una.ac.cr','adrian',0);

--INSERTA PRODUCTOS
exec insertarProducto(0,'Laptop MSI','laptop_msi',1000,10);
exec insertarProducto(1,'Desktop MSI','desktop_msi',2000,5);
exec insertarProducto(2,'Teclado Mecanico Rayzer','teclado_mecanico_rayzer',40,20);
exec insertarProducto(3,'Mouse Rayzer','mouse_rayzer',20,20);
exec insertarProducto(4,'Desktop Alienware','desktop_alienware',500,3);
exec insertarProducto(5,'Laptop Alienware','laptop_alienware',1500,4);
exec insertarProducto(6,'Router DLINK','router_dlink',30,8);
exec insertarProducto(7,'Router LINKSYS','router_linksys',35,10);
exec insertarProducto(8,'Parlantes THONET VANDER','parlantes',80,5);
exec insertarProducto(9,'Joystick de palanca','joystick_palanca',25,5);
exec insertarProducto(10,'Joystick de xbox','joystick_xbox',30,10);
exec insertarProducto(11,'Joystick de xbox 360','joystick_xbox360',25,7);
exec insertarProducto(12,'Joystick de play 3','joystick_play3',30,3);

--INSERTA CARRITOS
exec insertarCarrito(304830405,1,2);
exec insertarCarrito(604140420,2,3);

select listaProducto from dual;
select listaUsuario from dual;
select listaCarrito from dual;

select buscarProducto(1) from dual;
select buscarUsuario(304830405) from dual;
select buscarCarrito(304830405) from dual;