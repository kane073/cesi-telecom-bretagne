/*==============================================================*/
/* JEU DE DE DONNEE										        */
/* Nom de SGBD :  CESI_BD                   		            */
/* Date de création :  08/01/2015 			                    */
/* Createur : AKANE												*/
/*==============================================================*/

INSERT INTO publication(
            id, type, titre, datepublication, resume, langue, datedebutpublication, 
            datefinpublication, theme, lieu, dateconference, isbn, editeur, 
            description, collectivite, nomjournal, sujet, dateapparition, 
            volume)
    VALUES (DEFAULT, 'journal', 'La rose médiévale de la cathédrale de Poitiers', 
    		'2010-11-1', 'La rose occidentale de la cathédrale de Poitiers ne nous est connue quà travers deux réfections successives, réalisées respectivement au XVIIe et au XIXe siècle', 
    		'français', '2015-2-09', null, null, null, null, null, null, null, null, 
    		'Le monde', 'Centre dEtudes Supérieures de Civilisation médiévale', '2014-2-09','5');

INSERT INTO publication(
            id, type, titre, datepublication, resume, langue, datedebutpublication, 
            datefinpublication, theme, lieu, dateconference, isbn, editeur, 
            description, collectivite, nomjournal, sujet, dateapparition, 
            volume)
    VALUES (DEFAULT, 'conference', 'Configural information in gender categorisation', 
    		'2010-11-1', 'The role of configural information in gender categorisation was Studied', 
    		'français', '2015-2-09',null, 'Confihural', 'londre', '2011-1-01', '2011-1-01', 'alassane', 
    		'papa tu es où', null, null, null, null, null);
    		
INSERT INTO institutionrattchement(
            id_institution, nominstitution, adresse)
    VALUES (DEFAULT, 'Telecom Bretagne', 'Plouzané');
    
INSERT INTO institutionrattchement(
            id_institution, nominstitution, adresse)
    VALUES (DEFAULT, 'UBO', 'Brest');

INSERT INTO auteur(
            id, type, id_institution, nom, prenom, email, siteperso, idtelecom, 
            idexterne)
    VALUES (DEFAULT, 'auteurinterne',
    		(select id_institution from institutionrattchement where nominstitution='Telecom Bretagne'),
    		'Touré', 'Dieynaba', 'toure.dieynaba@telecom.fr', 'www.toure.fr', 'dtoure', null);

INSERT INTO auteur(
            id, type, id_institution, nom, prenom, email, siteperso, idtelecom, 
            idexterne)
    VALUES (DEFAULT, 'auteurexterne',
    		(select id_institution from institutionrattchement where nominstitution='Telecom Bretagne'),
    		'Kane', 'Fousseyni', 'fousseyni.kane@fmpos.fr', 'www.fkane.fr', null, 'fkane');
    		

insert into pulication_auteur (pub_id, id) values 
((select id from publication where titre='Configural information in gender categorisation'), 
(select id from auteur where email='toure.dieynaba@telecom.fr'));

insert into pulication_auteur (pub_id, id) values 
((select id from publication where titre='Configural information in gender categorisation'), 
(select id from auteur where email='fousseyni.kane@fmpos.fr'));

insert into pulication_auteur (pub_id, id) values 
((select id from publication where titre='La rose médiévale de la cathédrale de Poitiers'), 
(select id from auteur where email='fousseyni.kane@fmpos.fr'));