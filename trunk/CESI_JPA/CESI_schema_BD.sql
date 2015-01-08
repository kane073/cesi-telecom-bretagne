-- Suppression des tables
drop table if exists "agent";
drop table if exists "departement";
-- Création des tables
create table "departement"
(
id serial primary key,
intitule varchar(50) not null
);
create table "agent"
(
id serial primary key,
nom varchar(100) not null,
departement_fk integer not null references "departement"
);


-- Données de tests
--- Deux départements...
insert into "departement" values (DEFAULT, 'Dept1');
insert into "departement" values (DEFAULT, 'Dept2');
--- ... et trois agents: agt1, agt2 et agt3 dans le Dept1
insert into "agent" values (DEFAULT, 'agt1', (select id from Departement where intitule='Dept1'));
insert into "agent" values (DEFAULT, 'agt2', (select id from Departement where intitule='Dept1'));
insert into "agent" values (DEFAULT, 'agt3', (select id from Departement where intitule='Dept1'));

