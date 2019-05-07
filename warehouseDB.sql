drop database if exists warehousetp;
 create database if not exists warehousetp;
use warehousetp;
create table if not exists client
(id int unique not null auto_increment,
nume varchar(45),
prenume varchar(45),
adresa varchar(45));
alter table client
add primary key(id);
create table if not exists comanda
(id int unique not null auto_increment,
id_client int);

alter table comanda
add primary key(id);

create table if not exists produs
(id int unique not null auto_increment,
nume varchar(45),
pret float);

alter table produs
add primary key(id);

create table if not exists stoc
(id_produs int unique not null,
cantitate int );

create table if not exists comandaprodus
(id_comanda int,
id_produs int,
cantitate int );

alter table comanda
add constraint comanda_fk1 foreign key(id_client) references client(id) on delete cascade on update cascade;

alter table comandaprodus
add primary key(id_comanda,id_produs);

alter table comandaprodus
add constraint comanda_fk foreign key(id_comanda) references comanda(id) on delete cascade on update cascade;

alter table comandaprodus
add constraint produs_fk foreign key(id_produs) references produs(id) on delete cascade on update cascade;

alter table stoc
add primary key(id_produs);

alter table stoc
add constraint stocprodus_fk foreign key(id_produs) references produs(id) on delete cascade on update cascade;


create index index_comanda on comanda(id asc);
INSERT INTO `warehousetp`.`client` (`id`, `nume`, `prenume`, `adresa`) VALUES ('1', 'David', 'Carmen', 'Dej');
INSERT INTO `warehousetp`.`client` (`id`, `nume`, `prenume`, `adresa`) VALUES ('2', 'Gherman', 'Florica', 'Iasi');
INSERT INTO `warehousetp`.`client` (`id`, `nume`, `prenume`, `adresa`) VALUES ('3', 'Nas', 'Razvan', 'Cluj');
INSERT INTO `warehousetp`.`client` (`id`, `nume`, `prenume`, `adresa`) VALUES ('4', 'Iernutan', 'Denisa', 'Iernut');
INSERT INTO `warehousetp`.`client` (`id`, `nume`, `prenume`, `adresa`) VALUES ('5', 'Stan', 'Vasile', 'Calarasi');

insert into warehousetp.produs(id,nume,pret) values
(1,'cola',3.5),
(2,'fasole',1.2),
(3,'sprite',3.8),
(4,'fanta',4.0),
(5,'cartofi',2.3);

insert into comanda(id,id_client) values
(1,3),
(2,1),
(3,3),
(4,5);

insert into stoc(id_produs,cantitate) values
(1,20),
(2,100),
(3,38),
(4,200),
(5,10);

insert into comandaprodus(id_comanda,id_produs,cantitate) values
(1,1,3),
(1,4,2),
(2,3,12),
(3,5,24),
(4,1,1),
(4,2,1),
(4,3,1);