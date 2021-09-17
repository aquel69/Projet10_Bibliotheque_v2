
CREATE SEQUENCE public.editeur_id_seq;

CREATE TABLE public.Editeur (
                id_editeur VARCHAR NOT NULL DEFAULT nextval('public.editeur_id_seq'),
                nom_maison_edition VARCHAR NOT NULL,
                CONSTRAINT editeur_pk PRIMARY KEY (id_editeur)
);


ALTER SEQUENCE public.editeur_id_seq OWNED BY public.Editeur.id_editeur;

CREATE SEQUENCE public.livre_id_seq;

CREATE TABLE public.Livre (
                id_livre NUMERIC NOT NULL DEFAULT nextval('public.livre_id_seq'),
                titre VARCHAR NOT NULL,
                resume VARCHAR NOT NULL,
                date_edition DATE NOT NULL,
                num_ISBN13 VARCHAR NOT NULL,
                langue VARCHAR NOT NULL,
                id_editeur VARCHAR NOT NULL,
                nombre_de_pages NUMERIC NOT NULL,
                petite_photo_couverture VARCHAR NOT NULL,
                grande_photo_couverture VARCHAR NOT NULL,
                CONSTRAINT livre_pk PRIMARY KEY (id_livre)
);


ALTER SEQUENCE public.livre_id_seq OWNED BY public.Livre.id_livre;

CREATE SEQUENCE public.ouvrage_id_ouvrage_seq;

CREATE TABLE public.Ouvrage (
                id_ouvrage NUMERIC NOT NULL DEFAULT nextval('public.ouvrage_id_ouvrage_seq'),
                code_bibliotheque VARCHAR NOT NULL,
                id_livre NUMERIC NOT NULL,
                CONSTRAINT ouvrage_pk PRIMARY KEY (id_ouvrage)
);


ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.Ouvrage.id_ouvrage;

CREATE SEQUENCE public.pret_id_pret_seq;

CREATE TABLE public.Pret (
                id_pret NUMERIC NOT NULL DEFAULT nextval('public.pret_id_pret_seq'),
                date_emprunt DATE NOT NULL,
                date_restitution DATE,
                prolongation BOOLEAN NOT NULL,
                id_ouvrage NUMERIC NOT NULL,
                CONSTRAINT pret_pk PRIMARY KEY (id_pret)
);


ALTER SEQUENCE public.pret_id_pret_seq OWNED BY public.Pret.id_pret;

CREATE SEQUENCE public.auteur_id_seq;

CREATE TABLE public.Auteur (
                id_auteur NUMERIC NOT NULL DEFAULT nextval('public.auteur_id_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                date_de_naissance DATE NOT NULL,
                date_deces VARCHAR,
                commentaire VARCHAR NOT NULL,
                CONSTRAINT auteur_pk PRIMARY KEY (id_auteur)
);


ALTER SEQUENCE public.auteur_id_seq OWNED BY public.Auteur.id_auteur;

CREATE SEQUENCE public.livre_auteurs_id_seq;

CREATE TABLE public.Livre_Auteurs (
                id_livre_auteur NUMERIC NOT NULL DEFAULT nextval('public.livre_auteurs_id_seq'),
                id_livre NUMERIC NOT NULL,
                id_auteur NUMERIC NOT NULL,
                CONSTRAINT livre_auteurs_pk PRIMARY KEY (id_livre_auteur)
);


ALTER SEQUENCE public.livre_auteurs_id_seq OWNED BY public.Livre_Auteurs.id_livre_auteur;

CREATE SEQUENCE public.genre_nom_seq;

CREATE TABLE public.Genre (
                id_genre NUMERIC NOT NULL DEFAULT nextval('public.genre_nom_seq'),
                nom VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (id_genre)
);


ALTER SEQUENCE public.genre_nom_seq OWNED BY public.Genre.id_genre;

CREATE SEQUENCE public.liste_genre_id_liste_genre_seq;

CREATE TABLE public.liste_genre (
                id_liste_genre NUMERIC NOT NULL DEFAULT nextval('public.liste_genre_id_liste_genre_seq'),
                id_livre NUMERIC NOT NULL,
                id_genre NUMERIC NOT NULL,
                CONSTRAINT liste_genre_pk PRIMARY KEY (id_liste_genre)
);


ALTER SEQUENCE public.liste_genre_id_liste_genre_seq OWNED BY public.liste_genre.id_liste_genre;

CREATE TABLE public.EnumRole (
                code NUMERIC NOT NULL,
                status VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                CONSTRAINT enumrole_pk PRIMARY KEY (code)
);


CREATE SEQUENCE public.adresse_id_seq;

CREATE TABLE public.Adresse (
                id_adresse NUMERIC NOT NULL DEFAULT nextval('public.adresse_id_seq'),
                numero VARCHAR NOT NULL,
                rue VARCHAR NOT NULL,
                complement VARCHAR,
                code_postal VARCHAR NOT NULL,
                ville VARCHAR NOT NULL,
                CONSTRAINT adresse_pk PRIMARY KEY (id_adresse)
);


ALTER SEQUENCE public.adresse_id_seq OWNED BY public.Adresse.id_adresse;

CREATE TABLE public.Bibliotheque (
                numero_siret VARCHAR NOT NULL,
                nom VARCHAR NOT NULL,
                code VARCHAR NOT NULL,
                id_adresse NUMERIC NOT NULL,
                CONSTRAINT bibliotheque_pk PRIMARY KEY (numero_siret)
);
COMMENT ON COLUMN public.Bibliotheque.code IS 'Le code bibliothèque est un trigramme de lettres qui identifie de manière unique une bibliothèque au sein du groupement de bibliothèques. Ce trigramme est utilisé pour les 3 premiers caractères de la codification interne d''un ouvrage.';


CREATE SEQUENCE public.employe_id_employe_seq;

CREATE TABLE public.Employe (
                id_employe NUMERIC NOT NULL DEFAULT nextval('public.employe_id_employe_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                matricule VARCHAR NOT NULL,
                date_embauche DATE NOT NULL,
                date_depart DATE,
                email VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                bibliotheque VARCHAR NOT NULL,
                role NUMERIC NOT NULL,
                id_adresse NUMERIC NOT NULL,
                CONSTRAINT employe_pk PRIMARY KEY (id_employe)
);


ALTER SEQUENCE public.employe_id_employe_seq OWNED BY public.Employe.id_employe;

CREATE SEQUENCE public.abonne_id_abonne_seq;

CREATE TABLE public.Abonne (
                id_abonne NUMERIC NOT NULL DEFAULT nextval('public.abonne_id_abonne_seq'),
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                pseudo VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                numero_abonne VARCHAR NOT NULL,
                date_creation_du_compte DATE NOT NULL,
                role NUMERIC NOT NULL,
                id_adresse NUMERIC NOT NULL,
                bibliotheque VARCHAR NOT NULL,
                CONSTRAINT abonne_pk PRIMARY KEY (id_abonne)
);


ALTER SEQUENCE public.abonne_id_abonne_seq OWNED BY public.Abonne.id_abonne;

CREATE SEQUENCE public.pret_abonne_id_pret_abonne_seq;

CREATE TABLE public.pret_abonne (
                id_pret_abonne NUMERIC NOT NULL DEFAULT nextval('public.pret_abonne_id_pret_abonne_seq'),
                id_pret NUMERIC NOT NULL,
                id_abonne NUMERIC NOT NULL,
                CONSTRAINT pret_abonne_pk PRIMARY KEY (id_pret_abonne)
);


ALTER SEQUENCE public.pret_abonne_id_pret_abonne_seq OWNED BY public.pret_abonne.id_pret_abonne;

ALTER TABLE public.Livre ADD CONSTRAINT editeur_livre_fk
FOREIGN KEY (id_editeur)
REFERENCES public.Editeur (id_editeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livre_Auteurs ADD CONSTRAINT livre_livre_auteurs_fk
FOREIGN KEY (id_livre)
REFERENCES public.Livre (id_livre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Ouvrage ADD CONSTRAINT livre_ouvrage_fk
FOREIGN KEY (id_livre)
REFERENCES public.Livre (id_livre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.liste_genre ADD CONSTRAINT livre_liste_genre_fk
FOREIGN KEY (id_livre)
REFERENCES public.Livre (id_livre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Pret ADD CONSTRAINT ouvrage_pret_fk
FOREIGN KEY (id_ouvrage)
REFERENCES public.Ouvrage (id_ouvrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pret_abonne ADD CONSTRAINT pret_pret_abonne_fk
FOREIGN KEY (id_pret)
REFERENCES public.Pret (id_pret)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livre_Auteurs ADD CONSTRAINT auteur_livre_auteurs_fk
FOREIGN KEY (id_auteur)
REFERENCES public.Auteur (id_auteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.liste_genre ADD CONSTRAINT genre_liste_genre_fk
FOREIGN KEY (id_genre)
REFERENCES public.Genre (id_genre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Abonne ADD CONSTRAINT enumrole_abonne_fk
FOREIGN KEY (role)
REFERENCES public.EnumRole (code)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Employe ADD CONSTRAINT enumrole_employe_fk
FOREIGN KEY (role)
REFERENCES public.EnumRole (code)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Bibliotheque ADD CONSTRAINT adresse_bibliotheque_fk
FOREIGN KEY (id_adresse)
REFERENCES public.Adresse (id_adresse)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Abonne ADD CONSTRAINT adresse_abonne_fk
FOREIGN KEY (id_adresse)
REFERENCES public.Adresse (id_adresse)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Employe ADD CONSTRAINT adresse_employe_fk
FOREIGN KEY (id_adresse)
REFERENCES public.Adresse (id_adresse)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Abonne ADD CONSTRAINT bibliotheque_abonne_fk
FOREIGN KEY (bibliotheque)
REFERENCES public.Bibliotheque (numero_siret)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Employe ADD CONSTRAINT bibliotheque_employe_fk
FOREIGN KEY (bibliotheque)
REFERENCES public.Bibliotheque (numero_siret)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pret_abonne ADD CONSTRAINT abonne_pret_abonne_fk
FOREIGN KEY (id_abonne)
REFERENCES public.Abonne (id_abonne)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
