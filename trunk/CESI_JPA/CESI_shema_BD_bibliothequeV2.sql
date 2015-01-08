/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  08/01/2015 00:37:08                      */
/*==============================================================*/


drop index if exists AUTEUREXTERNEINSTITUTION_FK CASCADE;

drop index if exists AUTEUR_PK CASCADE;

drop table if exists Auteur CASCADE;

drop index if exists AUTHENTIFICATION_PK CASCADE;

drop table if exists Authentification CASCADE;

drop index if exists INSTITUTIONRATTCHEMENT_PK CASCADE;

drop table if exists InstitutionRattchement CASCADE;

drop index if exists PUBLICATION_PK CASCADE;

drop table if exists Publication CASCADE;

drop index if exists REFERENCEPUBLICATION_FK CASCADE;

drop index if exists REFERENCE_PK CASCADE;

drop table if exists Reference CASCADE;

drop index if exists PULICATIONAUTEUR_FK2 CASCADE;

drop index if exists PULICATIONAUTEUR_FK CASCADE;

drop index if exists PULICATIONAUTEUR_PK CASCADE;

drop table if exists pulicationAuteur CASCADE;

/*==============================================================*/
/* Table : Auteur                                               */
/*==============================================================*/
create table Auteur (
   id                   INT4                 not null,
   type					VARCHAR(254)		 not null,
   idInstitution        INT4                 not null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   email                VARCHAR(254)         null,
   sitePerso            VARCHAR(254)         null,
   idTelecom            VARCHAR(254)         null,
   idExterne            VARCHAR(254)         null,
   constraint PK_AUTEUR primary key (id)
);

/*==============================================================*/
/* Index : AUTEUR_PK                                            */
/*==============================================================*/
create unique index AUTEUR_PK on Auteur (
id
);

/*==============================================================*/
/* Index : AUTEUREXTERNEINSTITUTION_FK                          */
/*==============================================================*/
create  index AUTEUREXTERNEINSTITUTION_FK on Auteur (
idInstitution
);

/*==============================================================*/
/* Table : Authentification                                     */
/*==============================================================*/
create table Authentification (
   idAuthentification   INT4                 not null,
   login                VARCHAR(254)         null,
   password             VARCHAR(254)         null,
   role                 VARCHAR(254)         null,
   constraint PK_AUTHENTIFICATION primary key (idAuthentification)
);

/*==============================================================*/
/* Index : AUTHENTIFICATION_PK                                  */
/*==============================================================*/
create unique index AUTHENTIFICATION_PK on Authentification (
idAuthentification
);

/*==============================================================*/
/* Table : InstitutionRattchement                               */
/*==============================================================*/
create table InstitutionRattchement (
   idInstitution        INT4                 not null,
   nomInstitution       VARCHAR(254)         null,
   adresse              VARCHAR(254)         null,
   constraint PK_INSTITUTIONRATTCHEMENT primary key (idInstitution)
);

/*==============================================================*/
/* Index : INSTITUTIONRATTCHEMENT_PK                            */
/*==============================================================*/
create unique index INSTITUTIONRATTCHEMENT_PK on InstitutionRattchement (
idInstitution
);

/*==============================================================*/
/* Table : Publication                                          */
/*==============================================================*/
create table Publication (
   id                   INT4                 not null,
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
/* Index : PUBLICATION_PK                                       */
/*==============================================================*/
create unique index PUBLICATION_PK on Publication (
id
);

/*==============================================================*/
/* Table : Reference                                            */
/*==============================================================*/
create table Reference (
   idReference          INT4                 not null,
   id                   INT4                 null,
   codeReference        VARCHAR(254)         null,
   institutionRattachement VARCHAR(254)         null,
   dateDebutReference   DATE                 null,
   dateFinReference     DATE                 null,
   constraint PK_REFERENCE primary key (idReference)
);

/*==============================================================*/
/* Index : REFERENCE_PK                                         */
/*==============================================================*/
create unique index REFERENCE_PK on Reference (
idReference
);

/*==============================================================*/
/* Index : REFERENCEPUBLICATION_FK                              */
/*==============================================================*/
create  index REFERENCEPUBLICATION_FK on Reference (
id
);

/*==============================================================*/
/* Table : pulicationAuteur                                     */
/*==============================================================*/
create table pulicationAuteur (
   Pub_id               INT4                 not null,
   id                   INT4                 not null,
   constraint PK_PULICATIONAUTEUR primary key (Pub_id, id)
);

/*==============================================================*/
/* Index : PULICATIONAUTEUR_PK                                  */
/*==============================================================*/
create unique index PULICATIONAUTEUR_PK on pulicationAuteur (
Pub_id,
id
);

/*==============================================================*/
/* Index : PULICATIONAUTEUR_FK                                  */
/*==============================================================*/
create  index PULICATIONAUTEUR_FK on pulicationAuteur (
Pub_id
);

/*==============================================================*/
/* Index : PULICATIONAUTEUR_FK2                                 */
/*==============================================================*/
create  index PULICATIONAUTEUR_FK2 on pulicationAuteur (
id
);

alter table Auteur
   add constraint FK_AUTEUR_AUTEUREXT_INSTITUT foreign key (idInstitution)
      references InstitutionRattchement (idInstitution)
      on delete restrict on update restrict;

alter table Reference
   add constraint FK_REFERENC_REFERENCE_PUBLICAT foreign key (id)
      references Publication (id)
      on delete restrict on update restrict;

alter table pulicationAuteur
   add constraint FK_PULICATI_PULICATIO_AUTEUR foreign key (id)
      references Auteur (id)
      on delete restrict on update restrict;

alter table pulicationAuteur
   add constraint FK_PULICATI_PULICATIO_PUBLICAT foreign key (Pub_id)
      references Publication (id)
      on delete restrict on update restrict;

