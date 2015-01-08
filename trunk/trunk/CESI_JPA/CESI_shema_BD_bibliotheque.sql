/*==============================================================*/
/* SCHEMA BASE DE DONNEE										*/
/* Nom de SGBD :  CESI_BD                   		            */
/* Date de création :  08/01/2015 			                    */
/* Createur : AKANE												*/
/*==============================================================*/


drop index if exists auteurexterneinstitution_fk CASCADE;

drop index if exists auteur_pk CASCADE;

drop table if exists auteur CASCADE;

drop index if exists authentification_pk CASCADE;

drop table if exists authentification CASCADE;

drop index if exists institutionrattchement_pk CASCADE;

drop table if exists institutionrattchement CASCADE;

drop index if exists publication_pk CASCADE;

drop table if exists publication CASCADE;

drop index if exists referencepublication_fk CASCADE;

drop index if exists reference_pk CASCADE;

drop table if exists reference CASCADE;

drop index if exists pulicationauteur_fk2 CASCADE;

drop index if exists pulicationauteur_fk CASCADE;

drop index if exists pulicationauteur_pk CASCADE;

drop table if exists pulication_auteur CASCADE;

/*==============================================================*/
/* Table : Auteur                                               */
/*==============================================================*/
create table auteur (
   id                   serial           	 not null,
   type					VARCHAR(254)		 not null,
   id_institution        INT4                not null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   email                VARCHAR(254)         null,
   sitePerso            VARCHAR(254)         null,
   idTelecom            VARCHAR(254)         null,
   idExterne            VARCHAR(254)         null,
   constraint PK_AUTEUR primary key (id) 
);

/*==============================================================*/
/* Index : auteur_pk                                            */
/*==============================================================*/
create unique index auteur_pk on auteur (
id
);

/*==============================================================*/
/* Index : auteurexterneinstitution_fk                          */
/*==============================================================*/
create  index auteurexterneinstitution_fk on auteur (
id_institution
);

/*==============================================================*/
/* Table : Authentification                                     */
/*==============================================================*/
create table authentification (
   id_authentification   serial                 not null,
   login                VARCHAR(254)         null,
   password             VARCHAR(254)         null,
   role                 VARCHAR(254)         null,
   constraint PK_AUTHENTIFICATION primary key (id_authentification)
);

/*==============================================================*/
/* Index : authentification_pk                                  */
/*==============================================================*/
create unique index authentification_pk on authentification (
id_authentification
);

/*==============================================================*/
/* Table : InstitutionRattchement                               */
/*==============================================================*/
create table institutionrattchement (
   id_institution        serial                 not null,
   nomInstitution       VARCHAR(254)         null,
   adresse              VARCHAR(254)         null,
   constraint PK_INSTITUTIONRATTCHEMENT primary key (id_institution)
);

/*==============================================================*/
/* Index : institutionrattchement_pk                            */
/*==============================================================*/
create unique index institutionrattchement_pk on institutionrattchement (
id_institution
);

/*==============================================================*/
/* Table : Publication                                          */
/*==============================================================*/
create table publication (
   id                   serial                 not null,
   type					VARCHAR(254)		 not null,
   titre                VARCHAR(254)         not null,
   datePublication      DATE                 null,
   resume               VARCHAR(254)         null,
   langue               VARCHAR(254)         null,
   dateDebutPublication DATE                 null,
   dateFinPublication   DATE                 null,
   theme                VARCHAR(254)         null,
   lieu                 VARCHAR(254)         null,
   dateConference       DATE                 null,
   isbn                 VARCHAR(254)         null,
   editeur              VARCHAR(254)         null,
   description          VARCHAR(254)         null,
   collectivite         VARCHAR(254)         null,
   nomJournal           VARCHAR(254)         null,
   sujet                VARCHAR(254)         null,
   dateApparition       DATE                 null,
   volume               VARCHAR(254)         null,
   constraint PK_PUBLICATION primary key (id)
);

/*==============================================================*/
/* Index : publication_pk                                       */
/*==============================================================*/
create unique index publication_pk on publication (
id
);

/*==============================================================*/
/* Table : Reference                                            */
/*==============================================================*/
create table reference (
   id_reference          serial                 not null,
   id                   INT4                 null,
   codeReference        VARCHAR(254)         null,
   institutionRattachement VARCHAR(254)         null,
   dateDebutReference   DATE                 null,
   dateFinReference     DATE                 null,
   constraint PK_REFERENCE primary key (id_reference)
);

/*==============================================================*/
/* Index : reference_pk                                         */
/*==============================================================*/
create unique index reference_pk on reference (
id_reference
);

/*==============================================================*/
/* Index : referencepublication_fk                              */
/*==============================================================*/
create  index referencepublication_fk on reference (
id
);

/*==============================================================*/
/* Table : pulication_auteur                                     */
/*==============================================================*/
create table pulication_auteur (
   Pub_id               INT4                 not null,
   id                   INT4                 not null,
   constraint PK_PULICATIONAUTEUR primary key (Pub_id, id)
);

/*==============================================================*/
/* Index : pulicationauteur_pk                                  */
/*==============================================================*/
create unique index pulicationauteur_pk on pulication_auteur (
Pub_id,
id
);

/*==============================================================*/
/* Index : pulicationauteur_fk                                  */
/*==============================================================*/
create  index pulicationauteur_fk on pulication_auteur (
Pub_id
);

/*==============================================================*/
/* Index : pulicationauteur_fk2                                 */
/*==============================================================*/
create  index pulicationauteur_fk2 on pulication_auteur (
id
);

alter table auteur
   add constraint FK_AUTEUR_AUTEUREXT_INSTITUT foreign key (id_institution)
      references institutionrattchement (id_institution)
      on delete restrict on update restrict;

alter table reference
   add constraint FK_REFERENC_REFERENCE_PUBLICAT foreign key (id)
      references publication (id)
      on delete restrict on update restrict;

alter table pulication_auteur
   add constraint FK_PULICATI_PULICATIO_AUTEUR foreign key (id)
      references auteur (id)
      on delete restrict on update restrict;

alter table pulication_auteur
   add constraint FK_PULICATI_PULICATIO_PUBLICAT foreign key (Pub_id)
      references publication (id)
      on delete restrict on update restrict;

