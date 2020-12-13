--Ajout de la colonne de téléphone
alter table user add column phone varchar(10) after surname;
--Insert du numéro à toute les lignes (le mien pour pouvoir recevoir)
update user set phone = "0688137360" where 1;

--Ajout de la colonne de rôle
alter table user add column role boolean after email;
--Insert des rôles (1 = admin, 0 = non admin)
update user set role = 1 where login = "amassa";
update user set role = 0 where login = "pdupont";

--Ajout des colonnes temp_min(température minimale) et temp_max(température maximale) dans stade
alter table stade add column temp_min int after nom_stade;
alter table stade add column temp_max int after nom_stade;
--Insert d'un min et max pour chaque stade

--creation de la table des alertes
create table if not exists alertes(
    num_stade integer,
    num_zone integer,
    horodate datetime,
    constraint pk_alert primary key(num_stade, num_zone, horodate)
);