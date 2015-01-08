
/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  07/01/2015 12:22:01                      */
/*==============================================================*/


drop index if exists AUTEUR_PK CASCADE ;

drop table if exists Auteur CASCADE ;

drop index if exists AUTEUREXTERNEINSTITUTION_FK CASCADE ;

drop index if exists AUTEUREXTERNE_PK CASCADE ;

drop table if exists AuteurExterne CASCADE ;

drop index if exists AUTHEURINTERNE_PK CASCADE ;

drop table if exists AuteurInterne CASCADE ;

drop index if exists AUTHENTIFICATION_PK CASCADE ;

drop table if exists Authentification CASCADE ;

drop index if exists CONFERENCE_PK CASCADE ;

drop table if exists Conference CASCADE ;

drop index if exists INSTITUTIONRATTCHEMENT_PK CASCADE ;

drop table if exists InstitutionRattchement CASCADE ;

drop index if exists JOURNAL_PK CASCADE ;

drop table if exists Journal CASCADE ;

drop index if exists PUBLICATION_PK CASCADE ;

drop table if exists Publication CASCADE ;

drop index if exists REFERENCEPUBLICATION_FK CASCADE ;

drop index if exists REFERENCE_PK CASCADE ;

drop table if exists Reference CASCADE ;

drop index if exists PULICATIONAUTEUR_FK2 CASCADE ;

drop index if exists PULICATIONAUTEUR_FK CASCADE ;

drop index if exists PULICATIONAUTEUR_PK CASCADE ;

drop table if exists pulicationAuteur CASCADE ;

/*==============================================================*/
/* Table : Auteur                                               */
/*==============================================================*/
create table Auteur (
   id                   INT4                 not null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   email                VARCHAR(254)         null,
   sitePerso            VARCHAR(254)         null,
   constraint PK_AUTEUR primary key (id)
);

/*==============================================================*/
/* Index : AUTEUR_PK                                            */
/*==============================================================*/
create unique index AUTEUR_PK on Auteur (
id
);

/*==============================================================*/
/* Table : AuteurExterne                                        */
/*==============================================================*/
create table AuteurExterne (
   id                   INT4                 not null,
   idInstitution        INT4                 not null,
   idExterne            VARCHAR(254)         null,
   constraint PK_AUTEUREXTERNE primary key (id)
);

/*==============================================================*/
/* Index : AUTEUREXTERNE_PK                                     */
/*==============================================================*/
create unique index AUTEUREXTERNE_PK on AuteurExterne (
id
);

/*==============================================================*/
/* Index : AUTEUREXTERNEINSTITUTION_FK                          */
/*==============================================================*/
create  index AUTEUREXTERNEINSTITUTION_FK on AuteurExterne (
idInstitution
);

/*==============================================================*/
/* Table : AuteurInterne                                        */
/*==============================================================*/
create table AuteurInterne (
   id                   INT4                 not null,
   idTelecom            VARCHAR(254)         null,
   constraint PK_AUTEURINTERNE primary key (id)
);

/*==============================================================*/
/* Index : AUTHEURINTERNE_PK                                    */
/*==============================================================*/
create unique index AUTHEURINTERNE_PK on AuteurInterne (
id
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
/* Table : Conference                                           */
/*==============================================================*/
create table Conference (
   id                   INT4                 not null,
   theme                VARCHAR(254)         null,
   lieu                 VARCHAR(254)         null,
   dateConference       DATE                 null,
   isbn                 VARCHAR(254)         null,
   editeur              VARCHAR(254)         null,
   description          VARCHAR(254)         null,
   collectivite         VARCHAR(254)         null,
   constraint PK_CONFERENCE primary key (id)
);

/*==============================================================*/
/* Index : CONFERENCE_PK                                        */
/*==============================================================*/
create unique index CONFERENCE_PK on Conference (
id
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
/* Table : Journal                                              */
/*==============================================================*/
create table Journal (
   id                   INT4                 not null,
   nomJournal           VARCHAR(254)         null,
   sujet                VARCHAR(254)         null,
   dateApparition       DATE                 null,
   volume               VARCHAR(254)         null,
   constraint PK_JOURNAL primary key (id)
);

/*==============================================================*/
/* Index : JOURNAL_PK                                           */
/*==============================================================*/
create unique index JOURNAL_PK on Journal (
id
);

/*==============================================================*/
/* Table : Publication                                          */
/*==============================================================*/
create table Publication (
   id                   INT4                 not null,
   titre                VARCHAR(254)         not null,
   datePublication      DATE                 null,
   resume               VARCHAR(254)         null,
   langue               VARCHAR(254)         null,
   dateDebutPublication DATE                 null,
   dateFinPublication   DATE                 null,
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

alter table AuteurExterne
   add constraint FK_AUTEUREX_GENERALIS_AUTEUR foreign key (id)
      references Auteur (id)
      on delete restrict on update restrict;

alter table AuteurExterne
   add constraint FK_AUTEUREX_AUTEUREXT_INSTITUT foreign key (idInstitution)
      references InstitutionRattchement (idInstitution)
      on delete restrict on update restrict;

alter table AuteurInterne
   add constraint FK_AUTEURIN_GENERALIS_AUTEUR foreign key (id)
      references Auteur (id)
      on delete restrict on update restrict;

alter table Conference
   add constraint FK_CONFEREN_GENERALIS_PUBLICAT foreign key (id)
      references Publication (id)
      on delete restrict on update restrict;

alter table Journal
   add constraint FK_JOURNAL_GENERALIS_PUBLICAT foreign key (id)
      references Publication (id)
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

