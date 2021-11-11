--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2021-11-11 11:54:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 76183)
-- Name: abonne; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.abonne (
    id_abonne numeric NOT NULL,
    nom character varying NOT NULL,
    prenom character varying NOT NULL,
    pseudo character varying NOT NULL,
    email character varying NOT NULL,
    mot_de_passe character varying NOT NULL,
    numero_abonne character varying NOT NULL,
    date_creation_du_compte date NOT NULL,
    role numeric NOT NULL,
    id_adresse integer NOT NULL,
    bibliotheque character varying NOT NULL
);


ALTER TABLE public.abonne OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 76189)
-- Name: abonne_id_abonne_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abonne_id_abonne_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abonne_id_abonne_seq OWNER TO postgres;

--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 203
-- Name: abonne_id_abonne_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abonne_id_abonne_seq OWNED BY public.abonne.id_abonne;


--
-- TOC entry 230 (class 1259 OID 76411)
-- Name: abonne_ouvrage_reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.abonne_ouvrage_reservation (
    id_abonne_ouvrage_reservation numeric NOT NULL,
    id_abonne numeric NOT NULL,
    id_ouvrage numeric NOT NULL,
    date_reservation timestamp without time zone NOT NULL,
    recupere boolean NOT NULL,
    position_reservation numeric,
    date_delai_recupere timestamp without time zone
);


ALTER TABLE public.abonne_ouvrage_reservation OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 76409)
-- Name: abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq OWNER TO postgres;

--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 229
-- Name: abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq OWNED BY public.abonne_ouvrage_reservation.id_abonne_ouvrage_reservation;


--
-- TOC entry 204 (class 1259 OID 76191)
-- Name: adresse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresse (
    id_adresse integer NOT NULL,
    numero character varying NOT NULL,
    rue character varying NOT NULL,
    complement character varying,
    code_postal character varying NOT NULL,
    ville character varying NOT NULL
);


ALTER TABLE public.adresse OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 76197)
-- Name: adresse_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adresse_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.adresse_id_seq OWNER TO postgres;

--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 205
-- Name: adresse_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adresse_id_seq OWNED BY public.adresse.id_adresse;


--
-- TOC entry 206 (class 1259 OID 76199)
-- Name: auteur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auteur (
    id_auteur numeric NOT NULL,
    nom character varying NOT NULL,
    prenom character varying NOT NULL,
    date_de_naissance date NOT NULL,
    date_deces character varying,
    commentaire character varying NOT NULL
);


ALTER TABLE public.auteur OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 76205)
-- Name: auteur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auteur_id_seq OWNER TO postgres;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 207
-- Name: auteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auteur_id_seq OWNED BY public.auteur.id_auteur;


--
-- TOC entry 208 (class 1259 OID 76207)
-- Name: bibliotheque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bibliotheque (
    numero_siret character varying NOT NULL,
    nom character varying NOT NULL,
    code character varying NOT NULL,
    id_adresse integer NOT NULL,
    dernier_ouvrage_rendu integer,
    nouveau_dernier_ouvrage boolean
);


ALTER TABLE public.bibliotheque OWNER TO postgres;

--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN bibliotheque.code; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.bibliotheque.code IS 'Le code bibliothèque est un trigramme de lettres qui identifie de manière unique une bibliothèque au sein du groupement de bibliothèques. Ce trigramme est utilisé pour les 3 premiers caractères de la codification interne d''un ouvrage.';


--
-- TOC entry 209 (class 1259 OID 76213)
-- Name: editeur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editeur (
    id_editeur numeric NOT NULL,
    nom_maison_edition character varying NOT NULL
);


ALTER TABLE public.editeur OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 76219)
-- Name: editeur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.editeur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.editeur_id_seq OWNER TO postgres;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 210
-- Name: editeur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.editeur_id_seq OWNED BY public.editeur.id_editeur;


--
-- TOC entry 211 (class 1259 OID 76221)
-- Name: employe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employe (
    id_employe numeric NOT NULL,
    nom character varying NOT NULL,
    prenom character varying NOT NULL,
    matricule character varying NOT NULL,
    date_embauche date NOT NULL,
    date_depart date,
    email character varying NOT NULL,
    mot_de_passe character varying NOT NULL,
    bibliotheque character varying NOT NULL,
    role numeric NOT NULL,
    id_adresse integer NOT NULL
);


ALTER TABLE public.employe OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 76227)
-- Name: employe_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employe_id_employe_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employe_id_employe_seq OWNER TO postgres;

--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 212
-- Name: employe_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employe_id_employe_seq OWNED BY public.employe.id_employe;


--
-- TOC entry 213 (class 1259 OID 76229)
-- Name: enumrole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enumrole (
    code numeric NOT NULL,
    status character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.enumrole OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 76235)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    id_genre numeric NOT NULL,
    nom character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 76241)
-- Name: genre_nom_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genre_nom_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genre_nom_seq OWNER TO postgres;

--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 215
-- Name: genre_nom_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.genre_nom_seq OWNED BY public.genre.id_genre;


--
-- TOC entry 216 (class 1259 OID 76243)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 76245)
-- Name: id_adresse; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_adresse
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_adresse OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 76247)
-- Name: liste_genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liste_genre (
    id_liste_genre numeric NOT NULL,
    id_livre numeric NOT NULL,
    id_genre numeric NOT NULL
);


ALTER TABLE public.liste_genre OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 76253)
-- Name: liste_genre_id_liste_genre_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.liste_genre_id_liste_genre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.liste_genre_id_liste_genre_seq OWNER TO postgres;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 219
-- Name: liste_genre_id_liste_genre_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.liste_genre_id_liste_genre_seq OWNED BY public.liste_genre.id_liste_genre;


--
-- TOC entry 220 (class 1259 OID 76255)
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre (
    id_livre numeric NOT NULL,
    titre character varying NOT NULL,
    resume character varying NOT NULL,
    date_edition date NOT NULL,
    num_isbn13 character varying NOT NULL,
    langue character varying NOT NULL,
    nombre_de_pages numeric NOT NULL,
    petite_photo_couverture character varying NOT NULL,
    grande_photo_couverture character varying NOT NULL,
    id_editeur numeric NOT NULL,
    moyenne_photo_couverture character varying
);


ALTER TABLE public.livre OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 76261)
-- Name: livre_auteurs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre_auteurs (
    id_livre_auteur numeric NOT NULL,
    id_livre numeric NOT NULL,
    id_auteur numeric NOT NULL
);


ALTER TABLE public.livre_auteurs OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 76267)
-- Name: livre_auteurs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livre_auteurs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livre_auteurs_id_seq OWNER TO postgres;

--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 222
-- Name: livre_auteurs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livre_auteurs_id_seq OWNED BY public.livre_auteurs.id_livre_auteur;


--
-- TOC entry 223 (class 1259 OID 76269)
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livre_id_seq OWNER TO postgres;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 223
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id_livre;


--
-- TOC entry 224 (class 1259 OID 76271)
-- Name: ouvrage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ouvrage (
    id_ouvrage numeric NOT NULL,
    code_bibliotheque character varying NOT NULL,
    id_livre numeric NOT NULL,
    date_ajout date NOT NULL,
    nombre_exemplaires numeric NOT NULL,
    siret_bibliotheque character varying NOT NULL,
    nombre_total_exemplaires numeric NOT NULL,
    date_retour_prevue timestamp without time zone
);


ALTER TABLE public.ouvrage OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 76277)
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ouvrage_id_ouvrage_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ouvrage_id_ouvrage_seq OWNER TO postgres;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 225
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ouvrage_id_ouvrage_seq OWNED BY public.ouvrage.id_ouvrage;


--
-- TOC entry 226 (class 1259 OID 76279)
-- Name: pret; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pret (
    id_pret numeric NOT NULL,
    date_emprunt date NOT NULL,
    date_restitution date NOT NULL,
    prolongation boolean NOT NULL,
    id_ouvrage numeric NOT NULL,
    id_abonne numeric NOT NULL,
    statut character varying NOT NULL,
    rendu boolean NOT NULL,
    statut_priorite character varying NOT NULL
);


ALTER TABLE public.pret OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 76285)
-- Name: pret_abonne_id_pret_abonne_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pret_abonne_id_pret_abonne_seq
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pret_abonne_id_pret_abonne_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 76287)
-- Name: pret_id_pret_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pret_id_pret_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pret_id_pret_seq OWNER TO postgres;

--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 228
-- Name: pret_id_pret_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pret_id_pret_seq OWNED BY public.pret.id_pret;


--
-- TOC entry 2781 (class 2604 OID 76289)
-- Name: abonne id_abonne; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne ALTER COLUMN id_abonne SET DEFAULT nextval('public.abonne_id_abonne_seq'::regclass);


--
-- TOC entry 2792 (class 2604 OID 76414)
-- Name: abonne_ouvrage_reservation id_abonne_ouvrage_reservation; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne_ouvrage_reservation ALTER COLUMN id_abonne_ouvrage_reservation SET DEFAULT nextval('public.abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 76290)
-- Name: adresse id_adresse; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse ALTER COLUMN id_adresse SET DEFAULT nextval('public.adresse_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 76291)
-- Name: auteur id_auteur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auteur ALTER COLUMN id_auteur SET DEFAULT nextval('public.auteur_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 76292)
-- Name: editeur id_editeur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editeur ALTER COLUMN id_editeur SET DEFAULT nextval('public.editeur_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 76293)
-- Name: employe id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe ALTER COLUMN id_employe SET DEFAULT nextval('public.employe_id_employe_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 76294)
-- Name: genre id_genre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre ALTER COLUMN id_genre SET DEFAULT nextval('public.genre_nom_seq'::regclass);


--
-- TOC entry 2787 (class 2604 OID 76295)
-- Name: liste_genre id_liste_genre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liste_genre ALTER COLUMN id_liste_genre SET DEFAULT nextval('public.liste_genre_id_liste_genre_seq'::regclass);


--
-- TOC entry 2788 (class 2604 OID 76296)
-- Name: livre id_livre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre ALTER COLUMN id_livre SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- TOC entry 2789 (class 2604 OID 76297)
-- Name: livre_auteurs id_livre_auteur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteurs ALTER COLUMN id_livre_auteur SET DEFAULT nextval('public.livre_auteurs_id_seq'::regclass);


--
-- TOC entry 2790 (class 2604 OID 76298)
-- Name: ouvrage id_ouvrage; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage ALTER COLUMN id_ouvrage SET DEFAULT nextval('public.ouvrage_id_ouvrage_seq'::regclass);


--
-- TOC entry 2791 (class 2604 OID 76299)
-- Name: pret id_pret; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret ALTER COLUMN id_pret SET DEFAULT nextval('public.pret_id_pret_seq'::regclass);


--
-- TOC entry 2822 (class 2606 OID 76419)
-- Name: abonne_ouvrage_reservation abonne_ouvrage_reservation_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne_ouvrage_reservation
    ADD CONSTRAINT abonne_ouvrage_reservation_pk PRIMARY KEY (id_abonne_ouvrage_reservation);


--
-- TOC entry 2794 (class 2606 OID 76301)
-- Name: abonne abonne_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne
    ADD CONSTRAINT abonne_pk PRIMARY KEY (id_abonne);


--
-- TOC entry 2796 (class 2606 OID 76303)
-- Name: adresse adresse_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse
    ADD CONSTRAINT adresse_pk PRIMARY KEY (id_adresse);


--
-- TOC entry 2798 (class 2606 OID 76305)
-- Name: auteur auteur_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pk PRIMARY KEY (id_auteur);


--
-- TOC entry 2800 (class 2606 OID 76307)
-- Name: bibliotheque bibliotheque_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT bibliotheque_pk PRIMARY KEY (numero_siret);


--
-- TOC entry 2802 (class 2606 OID 76309)
-- Name: editeur editeur_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editeur
    ADD CONSTRAINT editeur_pk PRIMARY KEY (id_editeur);


--
-- TOC entry 2804 (class 2606 OID 76311)
-- Name: employe employe_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT employe_pk PRIMARY KEY (id_employe);


--
-- TOC entry 2806 (class 2606 OID 76313)
-- Name: enumrole enumrole_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enumrole
    ADD CONSTRAINT enumrole_pk PRIMARY KEY (code);


--
-- TOC entry 2808 (class 2606 OID 76315)
-- Name: genre genre_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pk PRIMARY KEY (id_genre);


--
-- TOC entry 2810 (class 2606 OID 76317)
-- Name: liste_genre liste_genre_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liste_genre
    ADD CONSTRAINT liste_genre_pk PRIMARY KEY (id_liste_genre);


--
-- TOC entry 2814 (class 2606 OID 76319)
-- Name: livre_auteurs livre_auteurs_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteurs
    ADD CONSTRAINT livre_auteurs_pk PRIMARY KEY (id_livre_auteur);


--
-- TOC entry 2812 (class 2606 OID 76321)
-- Name: livre livre_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pk PRIMARY KEY (id_livre);


--
-- TOC entry 2817 (class 2606 OID 76323)
-- Name: ouvrage ouvrage_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage
    ADD CONSTRAINT ouvrage_pk PRIMARY KEY (id_ouvrage);


--
-- TOC entry 2820 (class 2606 OID 76325)
-- Name: pret pret_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT pret_pk PRIMARY KEY (id_pret);


--
-- TOC entry 2818 (class 1259 OID 76326)
-- Name: fki_abonne_pret_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_abonne_pret_fk ON public.pret USING btree (id_abonne);


--
-- TOC entry 2815 (class 1259 OID 76327)
-- Name: fki_bibliotheque_ouvrage_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_bibliotheque_ouvrage_fk ON public.ouvrage USING btree (siret_bibliotheque);


--
-- TOC entry 2839 (class 2606 OID 76420)
-- Name: abonne_ouvrage_reservation abonne_abonne_ouvrage_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne_ouvrage_reservation
    ADD CONSTRAINT abonne_abonne_ouvrage_reservation_fk FOREIGN KEY (id_abonne) REFERENCES public.abonne(id_abonne);


--
-- TOC entry 2837 (class 2606 OID 76328)
-- Name: pret abonne_pret_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT abonne_pret_fk FOREIGN KEY (id_abonne) REFERENCES public.abonne(id_abonne) NOT VALID;


--
-- TOC entry 2823 (class 2606 OID 76333)
-- Name: abonne adresse_abonne_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne
    ADD CONSTRAINT adresse_abonne_fk FOREIGN KEY (id_adresse) REFERENCES public.adresse(id_adresse);


--
-- TOC entry 2826 (class 2606 OID 76338)
-- Name: bibliotheque adresse_bibliotheque_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bibliotheque
    ADD CONSTRAINT adresse_bibliotheque_fk FOREIGN KEY (id_adresse) REFERENCES public.adresse(id_adresse);


--
-- TOC entry 2827 (class 2606 OID 76343)
-- Name: employe adresse_employe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT adresse_employe_fk FOREIGN KEY (id_adresse) REFERENCES public.adresse(id_adresse);


--
-- TOC entry 2833 (class 2606 OID 76348)
-- Name: livre_auteurs auteur_livre_auteurs_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteurs
    ADD CONSTRAINT auteur_livre_auteurs_fk FOREIGN KEY (id_auteur) REFERENCES public.auteur(id_auteur);


--
-- TOC entry 2824 (class 2606 OID 76353)
-- Name: abonne bibliotheque_abonne_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne
    ADD CONSTRAINT bibliotheque_abonne_fk FOREIGN KEY (bibliotheque) REFERENCES public.bibliotheque(numero_siret);


--
-- TOC entry 2828 (class 2606 OID 76358)
-- Name: employe bibliotheque_employe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT bibliotheque_employe_fk FOREIGN KEY (bibliotheque) REFERENCES public.bibliotheque(numero_siret);


--
-- TOC entry 2835 (class 2606 OID 76363)
-- Name: ouvrage bibliotheque_ouvrage_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage
    ADD CONSTRAINT bibliotheque_ouvrage_fk FOREIGN KEY (siret_bibliotheque) REFERENCES public.bibliotheque(numero_siret) NOT VALID;


--
-- TOC entry 2832 (class 2606 OID 76368)
-- Name: livre editeur_livre_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT editeur_livre_fk FOREIGN KEY (id_editeur) REFERENCES public.editeur(id_editeur);


--
-- TOC entry 2825 (class 2606 OID 76373)
-- Name: abonne enumrole_abonne_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne
    ADD CONSTRAINT enumrole_abonne_fk FOREIGN KEY (role) REFERENCES public.enumrole(code);


--
-- TOC entry 2829 (class 2606 OID 76378)
-- Name: employe enumrole_employe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT enumrole_employe_fk FOREIGN KEY (role) REFERENCES public.enumrole(code);


--
-- TOC entry 2830 (class 2606 OID 76383)
-- Name: liste_genre genre_liste_genre_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liste_genre
    ADD CONSTRAINT genre_liste_genre_fk FOREIGN KEY (id_genre) REFERENCES public.genre(id_genre);


--
-- TOC entry 2831 (class 2606 OID 76388)
-- Name: liste_genre livre_liste_genre_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liste_genre
    ADD CONSTRAINT livre_liste_genre_fk FOREIGN KEY (id_livre) REFERENCES public.livre(id_livre);


--
-- TOC entry 2834 (class 2606 OID 76393)
-- Name: livre_auteurs livre_livre_auteurs_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre_auteurs
    ADD CONSTRAINT livre_livre_auteurs_fk FOREIGN KEY (id_livre) REFERENCES public.livre(id_livre);


--
-- TOC entry 2836 (class 2606 OID 76398)
-- Name: ouvrage livre_ouvrage_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ouvrage
    ADD CONSTRAINT livre_ouvrage_fk FOREIGN KEY (id_livre) REFERENCES public.livre(id_livre);


--
-- TOC entry 2840 (class 2606 OID 76425)
-- Name: abonne_ouvrage_reservation ouvrage_abonne_ouvrage_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonne_ouvrage_reservation
    ADD CONSTRAINT ouvrage_abonne_ouvrage_reservation_fk FOREIGN KEY (id_ouvrage) REFERENCES public.ouvrage(id_ouvrage);


--
-- TOC entry 2838 (class 2606 OID 76403)
-- Name: pret ouvrage_pret_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pret
    ADD CONSTRAINT ouvrage_pret_fk FOREIGN KEY (id_ouvrage) REFERENCES public.ouvrage(id_ouvrage);


-- Completed on 2021-11-11 11:54:42

--
-- PostgreSQL database dump complete
--

