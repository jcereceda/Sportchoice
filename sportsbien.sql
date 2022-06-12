-- BASE DE DATOS - PROYECTO INTEGRADOR - SPORTCHOICE

-- Creación de la base de datos
create database Sportchoice character set latin1 collate latin1_spanish_ci;
use Sportchoice;

-- Creación de las tablas
create table usuarios (
	codigo_usuario int AUTO_INCREMENT,
	nombre varchar (30),
	email varchar (30),
	poblacion varchar (30),
	fecha_nacimiento varchar (20),
    passwd varchar(20),
    sexo varchar(10) default "Otro",
	primary key (codigo_usuario),
	unique key (nombre)
)engine=innodb;


create table deportes(
	cod_deporte int,
    nombre varchar(40),
    primary key(cod_deporte)
)Engine = InnoDB;

CREATE TABLE eventos(
	codigoEvento int  auto_increment,
    nombre varchar(30),
	fecha DATE,
	cod_deporte int,
	lugar varchar(40),
    numeroUsuarios int,
    observaciones Text,
    primary key (codigoEvento),
    foreign key (cod_deporte) references deportes(cod_deporte)
)Engine = InnoDb;

CREATE TABLE usuario_eventos(
	cod_usuario int,
    cod_evento int,
	primary key (cod_usuario, cod_evento),
	foreign key (cod_usuario) references usuarios (codigo_usuario),
    foreign key (cod_evento) references eventos (codigoEvento) 
)Engine = InnoDb;

CREATE TABLE foro(
	codigo_foro int,
    codigo_evento int,
    primary key(codigo_foro),
    foreign key (codigo_evento) references eventos (codigoEvento)
)Engine = InnoDB;

CREATE TABLE mensaje (
	cod_mensaje int auto_increment,
    mensaje varchar(200),
    cod_usuario int,
    cod_foro int,
    primary key (cod_mensaje),
    foreign key (cod_usuario) references usuarios (codigo_usuario),
    foreign key (cod_foro) references foro (codigo_foro)
)Engine = InnoDB;

   -- Inserts

INSERT INTO usuarios (codigo_usuario, nombre, email, poblacion, fecha_nacimiento, passwd) 
VALUES 
('1', 'admin', 'admin@sportschoice.com', 'root','2000-01-01','321'),
('2', 'Franklin', 'franki@gmail.com', 'Galapagar','1999-02-21','123'), 
('3', 'Gervasio', 'Gerv@gmail.com', 'Mataro','1997-02-10','123'),  
('4', 'Daniel','dani@gmail.com', 'Torrelodones','2000-03-23','123'),
('5', 'Marcos', 'narkitos@gmail.com', 'Las Matas','2002-06-27','123'), 
('6', 'Javier', 'javimakina@gmail.com', 'Pinto','2001-08-30','123'), 
('7', 'Bryan', 'tubryan@gmail.com', 'Pan Bendito','1996-10-31','123'), 
('8', 'Juan', 'juaneke@gmail.com', 'Villalba','2003-07-22','123'), 
('9', 'Antonio', 'toniskizo@gmail.com', 'Pozuelo','2007-01-28','123'), 
('10', 'Alberto', 'albert@gmail.com', 'Las Rozas','2010-12-27','123');

INSERT INTO deportes (cod_deporte, nombre) 
VALUES 
('1', 'Baloncesto'),
('2', 'Futbol'),
('3', 'Padel');

INSERT INTO eventos (codigoEvento, nombre,fecha,cod_deporte,lugar,numeroUsuarios,observaciones) 
VALUES 
('1','Torneillo','2022-07-01','1','Canchas indurain','15',''),
('2', 'Pachanguita','2022-06-17','2','Polideportivo Navalcarbon','20','Va a hacer calor'),
('3', 'Padelsiuco','2022-07-03','3','Polideportivo Mostoles','4','Llevad pelotas'),
('4', 'Basketcallejero','2022-07-10','1','Canchas Torrelodones','10','a lo mejor hay gente'),
('5', 'Pachanguitafreecovid','2022-06-12','2','Polideportivo Galapagar','20','Se juega con mascarilla');


INSERT INTO usuario_eventos (cod_usuario,cod_evento) 
VALUES 
('1', '2'),
('2', '3'),
('3', '4'),
('4', '5');

INSERT INTO foro (codigo_foro,codigo_evento)
VALUES
('1', '1'),
('2', '2'),
('3', '3'),
('4', '4'),
('5', '5');

INSERT INTO mensaje (cod_mensaje,mensaje,cod_usuario,cod_foro)
VALUES
('1', '¿Que dia es la pachanguitafreecovid?','2','5'),
('2', 'Creo que es el martes','3','5'),
('3', 'El polideportivo de Las Rozas esta cerrado','5','3'),
('4', 'Pues na nos quedamos sin partido','7','3');


 -- Selects

use sportchoice;
select e.nombre, e.fecha, d.nombre as deporte, e.lugar, e.numeroUsuarios as 'Nº Usuarios' from eventos e INNER JOIN deportes d ON e.cod_deporte = d.cod_deporte;

SELECT * FROM usuarios WHERE nombre = 'Gervasio' AND passwd = 123;

SELECT nombre,email FROM usuarios;

Select cod_usuario,cod_evento from usuario_eventos;

Select codigoEvento from eventos where nombre = 'Franklin';

select cod_deporte from deportes where nombre = 'Padel';

Select nombre from eventos;

select codigo_usuario from usuarios where nombre = 'Franklin';

select codigoEvento from eventos where nombre = 'Padelsiuco';

SELECT * FROM eventos;