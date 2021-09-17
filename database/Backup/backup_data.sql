--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2021-03-10 14:02:11

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

--
-- TOC entry 2977 (class 0 OID 42883)
-- Dependencies: 224
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (1, '25', 'rue du manoir', NULL, 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (4, '58', 'chemin du comics', NULL, 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (2, '36', 'rue d''Arkham', 'derrière la maison 35', 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (3, '78', 'Route du GCPD', NULL, 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (41, '15', 'rue des marroniers', '', 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (39, '15', 'rue de l''égalité', 'tout en haut', 'NJ 12345', 'Gotham');
INSERT INTO public.adresse (id_adresse, numero, rue, complement, code_postal, ville) VALUES (40, '15', 'chemin de la coupe', '', 'NJ 12345', 'Gotham');


--
-- TOC entry 2978 (class 0 OID 42892)
-- Dependencies: 225
-- Data for Name: bibliotheque; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200177', 'Bibliothèque Bruce Wayne', 'BBW', 1);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200568', 'Bibliothèque Alfred', 'BAL', 3);
INSERT INTO public.bibliotheque (numero_siret, nom, code, id_adresse) VALUES ('18004625200356', 'Bibliothèque Robin', 'BRO', 2);


--
-- TOC entry 2975 (class 0 OID 42873)
-- Dependencies: 222
-- Data for Name: enumrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enumrole (code, status, description) VALUES (1, 'Abonné', 'Abonné');
INSERT INTO public.enumrole (code, status, description) VALUES (2, 'Administrateur', 'Administrateur');
INSERT INTO public.enumrole (code, status, description) VALUES (3, 'Directeur', 'Directeur des bibliothèques');
INSERT INTO public.enumrole (code, status, description) VALUES (4, 'Gérant', 'Responsable de la bibliothèque');
INSERT INTO public.enumrole (code, status, description) VALUES (5, 'Employé', 'Employé dans une bibliothèque');


--
-- TOC entry 2982 (class 0 OID 42913)
-- Dependencies: 229
-- Data for Name: abonne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.abonne (id_abonne, nom, prenom, pseudo, email, mot_de_passe, numero_abonne, date_creation_du_compte, role, id_adresse, bibliotheque) VALUES (34, 'Lardon', 'Alexandre', 'alouadmin', 'alexandre.lardon@yahoo.fr', '$2a$12$ZJXF.1iQ9yEfZDZh1PTvGuOBCFhQYd/B4XsGA6lhSCg.ozlny7LM2', '456132246', '2021-01-26', 1, 39, '18004625200177');
INSERT INTO public.abonne (id_abonne, nom, prenom, pseudo, email, mot_de_passe, numero_abonne, date_creation_du_compte, role, id_adresse, bibliotheque) VALUES (36, 'Lardon', 'Alexandre', 'aloux', 'alexandre.lardon@gmail.com', '$2a$12$Gm6Xj9YsN4flAJ8hd1xaBeuKgfDJKF/EI57AiPHj0IdbjVvRfNZqu', '456132248', '2021-02-15', 1, 41, '18004625200177');
INSERT INTO public.abonne (id_abonne, nom, prenom, pseudo, email, mot_de_passe, numero_abonne, date_creation_du_compte, role, id_adresse, bibliotheque) VALUES (35, 'Bertrand', 'François', 'beber', 'alexandre.lardon69@gmail.com', '$2a$12$Y5cLuwAaLVO8lk7dotuA4OBg8thmJeuNMaZgOoGDwbDxDvwItY1YW', '456132247', '2021-02-05', 1, 40, '18004625200177');


--
-- TOC entry 2968 (class 0 OID 42831)
-- Dependencies: 215
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (1, 'Hugo', 'Victor', '1802-02-26', '1885-06-22', 'Victor Hugo est un poète, dramaturge, écrivain, romancier et dessinateur romantique français, né le 7 ventôse an X (26 février 1802) à Besançon et mort le 22 mai 1885 à Paris. Il est considéré comme l''un des plus importants écrivains de langue française. Il est aussi une personnalité politique et un intellectuel engagé qui a eu un rôle idéologique majeur et occupe une place marquante dans l''histoire des lettres françaises au XIXe siècle, dans des genres et des domaines d’une remarquable variété.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (2, 'Saint-Exupery', 'Antoine', '1900-06-29', '1944-07-31', 'Antoine de Saint-Exupéry, né le 29 juin 1900 à Lyon et disparu en vol le 31 juillet 1944 au large de Marseille, est un écrivain, poète, aviateur et reporter français.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (3, 'Coelho', 'Paulo', '1947-08-24', NULL, 'Paulo Coelho de Souza, né le 24 août 1947 à Rio de Janeiro, est un romancier, journaliste et un interprète brésilien. Il a acquis une renommée internationale avec la publication de L''Alchimiste, vendu à 85 millions d''exemplaires. En ajoutant ses autres ouvrages, ce sont 350 millions d''exemplaires vendus à travers le monde. Ses romans ont été traduits dans 81 langues du monde entier. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (4, 'Obama', 'Barack', '1961-08-04', NULL, 'Barack Obama né le 4 août 1961 à Honolulu (Hawaï), est un homme d''État américain. Il est le 44e président des États-Unis, en fonction du 20 janvier 2009 au 20 janvier 2017. Fils d''un Kényan noir et d''une Américaine blanche du Kansas d’ascendance anglaise et irlandaise, il est élevé durant plusieurs années en Indonésie. Diplômé de l''université Columbia de New York et de la faculté de droit de Harvard, il est, en 1990, le premier Afro-Américain à présider la prestigieuse Harvard Law Review. Après avoir été travailleur social dans les quartiers sud de Chicago durant les années 1980, puis avocat en droit civil à sa sortie de Harvard, il enseigne le droit constitutionnel à l''université de Chicago de 1992 à 2004. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (5, 'Tolle', 'Eckhart', '1948-02-16', NULL, 'Eckhart Tolle, de son vrai nom Ulrich Leonard Tolle, né le 16 février 1948 à Lünen (Allemagne), est un écrivain et conférencier canadien d''origine allemande, auteur des best-sellers Le Pouvoir du moment présent et Nouvelle Terre. Tolle raconte qu’il s''est senti déprimé toute la première partie de sa vie jusqu’à ce qu’il connût, à l’âge de 29 ans, une « transformation intérieure » à la suite de laquelle il passa plusieurs années sans emploi à vagabonder « dans un état de félicité profonde » avant de devenir enseignant spirituel. Plus tard, il déménagea en Amérique du Nord où il commença à écrire son premier livre, Le Pouvoir du moment présent, qui fut publié en 1997 aux États-Unis et est devenu un best-seller international traduit en 33 langues1. Tolle s’installa ensuite à Vancouver au Canada où il vit depuis. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (6, 'Pagnol', 'Marcel', '1895-02-28', '1974-05-18', 'Marcel Pagnol est un écrivain, dramaturge, cinéaste et producteur français, né le 28 février 1895 à Aubagne (Bouches-du-Rhône) et mort à Paris le 18 avril 1974. Il devient célèbre avec Marius, pièce représentée au théâtre en mars 1929. Il fonde à Marseille en 1934 sa propre société de production et ses studios de cinéma, et réalise de nombreux films avec les grands acteurs de la période (en particulier Raimu, Fernandel et Pierre Fresnay), dont Angèle (1934), Regain (1937) et La Femme du boulanger (1938).');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (7, 'Morris', ' ', '2020-12-01', '2001-01-16', 'Maurice de Bevere, dit Morris, né le 1er décembre 1923 à Courtrai et mort le 16 juillet 2001 à Bruxelles, est un auteur de bandes dessinées belge connu comme créateur en 1946 de Lucky Luke, série populaire qu''il a dessinée jusqu''à sa mort, seul ou en collaboration avec divers scénaristes, dont René Goscinny. Il a qualifié pour la première fois la bande dessinée de « neuvième art » dans le journal Spirou, en 1965.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (8, 'Lucas', 'George', '1944-05-14', NULL, 'George Lucas est un réalisateur, scénariste et producteur américain né le 14 mai 1944 à Modesto en Californie. Issu de l''école de cinéma de l''université de Californie du Sud à Los Angeles, il cofonde avec son ami Francis Ford Coppola le studio American Zoetrope puis crée sa propre société de production : Lucasfilm. Il commence sa carrière de réalisateur avec les films THX 1138 en 1971 et American Graffiti en 1973. Il connaît ensuite la consécration avec les deux premières trilogies cinématographiques Star Wars. Énorme succès au box-office, la saga galactique fait de lui un des cinéastes les plus reconnus et fortunés du cinéma américain mais aussi complique sa vie, notamment à cause de ses relations parfois difficiles avec les fans.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (9, 'Tolkien', 'J.R.R.', '1892-01-03', '1973-09-02', 'John Ronald Reuel Tolkien, plus connu sous la forme J. R. R. Tolkien, est un écrivain, poète, philologue, essayiste et professeur d’université britannique, né le 3 janvier 1892 à Bloemfontein (État libre d''Orange) et mort le 2 septembre 1973 à Bournemouth (Royaume-Uni). Il est principalement connu pour ses romans Le Hobbit et Le Seigneur des anneaux.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (10, 'Hergé', ' ', '1907-05-22', '1983-03-03', 'Georges Remi dit Hergé, né le 22 mai 1907 à Etterbeek et mort le 3 mars 1983 à Woluwe-Saint-Lambert, est un auteur belge de bande dessinée principalement connu pour Les Aventures de Tintin, désignée aujourd''hui comme l''une des bandes dessinées européennes les plus populaires du XXe siècle.dit Hergé, né le 22 mai 1907 à Etterbeek et mort le 3 mars 1983 à Woluwe-Saint-Lambert, est un auteur belge de bande dessinée principalement connu pour Les Aventures de Tintin, désignée aujourd''hui comme l''une des bandes dessinées européennes les plus populaires du XXe siècle.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (11, 'Goscinny', 'René', '1926-08-14', '1977-11-05', 'René Goscinny, né le 14 août 1926 à Paris où il est mort le 5 novembre 1977, est un scénariste de bande dessinée, journaliste, écrivain et humoriste français, également producteur, réalisateur et scénariste de films.

Il est l''un des fondateurs et rédacteurs en chef de Pilote, l''un des principaux journaux français de bande dessinée. Créateur d''Astérix avec Albert Uderzo, d''Iznogoud avec Jean Tabary, auteur du Petit Nicolas, personnage créé et dessiné par Jean-Jacques Sempé, scénariste de nombreux albums de Lucky Luke créé par Morris. Il est l’un des auteurs français les plus lus au monde : l’ensemble de son œuvre représente environ 500 millions d’ouvrages vendus.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (12, 'Hillmann', 'Jesta', '1992-02-25', NULL, 'Jesta Hillmann est une candidate de télé-réalité et une influenceuse née le 25 février 1992.
Ancienne Miss régionale – elle a été élue Miss Prestige Albigeois Midi-Pyrénées 2013, elle participe en 2016 à l’émission Koh-Lanta : L’île au trésor. Elle se hisse jusqu’en finale, mais perd face à Benoît Assadi.
A défaut d’avoir remporté l’aventure, elle a trouvé l’amour grâce à l’émission. En effet, Jesta Hillmann est aujourd’hui en couple avec celui qui a été son adversaire, Benoît Assadi.
Les deux tourtereaux se sont mariés en juin 2019. Le 16 juillet 2019, les deux aventuriers sont devenus parents pour la première fois. Ils ont accueilli un petit garçon prénommé Juliann.
Après Koh-Lanta, Jesta Hillman a participé à d’autres émissions de télé-réalité comme Ninja Warrior 2 (2017), La Villa, La bataille des couples (2018) ou encore Mamans et célèbres (2020).');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (13, 'Le Tellier', 'Hervé', '1957-05-21', NULL, 'Hervé Le Tellier est un écrivain français né le 21 avril 1957.Auteur de romans, nouvelles, poésies, théâtre. Il a été coopté à l''Oulipo (Ouvroir de Littérature Potentielle) en 1992 (simultanément au poète allemand Oskar Pastior), et a publié sur l''Ouvroir un ouvrage de référence : Esthétique de l''Oulipo. Il participé à l''aventure de la série « Le Poulpe », avec un roman, La Disparition de Perek, adapté également en bande dessinée. Mathématicien de formation, puis journaliste — diplômé du Centre de formation des journalistes à Paris (promotion 1983) il est linguiste et spécialiste des littératures à contraintes. Éditeur, il a fait publier plusieurs ouvrages au Castor Astral comme What a man !, de Georges Perec, et Je me souviens de « Je me souviens », de Roland Brasseur. Il collabore quotidiennement, depuis 2002, à la lettre électronique matinale du journal Le Monde, par un billet d''humeur intitulé Papier de verre. Son roman Toutes les familles heureuses (JC Lattès, 2017) fait partie de la première sélection du Prix Renaudot 2017.
Il collabore à l''émission de France-Culture "Les papous dans la tête".');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (14, 'King', 'Stephen', '1947-09-21', NULL, 'Stephen King est un écrivain américain né le 21 septembre 1947 à Portland, dans le Maine. Mondialement célèbre, ses romans relèvent majoritairement du genre fantastique, horreur, science-fiction et roman policier. Maintes fois récompensé pour ses ouvrages, l''écrivain Stephen King produit de nombreux best-sellers adaptés au cinéma et à la télévision, notamment les films cultes Shining ou encore Carrie.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (15, 'Giordano', 'Raphaëlle', '1974-05-19', NULL, 'Raphaëlle Giordano est une écrivaine, artiste peintre et coach en développement personnel française née en 1974 à Paris. Véritable phénomène d’édition, elle remporte un énorme succès avec son premier roman Ta deuxième vie commence quand tu comprends que tu n’en as qu’une.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (16, 'Lignac', 'Cyril', '1977-11-05', NULL, 'Il est chef et propriétaire du restaurant Le Chardenoux dans le 11e arrondissement, des restaurants Aux Prés et Le Bar des Prés dans le 6e arrondissement, du bar à cocktails Dragon dans le 6e arrondissement, des boutiques La Pâtisserie Cyril Lignac dans les 6e, 11e, 15e, 16e et 17e arrondissements et de la boutique La Chocolaterie Cyril Lignac dans le 11e arrondissement de Paris.

Il a été chef-propriétaire du restaurant gastronomique Le Quinzième (1 étoile Michelin) dans le 15e arrondissement de Paris jusqu''à sa fermeture en juillet 2019.

Il est animateur dans de nombreux programmes culinaires sur la chaîne M6 et est auteur de livres de recettes chez Hachette Pratique et aux Editions de La Martinière principalement. En 2012, un sondage le consacre « Chef préféré à la télévision » en France3. En 2019, il fait partie des trois chefs français les plus suivis sur Twitter4. En décembre 2020, il compte 2,6 millions d''abonnés sur son compte Instagram. ');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (17, 'Ruiz', 'Miguel', '1952-10-04', NULL, 'Miguel Ángel Ruiz (ou Don Miguel Ruiz) est un auteur mexicain, chamane (se disant nagual) et enseignant, né en 1952. Son ouvrage Les quatre accords toltèques est un best-seller de la littérature New Age.

Né d''une mère curandera et d''un grand-père nagual (chaman toltèque), il fait des études de médecine pour devenir chirurgien. Sa vie bascule lors d''une expérience de mort imminente qui l''aurait inspiré à chercher des réponses aux questions de l''existence dans la tradition toltèque.

Son livre s''est vendu à plus de 4 millions d''exemplaires. Il a été reçu par l''animatrice de télévision américaine Oprah Winfrey dans son émission à ce sujet.

En 2002, il subit une crise cardiaque à laquelle il survit. En 2010, il bénéficie avec succès d''une greffe du cour.');
INSERT INTO public.auteur (id_auteur, nom, prenom, date_de_naissance, date_deces, commentaire) VALUES (18, 'Oda', 'Eiichiro', '1975-01-01', NULL, 'Eiichiro Oda est né le 1er janvier 1975 à Kumamoto. Il publie pour la première fois de façon professionnelle en 1993 Kami kara mirai no puresento dans le Jump Original d''octobre et remporte le concours de talent mensuel organisé par la rédaction du Weekly Shonen Jump, le Tenkaichi Manga Award, avec sa nouvelle intitulée Ikki Yako. En 1994, Eiichiro Oda abandonne l''université et se rend à Tokyo pour devenir l''assistant de trois mangakas travaillant pour le Weekly Jump : Shinobu Kaitani, Masaya Tokuhiro et Nobuhiro Watsuki (Kenshin le vagabond). Parallèlement, Eiichiro Oda publie deux nouvelles : Monstres dans le Autumn Special de 1994 et la première des deux versions de Romance Dawn dans le Summer Special de 1996. Quelques mois plus tard, il fait enfin sa première apparition dans les pages du Weekly Shonen Jump avec la deuxième version de Romance Dawn. Eiichiro Oda découvre véritablement le succès avec la publication du premier chapitre de son ouvre phare, One Piece, dans le numéro 34 du Weekly Shonen Jump de 1997. La série gagne très vite en popularité et devient une référence incontournable du genre shonen au même titre que Dragon Ball de Akira Toriyama. Oda est l''un des rares mangakas à avoir dépassé la barre des 100 millions de mangas vendus pour une ouvre au Japon et chaque nouveau tome de la série se vend à plus d''un million d''exemplaires.');


--
-- TOC entry 2984 (class 0 OID 51346)
-- Dependencies: 231
-- Data for Name: editeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (1, 'Gallimard');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (2, 'J''ai Lu');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (3, 'casterman');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (4, 'Omnibus');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (5, 'Dargaud');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (6, 'Fayard');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (7, 'Hachette Pratique');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (8, 'Albin Michel');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (9, 'Plon');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (10, 'La Martiniere Eds De');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (11, 'Glénat');
INSERT INTO public.editeur (id_editeur, nom_maison_edition) VALUES (12, 'Poche');


--
-- TOC entry 2980 (class 0 OID 42902)
-- Dependencies: 227
-- Data for Name: employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employe (id_employe, nom, prenom, matricule, date_embauche, date_depart, email, mot_de_passe, bibliotheque, role, id_adresse) VALUES (1, 'Dupont', 'Régis', '168954632', '2017-12-15', NULL, 'dupont.regis@yahoo.fr', '123', '18004625200177', 5, 4);


--
-- TOC entry 2972 (class 0 OID 42853)
-- Dependencies: 219
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.genre (id_genre, nom, description) VALUES (1, 'Conte philosophique', 'Le conte philosophique, genre littéraire né au XVIIIe siècle, est une histoire fictive, critique de la société et du pouvoir en place pour transmettre des idées, concepts à portée philosophique : mœurs de la noblesse, régimes politiques, fanatisme religieux ou encore certains courants philosophiques. Il reprend la construction du conte et utilise certaines de ses formulations comme « il était une fois », dans le but de se soustraire à la censure qui sévit à cette époque. Il appartient, comme lui, au genre de l''apologue, court récit allégorique et argumentatif dont on tire une morale, et qui regroupe aussi, entre autres, la fable et l''utopie.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (2, 'Roman', 'Le roman est un genre littéraire caractérisé essentiellement par une narration fictionnelle et dont la première apparition peut être datée du XIIe siècle. Initialement écrit en vers qui jouent sur les assonances, il est écrit en prose dès le XIIe siècle et se distingue du conte ou de l''épopée par sa vocation à être lu individuellement et non écouté.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (3, 'Science-Fiction', 'La science-fiction n''est-elle pas un genre en soi ? C''est ce que suggère cette définition issue d''une ancienne encyclopédie « Focus » : « Genre où l''auteur donne libre cours à son imagination en s''appuyant sur les progrès de la science et sur ses possibilités de découvertes ».

Il existe un grand nombre de genres et de sous-genres de la science-fiction, qui se confondent et s''articulent entre des thématiques, des modes opératoires, des supports de diffusion et enfin des écoles par nationalité des auteurs');
INSERT INTO public.genre (id_genre, nom, description) VALUES (4, 'Autobiographie', 'L’autobiographie est un genre littéraire et artistique. Son étymologie grecque définit le fait d’écrire (graphè, graphie) sur sa propre vie (auto, soi ; et bios, vie). Au sens large l’autobiographie se caractérise donc au moins par l’identité de l’auteur, du narrateur et du personnage. Le mot est assez récent, il n’est fabriqué qu’au début du xixe siècle (1815 en anglais, 1832 pour l’adjectif et 1842 pour le substantif en français). L’approche actuelle parle dans ce cas plutôt de « genre autobiographique », réservant à « autobiographie » un sens plus étroit qu’a établi Philippe Lejeune dans les années 1970.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (5, 'Bande dessinée', 'Une bande dessinée (dénomination communément abrégée en BD ou en bédé) est une forme d''expression artistique, souvent désignée comme le « neuvième art », utilisant une juxtaposition de dessins (ou d''autres types d''images fixes, mais pas uniquement photographiques), articulés en séquences narratives et le plus souvent accompagnés de textes (narrations, dialogues, onomatopées). Will Eisner, un des plus grands auteurs de bande dessinée, l''a définie (avant l''émergence d''Internet) comme « la principale application de l''art séquentiel au support papier »');
INSERT INTO public.genre (id_genre, nom, description) VALUES (6, 'Aventure', 'Le roman d’aventures est un type de roman populaire qui met particulièrement l''accent sur l''action en multipliant les péripéties plutôt violentes, dans lequel le héros est plutôt masculin et jeune, en général positifs, et où le souci de la forme littéraire est relativement peu important.');
INSERT INTO public.genre (id_genre, nom, description) VALUES (7, 'Humoristique', 'La bande dessinée humoristique ou bande dessinée d''humour est le premier type de bande dessinée à avoir été développé. Les bandes dessinées humoristiques existent dans tous types de formats (comic strip, Gag, récit complet, etc.) et peuvent s''adresser selon le type d''humour aux enfants comme aux adultes, voire à tous les publics grâce à différents niveaux de lecture1. Il est resté le genre dominant jusqu''à l''émergence des bandes dessinées d''aventure réalistes au tournant des années 1930 aux États-Unis puis dans le reste du monde');
INSERT INTO public.genre (id_genre, nom, description) VALUES (8, 'Développement Personnel', 'Le développement personnel est un ensemble hétéroclite de pratiques, appartenant à divers courants de pensées1, qui ont pour objectif l''amélioration de la connaissance de soi2, la valorisation des talents et potentiels3, l''amélioration de la qualité de vie personnelle, la réalisation de ses aspirations et de ses rêves4. Le développement personnel n''est toutefois pas une sorte de psychothérapie1 et résulte d''influences multiples. En effet, la psychologie et la philosophie, et souvent la diététique et la pratique du sport, fondent généralement les pratiques des acteurs du développement personnel moderne ; d''autres y rattachent également des notions religieuses ou relevant de l''ésotérisme');
INSERT INTO public.genre (id_genre, nom, description) VALUES (9, 'Fantastique', 'Une œuvre est de genre Fantastique quand elle relate des événements totalement étranges, le plus souvent irrationnels ou incompréhensibles, hors d''atteinte de la puissance humaine ou de l''explication rationnelle (apparition de doubles, de fantômes, de spectres ou de revenants ; labyrinthes étranges ; rêves ou prémonitions ; réincarnation ; événements maléfiques inexpliqués ou apparition de démons ; apparition d''anges ou d''anges gardien ; objets usuellement inertes mais devenus vivants, etc).');
INSERT INTO public.genre (id_genre, nom, description) VALUES (10, 'Gastronomie', 'La gastronomie est la connaissance raisonnée de tout ce qui se rapporte à l''être humain en tant qu''il se nourrit. Certains restreignent cela à l''ensemble des règles (fluctuantes, selon pays, classes sociales et modes) qui définissent l''art de faire bonne chère1.

Selon l''Académie française, l''expression « faire bonne chère », qui signifiait « faire bon accueil », fut utilisée dès le XIXe siècle au sens de « faire un bon repas », un bon repas étant un élément d''un bon accueil. Dans ce sens, « chère » comprend tout ce qui concerne la quantité, la qualité et la préparation des mets');
INSERT INTO public.genre (id_genre, nom, description) VALUES (11, 'Horreur', 'L''horreur est un genre littéraire s''inscrivant dans le registre de la peur. Ce genre met souvent en scène des phénomènes surnaturels (et des créatures à l''avenant : vampires, fantômes, loup-garous et autres monstres) mais aussi des psychopathes et des tueurs en séries. Le roman d''horreur cherche à susciter chez le lecteur l''angoisse et l''effroi, ou à tout le moins à le mettre mal à l''aise (l''inquiétante étrangeté par laquelle on a parfois cherché à définir le fantastique est ici exacerbée).');
INSERT INTO public.genre (id_genre, nom, description) VALUES (12, 'Manga', 'Un Manga est une bande dessinnée japonnaise. Le mot « manga » est par ailleurs parfois utilisé pour désigner, par extension, une bande dessinée non japonaise respectant les codes des productions populaires japonaises ou pour nommer, par métonymie, d''autres produits visuels rappelant certaines de ces bandes dessinées (dessins animés, style graphique, etc.). ');


--
-- TOC entry 2962 (class 0 OID 42798)
-- Dependencies: 209
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (1, 'Le Petit Prince', '« Le premier soir je me suis donc endormi sur le sable à mille milles de toute terre habitée. J''étais bien plus isolé qu''un naufragé sur un radeau au milieu de l''Océan. Alors vous imaginez ma surprise, au lever du jour, quand une drôle de petite voix m''a réveillé. Elle disait :
- S''il vous plaît... dessine-moi un mouton !
- Hein !
- Dessine-moi un mouton...
J''ai sauté sur mes pieds comme si j''avais été frappé par la foudre. J''ai bien frotté mes yeux. J''ai bien regardé.
Et j''ai vu un petit bonhomme tout à fait extraordinaire qui me considérait gravement. » ', '2007-03-15', '978-2-070-61275-8', 'Français', 113, 'petit_prince_petit.jpg', 'petit_prince_grand.jpg', 1, 'petit_prince_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (8, 'Star Wars - La Guerre des Etoiles ', 'La guerre des étoiles , première épisode de la trilogie fondatrice.
Il y a bien longtemps, dans une galaxie lointaine, très lointaine...
C''est une époque de guerre civile. À bord de vaisseaux spatiaux opérant à partir d''une base inconnue, les Rebelles ont remporté leur première victoire sur l''abominable Empire Galactique.
Au cours de la bataille, les Rebelles ont réussi à dérober les plans secrets de l''arme absolue de l''Empire : L''Étoile Noire, une station spatiale dotée d''un armement assez puissant pour annihiler une planète tout entière.
Poursuivie par les sbires sinistres de l''Empire, la princesse Leia regagne sa base dans son vaisseau cosmique, porteuse des plans volés à l''ennemi qui pourraient sauver son peuple et restaurer la liberté dans la galaxie...', '2015-11-26', '978-2-785-00440-9', 'Français', 1012, 'star_wars_petit.jpg', 'star_wars_grand.jpg', 4, 'star_wars_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (9, 'Le Seigneur des Anneaux', 'Une contrée paisible où vivent les Hobbits. Un anneau magique à la puissance infinie. Sauron, son créateur, prêt à dévaster le monde entier pour récupérer son bien. Frodon, jeune Hobbit, détenteur de l''Anneau malgré lui. Gandalf, le Magicien, venu avertir Frodon du danger. Et voilà déjà les Cavaliers Noirs qui approchent... C''est ainsi que tout commence en Terre du Milieu entre la Comté et Mordor. C''est ainsi que la plus grande légende est née.', '2018-09-13', '978-2-266-28626-8', 'Français', 1592, 'seigneurs_petit.jpg', 'seigneurs_grand.jpg', 1, 'seigneurs_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (7, 'Daisy Town', 'Daisy Town, une grande ville moderne du Far West, est livrée à la corruption. Il faudra peu de temps à Lucky Luke pour rétablir l''ordre. Mais c''est sans compter sur l''arrivée des Dalton en ville qui commencent à terroriser la population couarde et tentent de se faire élire shérifs à la place de Lucky Luke. Après s''être fait bouter en bonne est due forme de la ville, ils vont tout faire pour se venger et faire raser la ville.', '2001-02-20', '978-2-884-71036-7', 'Français', 46, 'daisy_town_petit.jpg', 'daisy_town_grand.jpg', 5, 'daisy_town_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (2, 'Les Misérables', 'Jean Valjean, les Thénardier, Cosette sont entrés au panthéon de la littérature. Porté par ses personnages, Victor Hugo a écrit avec les Misérables un hymne à la littérature, profondément ancré dans la mentalité collective de son temps. Une épopée du peuple aux prises avec l''Histoire, pleine debruits et de fureur.', '2008-08-22', '978-2-035-83425-6', 'Français', 270, 'les_miserables_petit.jpg', 'les_miserables_grand.jpg', 1, 'les_miserables_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (10, 'On a marché sur la Lune', 'Après un voyage spatial mouvementé, Tintin, Milou, le capitaine Haddock, le professeur Tournesol et l''ingénieur Wolff atteignent la Lune. L''exploration de ce nouveau monde leur réserve encore bien des surprises... ', '1993-05-04', '978-2-203-00116-9', 'Français', 61, 'tintin_petit.jpg', 'tintin_grand.jpg', 3, 'tintin_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (5, 'Le Pouvoir du Moment Présent', 'Le pouvoir du moment présent est probablement l''un des livres les plus importants de notre époque.
Son enseignement simple et néanmoins profond a aidé des millions de gens à travers le monde à trouver la paix intérieure et à se sentir plus épanouis dans leur vie. Au coeur de cet enseignement se trouve la transformation de la conscience : en vivant dans l''instant présent, nous transcendons notre ego et accédons à " un état de grâce, de légèreté et de bien-être ". Ce livre a le pouvoir de métamorphoser votre vie par une expérience unique.', '2010-08-28', '978-2-290-02020-3', 'Français', 253, 'present_petit.jpg', 'present_grand.jpg', 2, 'present_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (3, 'L''Alchimiste', 'santiago, un jeune berger andalou, part à la recherche d''un trésor enfoui au pied des pyramides.
lorsqu''il rencontre l''alchimiste dans le désert, celui-ci lui apprend à écouter son coeur, à lire les signes du destin et, par-dessus tout, à aller au bout de son rêve.merveilleux conte philosophique destiné à l''enfant qui sommeille en chaque être, ce livre a déjà marqué une génération de lecteurs.', '2007-03-01', '978-2-290-00444-9', 'Français', 190, 'l_Alchimiste_petit.jpg', 'l_Alchimiste_grand.jpg', 2, 'l_Alchimiste_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (4, 'Une Terre Promise', 'Un récit fascinant et profondément intime de l''histoire en marche, par le président qui nous a insufflé la foi dans le pouvoir de la démocratie.

Dans le premier volume passionnant et très attendu de ses mémoires présidentiels, Barack Obama raconte l''histoire de son improbable odyssée, de jeune homme en quête d''identité à dirigeant du monde libre, retraçant de manière singulièrement détaillée et personnelle son éducation politique et les moments emblématiques du premier mandat de sa présidence historique - une période de transformations et de bouleversements profonds.

Barack Obama entraîne le lecteur dans un voyage fascinant, depuis ses toutes premières aspirations politiques à sa victoire cruciale aux primaires de l''Iowa, qui démontra le pouvoir de l''engagement citoyen, jusqu''à la soirée décisive du 4 novembre 2008, lorsqu''il fut élu 44e président des États-Unis, devenant ainsi le premier Afro-Américain à accéder à la fonction suprême.', '2020-11-17', '978-2-213-70612-2', 'Français', 890, 'barack_petit.jpg', 'barack_grand.jpg', 6, 'barack_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (6, 'Le Château de ma Mère', 'Le plus beau livre sur l''amitié enfantine : un matin de chasse dans les collines. Marcel rencontre le petit paysan, Lili des Bellons. Ses vacances et sa vie entière en seront illuminées.
Un an après La Gloire de mon père, Marcel Pagnol pensait conclure ses Souvenirs d''enfance avec ce Château de ma mère (1958), deuxième volet de ce qu''il considérait comme un diptyque, s''achevant sur la scène célèbre du féroce gardien effrayant la timide Augustine. Le petit Marcel, après la tendresse familiale, a découvert l''amitié avec le merveilleux Lili, sans doute le plus attachant de ses personnages. Le livre se clôt sur un épilogue mélancolique, poignante élégie au temps qui a passé. Pagnol y fait vibrer les cordes d''une gravité à laquelle il a rarement habitué ses lecteurs.
Je ris un garçon de mon âge qui me regardait sévèrement. "Il ne faut pas toucher les pièges des autres, dit-il. Un piège, c''est sacré ! - Je n''allais pas le prendre, dis-je. Je voulais voir l''oiseau. '''' Il s''approcha : c''était un petit paysan. Il était brun, avec un fin ri sage provençal, des veux noirs et de longs cils de fille. " ', '2004-08-25', '978-2-877-06508-5', 'Français', 220, 'chateau_petit.jpg', 'chateau_grand.jpg', 2, 'chateau_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (11, 'Jestatouille', 'Zéro culpabilité, un maximum de plaisir  ! 
Après sa grossesse, et 28 kilos supplémentaires, Jesta Hillmann a voulu se mettre à la cuisine afin de rééquilibrer son alimentation.
Elle a vite compris qu’elle n’était pas la seule et a décidé de partager son expérience et ses recettes avec les gens qui la suivent. Poisson aux agrumes, tarte épinards-chèvre, toast aux figues, blueberry bowl, salade sucrée-salée, cookies de falafels… Elle publie régulièrement sur Instagram ses idées de bons petits plats hyper simples à réaliser, sans ingrédients complexes et, surtout, sain et équilibrés. Le partage (de recettes, d’expériences, de conseils et de bonne humeur), c’est ce qui fait vivre sa communauté.
Ce livre réuni 65 recettes, dont une cinquantaine de recettes  inédites, à découvrir, savourer… et partager sur les réseaux sociaux !
Le compte Instagram Jestatouille, sur lequel elle partage ses recettes, a été lancé mi-avril 2020 et compte aujourd’hui + de 220 000 abonnés.', '2017-02-21', '978-2-017-13806-8', 'Français', 160, 'jestatouille_petit.jpg', 'jestatouille_grand.jpg', 7, 'jestatouille_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (12, 'L''anomalie', '"Il est une chose admirable qui surpasse toujours la connaissance, l''intelligence, et même le génie, c''est l''incompréhension."En juin 2021, un événement insensé bouleverse les vies de centaines d''hommes et de femmes, tous passagers d''un vol Paris-New York. Parmi eux : Blake, père de famille respectable et néanmoins tueur à gages ; Slimboy, pop star nigériane, las de vivre dans le mensonge ; Joanna, redoutable avocate rattrapée par ses failles ; ou encore Victor Miesel, écrivain confidentiel soudain devenu culte.Tous croyaient avoir une vie secrète. Nul n''imaginait à quel point c''était vrai.Roman virtuose où la logique rencontre le magique, L''anomalie explore cette part de nous-mêmes qui nous échappe.', '2020-08-20', '978-2-264-05682-8', 'Français', 336, 'anomalie_petit.jpg', 'anomalie_grand.jpg', 1, 'anomalie_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (13, 'Si ça saigne ', '«King maîtrise l''horreur comme nul autre, et l''auteur emblématique vous tiendra éveillé tard dans la nuit avec ces quatre histoires sur nos rêves et nos faiblesses.»USA Today ', '2021-10-02', '978-2-226-45105-7', 'Français', 464, 'saigne_petit.jpg', 'saigne_grand.jpg', 8, 'saigne_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (14, 'Le bazar du zèbre à pois', 'Auteur star, Raphaëlle Giordano revient chez Plon avec un roman réjouissant. Après le best-seller Cupidon a des ailes en carton, l''auteur du phénomène Ta deuxième vie commence quand tu comprends que tu n''en as qu''une surprend et séduit une nouvelle fois. Au meilleur d''elle-même.

" Je m''appelle Basile. J''ai commencé ma vie en montrant ma lune. Est-ce pour cela que j''ai toujours eu l''impression de venir d''une autre planète ? Je n''ai pourtant pas compris tout de suite de quel bois j''étais fait. Peut-être plus un bois de Gepetto que de meuble Ikea."', '2021-01-14', '978-2-259-27761-7', 'Français', 462, 'zebre_petit.jpg', 'zebre_grand.jpg', 9, 'zebre_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (15, 'Fait Maison', 'Cyril Lignac cuisine 45 recettes salées et sucrées pour mettre encore et toujours un peu de peps dans ton quotidien. Un tian de légumes, un burger de bœuf, des endives au jambon au maroilles ou encore une superbe tarte au citron, un gâteau de Savoie ou un pop-corn caramélisé, sauce chocolat... " Tu vas te régaler en toute simplicité ! "

Pour égayer tes déjeuners et dîners, des recettes faciles et rapides, à déguster en solo, à deux, en famille ou entre amis. Avec Cyril, le fait-maison, c''est facile ! Mets ton tablier et laisse-toi guider par ses précieux conseils et ses recettes ultra-réconfortantes.', '2020-12-10', '978-2-732-49714-3', 'Français', 112, 'fait_maison_petit.jpg', 'fait_maison_grand.jpg', 10, 'fait_maison_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (16, 'One Piece - Tome 97', 'Alors qu’ils s’apprêtent à lancer l’assaut sur Onigashima, Kinémon et ses hommes découvrent la trahison de Kanjuro et assistent médusés à l’enlèvement de Momonosuké. Mais le front commun formé par Luffy, Law et Kidd vient aussitôt raviver la flamme de l’espoir ! Les aventures de Luffy à la poursuite du One Piece continuent !', '2021-01-06', '978-2-344-04638-8', 'Français', 192, 'one_petit.jpg', 'one_grand.jpg', 11, 'one_moyen.jpg');
INSERT INTO public.livre (id_livre, titre, resume, date_edition, num_isbn13, langue, nombre_de_pages, petite_photo_couverture, grande_photo_couverture, id_editeur, moyenne_photo_couverture) VALUES (17, 'Les quatre accords toltèques', 'Castaneda a fait découvrir au grand public les enseignements des chamans mexicains qui ont pour origine la tradition toltèque, gardienne des connaissances de Quetzacoatl, le serpent à plumes. Dans ce livre, Don Miguel révèle la source des croyances limi-tatrices qui nous privent de joie et créent des souffrances inutiles. Il montre en des termes très simples comment on peut se libérer du conditionnement collectif - le rêve de la planète, basé sur la peur - afin de retrouver la dimension d''amour inconditionnel qui est à notre origine et constitue le fondement des enseignements toltèques. br/ br/ Les quatre accords proposent un puissant code de conduite capable de transformer rapidement notre vie en une expérience de liberté, de vrai bonheur et d''amour. Le monde fascinant de la Connaissance véritable et incarnée est enfin à la portée de chacun.', '2016-01-08', '978-2-889-11654-6', 'Français', 141, 'tolteque_petit.jpg', 'tolteque_grand.jpg', 12, 'tolteque_moyen.jpg');


--
-- TOC entry 2974 (class 0 OID 42864)
-- Dependencies: 221
-- Data for Name: liste_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (1, 1, 1);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (2, 2, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (3, 3, 1);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (4, 4, 4);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (5, 5, 8);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (6, 6, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (7, 7, 5);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (8, 7, 7);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (9, 8, 3);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (10, 9, 9);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (11, 10, 5);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (12, 10, 6);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (13, 11, 10);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (14, 12, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (15, 13, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (16, 13, 11);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (17, 14, 2);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (18, 15, 10);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (19, 16, 12);
INSERT INTO public.liste_genre (id_liste_genre, id_livre, id_genre) VALUES (20, 17, 8);


--
-- TOC entry 2970 (class 0 OID 42842)
-- Dependencies: 217
-- Data for Name: livre_auteurs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (1, 1, 2);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (2, 2, 1);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (3, 3, 3);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (4, 4, 4);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (5, 5, 5);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (6, 6, 6);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (7, 7, 7);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (8, 7, 11);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (9, 8, 8);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (10, 9, 9);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (11, 10, 10);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (12, 11, 12);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (13, 12, 13);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (14, 13, 14);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (15, 14, 15);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (16, 15, 16);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (17, 16, 17);
INSERT INTO public.livre_auteurs (id_livre_auteur, id_livre, id_auteur) VALUES (18, 17, 18);


--
-- TOC entry 2964 (class 0 OID 42809)
-- Dependencies: 211
-- Data for Name: ouvrage; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (2, 'BBWROM565', 2, '2019-12-19', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (9, 'BBWFAN698', 9, '2018-12-26', 3, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (16, 'BBWMAN117', 16, '2021-01-01', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (8, 'BBWSFI198', 8, '2018-12-25', 0, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (15, 'BBWGAS342', 15, '2021-01-08', 4, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (14, 'BBWDEV150', 14, '2021-01-01', 2, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (7, 'BBWBAN432', 7, '2018-12-24', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (6, 'BBWROM595', 6, '2018-12-23', 0, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (13, 'BBWHOR331', 13, '2021-01-02', 3, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (12, 'BBWROM558', 12, '2020-12-20', 2, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (23, 'BALROM595', 6, '2018-12-23', 1, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (5, 'BBWDEV98', 5, '2018-12-22', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (4, 'BBWMEM058', 4, '2018-12-21', 4, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (11, 'BBWGAS341', 11, '2021-01-06', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (10, 'BBWBAN458', 10, '2018-12-27', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (3, 'BBWCON855', 3, '2018-12-20', 2, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (29, 'BALROM558', 12, '2020-12-20', 1, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (17, 'BBWDEV151', 17, '2021-01-14', 0, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (43, 'BROGAS341', 11, '2021-01-06', 3, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (51, 'BROMEM058', 4, '2018-12-21', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (40, 'BRODEV151', 17, '2021-01-14', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (41, 'BRODEV98', 5, '2018-12-22', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (47, 'BROFAN698', 9, '2018-12-26', 0, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (50, 'BROMAN117', 16, '2021-01-01', 1, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (46, 'BROBAN432', 7, '2018-12-24', 1, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (44, 'BRODEV150', 14, '2021-01-01', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (38, 'BROSFI198', 8, '2018-12-25', 1, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (45, 'BROBAN458', 10, '2018-12-27', 1, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (48, 'BROROM565', 2, '2019-12-19', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (36, 'BROROM595', 6, '2018-12-23', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (37, 'BROROM558', 12, '2020-12-20', 0, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (35, 'BROHOR331', 13, '2021-01-02', 3, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (42, 'BROGAS342', 15, '2021-01-08', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (25, 'BALSFI198', 8, '2018-12-25', 0, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (20, 'BALCON855', 3, '2018-12-20', 3, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (22, 'BALDEV98', 5, '2018-12-22', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (32, 'BALGAS342', 15, '2021-01-08', 3, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (28, 'BALGAS341', 11, '2021-01-06', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (31, 'BALDEV150', 14, '2021-01-01', 3, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (27, 'BALBAN458', 10, '2018-12-27', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (24, 'BALBAN432', 7, '2018-12-24', 0, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (26, 'BALFAN698', 9, '2018-12-26', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (19, 'BALROM565', 2, '2019-12-19', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (33, 'BALMAN117', 16, '2021-01-01', 2, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (21, 'BALMEM058', 4, '2018-12-21', 3, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (30, 'BALHOR331', 13, '2021-01-02', 4, '18004625200568');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (34, 'BALDEV151', 17, '2021-01-14', 2, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (39, 'BROCON855', 3, '2018-12-20', 3, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (1, 'BBWCON1355', 1, '2016-08-10', 1, '18004625200177');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (49, 'BROCON1355', 1, '2016-08-10', 1, '18004625200356');
INSERT INTO public.ouvrage (id_ouvrage, code_bibliotheque, id_livre, date_ajout, nombre_exemplaires, siret_bibliotheque) VALUES (18, 'BALCON1355', 1, '2016-08-10', 2, '18004625200568');


--
-- TOC entry 2966 (class 0 OID 42820)
-- Dependencies: 213
-- Data for Name: pret; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (78, '2021-02-22', '2021-02-24', true, 15, 34, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (80, '2021-02-24', '2021-02-24', true, 11, 34, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (86, '2021-02-24', '2021-04-24', true, 8, 35, 'Prolongation', false, '3');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (85, '2021-02-24', '2021-02-24', true, 7, 35, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (84, '2021-02-24', '2021-02-24', true, 4, 35, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (88, '2021-02-24', '2021-03-24', false, 2, 34, 'Prêt en cours', false, '4');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (69, '2021-01-25', '2021-02-25', true, 17, 34, 'A rendre', false, '1');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (87, '2021-02-24', '2021-03-24', false, 17, 35, 'Prêt en cours', false, '4');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (82, '2021-02-24', '2021-02-27', true, 1, 35, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (83, '2021-01-24', '2021-02-24', true, 2, 35, 'A rendre', false, '1');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (70, '2021-02-22', '2021-03-22', false, 9, 34, 'Prêt en cours', false, '4');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (72, '2021-02-22', '2021-02-21', true, 16, 34, 'Rendu', true, '5');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (71, '2021-02-22', '2021-02-22', true, 5, 34, 'A rendre', false, '1');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (89, '2021-03-01', '2021-04-01', false, 49, 35, 'Prêt en cours', false, '4');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (67, '2021-02-21', '2021-03-01', true, 49, 34, 'A rendre', false, '1');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (68, '2021-02-02', '2021-03-02', true, 3, 34, 'A rendre', false, '1');
INSERT INTO public.pret (id_pret, date_emprunt, date_restitution, prolongation, id_ouvrage, id_abonne, statut, rendu, statut_priorite) VALUES (90, '2021-03-08', '2021-04-08', false, 18, 34, 'Prêt en cours', false, '4');


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 228
-- Name: abonne_id_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abonne_id_abonne_seq', 36, true);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 223
-- Name: adresse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_seq', 41, true);


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 214
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auteur_id_seq', 1, false);


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 230
-- Name: editeur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editeur_id_seq', 1, false);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 226
-- Name: employe_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employe_id_employe_seq', 1, false);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 218
-- Name: genre_nom_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genre_nom_seq', 13, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 233
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 9, true);


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 232
-- Name: id_adresse; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_adresse', 51, true);


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 220
-- Name: liste_genre_id_liste_genre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.liste_genre_id_liste_genre_seq', 11, true);


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 216
-- Name: livre_auteurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_auteurs_id_seq', 3, true);


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 208
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 1, false);


--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 210
-- Name: ouvrage_id_ouvrage_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ouvrage_id_ouvrage_seq', 1, false);


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 234
-- Name: pret_abonne_id_pret_abonne_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_abonne_id_pret_abonne_seq', 2, false);


--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 212
-- Name: pret_id_pret_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pret_id_pret_seq', 90, true);


-- Completed on 2021-03-10 14:02:11

--
-- PostgreSQL database dump complete
--

