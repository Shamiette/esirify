USE esirify;

DROP TABLE IF EXISTS AlbumGenre;
DROP TABLE IF EXISTS AlbumUtilisateur;
DROP TABLE IF EXISTS ArtisteAlbum;
DROP TABLE IF EXISTS ArtisteGenre;
DROP TABLE IF EXISTS GroupeArtiste;
DROP TABLE IF EXISTS MorceauAlbum;
DROP TABLE IF EXISTS MorceauArtiste;
DROP TABLE IF EXISTS MorceauGenre;
DROP TABLE IF EXISTS MorceauPlaylist;
DROP TABLE IF EXISTS MorceauUtilisateur;
DROP TABLE IF EXISTS PlaylistGenre;
DROP TABLE IF EXISTS PlaylistUtilisateur;
DROP TABLE IF EXISTS UtilisateurGenre;
DROP TABLE IF EXISTS Album;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Playlist;
DROP TABLE IF EXISTS Parole;
DROP TABLE IF EXISTS Artiste;
DROP TABLE IF EXISTS Genre;
DROP TABLE IF EXISTS Morceau;
DROP TABLE IF EXISTS AuditEcoute;

CREATE TABLE Album (
  PRIMARY KEY (idAlbum),
  idAlbum     INTEGER AUTO_INCREMENT NOT NULL,
  Titre       VARCHAR(255) NOT NULL,
  DateSortie  DATE NOT NULL,
  Description TEXT,
  LienImage   TEXT
);

CREATE TABLE AlbumGenre (
  PRIMARY KEY (idAlbum, idGenre),
  idAlbum INTEGER NOT NULL,
  idGenre INTEGER NOT NULL
);

CREATE TABLE AlbumUtilisateur (
  PRIMARY KEY (idAlbum, idUtilisateur),
  idAlbum       INTEGER NOT NULL,
  idUtilisateur INTEGER NOT NULL
);

CREATE TABLE Artiste (
  PRIMARY KEY (idArtiste),
  idArtiste   INTEGER AUTO_INCREMENT NOT NULL,
  NomDeScene  VARCHAR(255) NOT NULL,
  Prenom      VARCHAR(255),
  Nom         VARCHAR(255),
  Nationalite VARCHAR(255),
  Description TEXT
);

CREATE TABLE ArtisteAlbum (
  PRIMARY KEY (idArtiste, idAlbum),
  idArtiste INTEGER NOT NULL,
  idAlbum   INTEGER NOT NULL,
  Principal BOOLEAN DEFAULT TRUE
);

CREATE TABLE ArtisteGenre (
  PRIMARY KEY (idArtiste, idGenre),
  idArtiste INTEGER NOT NULL,
  idGenre   INTEGER NOT NULL
);

CREATE TABLE Genre (
  PRIMARY KEY (idGenre),
  idGenre     INTEGER AUTO_INCREMENT NOT NULL,
  Nom         VARCHAR(255) UNIQUE NOT NULL,
  Description TEXT
);

CREATE TABLE GroupeArtiste (
  PRIMARY KEY (idArtiste_Artiste, idArtiste_Groupe),
  idArtiste_Artiste INTEGER NOT NULL,
  idArtiste_Groupe  INTEGER NOT NULL,
  Leader            BOOLEAN
);

CREATE TABLE Morceau (
  PRIMARY KEY (idMorceau),
  idMorceau INTEGER AUTO_INCREMENT NOT NULL,
  Titre     VARCHAR(255) NOT NULL,
  Duree     INT CHECK(Duree > 0),
  NbEcoute  INT DEFAULT 0
);

CREATE TABLE MorceauAlbum (
  PRIMARY KEY (idMorceau, idAlbum),
  idMorceau INTEGER NOT NULL,
  idAlbum   INTEGER NOT NULL,
  Position  INT NOT NULL
);

CREATE TABLE MorceauArtiste (
  PRIMARY KEY (idMorceau, idArtiste),
  idMorceau INTEGER NOT NULL,
  idArtiste INTEGER NOT NULL
);

CREATE TABLE MorceauGenre (
  PRIMARY KEY (idMorceau, idGenre),
  idMorceau INTEGER NOT NULL,
  idGenre   INTEGER NOT NULL
);

CREATE TABLE MorceauPlaylist (
  PRIMARY KEY (idMorceau, idPlaylist),
  idMorceau  INTEGER NOT NULL,
  idPlaylist INTEGER NOT NULL,
  Position   INT NOT NULL
);

CREATE TABLE MorceauUtilisateur (
  PRIMARY KEY (idMorceau, idUtilisateur, DateEcoute),
  idMorceau     INTEGER NOT NULL,
  idUtilisateur INTEGER NOT NULL,
  DateEcoute    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Parole (
  PRIMARY KEY (idParole),
  idParole  INTEGER AUTO_INCREMENT NOT NULL,
  Phrase    TEXT NOT NULL,
  TimeCode  VARCHAR(100) NOT NULL,
  idMorceau INTEGER NOT NULL
);

CREATE TABLE Playlist (
  PRIMARY KEY (idPlaylist),
  idPlaylist   INTEGER AUTO_INCREMENT NOT NULL,
  Nom          VARCHAR(255) NOT NULL,
  DateCreation DATE,
  Description  TEXT
);

CREATE TABLE PlaylistGenre (
  PRIMARY KEY (idPlaylist, idGenre),
  idPlaylist INTEGER NOT NULL,
  idGenre    INTEGER NOT NULL
);

CREATE TABLE PlaylistUtilisateur (
  PRIMARY KEY (idPlaylist, idUtilisateur),
  idPlaylist    INTEGER NOT NULL,
  idUtilisateur INTEGER NOT NULL
);

CREATE TABLE Utilisateur (
  PRIMARY KEY (idUtilisateur),
  idUtilisateur INTEGER AUTO_INCREMENT NOT NULL,
  Email         VARCHAR(255) NOT NULL,
  MotDePasse    VARCHAR(255) NOT NULL,
  Pseudo        VARCHAR(100) UNIQUE NOT NULL,
  Nom           VARCHAR(255),
  Prenom        VARCHAR(255),
  DateNaissance DATE,
  DateCreation  DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE UtilisateurGenre (
  PRIMARY KEY (idUtilisateur, idGenre),
  idUtilisateur INTEGER NOT NULL,
  idGenre       INTEGER NOT NULL
);

ALTER TABLE AlbumGenre ADD FOREIGN KEY (idGenre) REFERENCES Genre (idGenre);
ALTER TABLE AlbumGenre ADD FOREIGN KEY (idAlbum) REFERENCES Album (idAlbum);

ALTER TABLE AlbumUtilisateur ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE AlbumUtilisateur ADD FOREIGN KEY (idAlbum) REFERENCES Album (idAlbum);

ALTER TABLE ArtisteAlbum ADD FOREIGN KEY (idAlbum) REFERENCES Album (idAlbum);
ALTER TABLE ArtisteAlbum ADD FOREIGN KEY (idArtiste) REFERENCES Artiste (idArtiste);

ALTER TABLE ArtisteGenre ADD FOREIGN KEY (idGenre) REFERENCES Genre (idGenre);
ALTER TABLE ArtisteGenre ADD FOREIGN KEY (idArtiste) REFERENCES Artiste (idArtiste);

ALTER TABLE GroupeArtiste ADD FOREIGN KEY (idArtiste_Groupe) REFERENCES Artiste (idArtiste);
ALTER TABLE GroupeArtiste ADD FOREIGN KEY (idArtiste_Artiste) REFERENCES Artiste (idArtiste);

ALTER TABLE MorceauAlbum ADD FOREIGN KEY (idAlbum) REFERENCES Album (idAlbum);
ALTER TABLE MorceauAlbum ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE MorceauArtiste ADD FOREIGN KEY (idArtiste) REFERENCES Artiste (idArtiste);
ALTER TABLE MorceauArtiste ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE MorceauGenre ADD FOREIGN KEY (idGenre) REFERENCES Genre (idGenre);
ALTER TABLE MorceauGenre ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE MorceauPlaylist ADD FOREIGN KEY (idPlaylist) REFERENCES Playlist (idPlaylist);
ALTER TABLE MorceauPlaylist ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE MorceauUtilisateur ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE MorceauUtilisateur ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE Parole ADD FOREIGN KEY (idMorceau) REFERENCES Morceau (idMorceau);

ALTER TABLE PlaylistGenre ADD FOREIGN KEY (idGenre) REFERENCES Genre (idGenre);
ALTER TABLE PlaylistGenre ADD FOREIGN KEY (idPlaylist) REFERENCES Playlist (idPlaylist);

ALTER TABLE PlaylistUtilisateur ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);
ALTER TABLE PlaylistUtilisateur ADD FOREIGN KEY (idPlaylist) REFERENCES Playlist (idPlaylist);

ALTER TABLE UtilisateurGenre ADD FOREIGN KEY (idGenre) REFERENCES Genre (idGenre);
ALTER TABLE UtilisateurGenre ADD FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur);

INSERT INTO Artiste (idArtiste, NomDeScene, Prenom, Nom) VALUES
(1,"Queen", NULL, NULL),
(2, "Imagine Dragons", NULL, NULL),
(3, "Patrick Bruel", "Patrick", "Bruel"),
(4, "Maneskin", NULL, NULL),
(5, "Jain", "Jeanne", "Galice"),
(6, "Loic Nottet", "Loic", "Nottet"),
(7, "Alicia Keys", "Alicia", "Cook"),
(8, "Christophe Willem", "Christophe", "Durier");

INSERT INTO Album (idAlbum, Titre, DateSortie, Description, LienImage) VALUES
(1, "A Night At The Opera", '1975-11-21', "Quatrième album studio du groupe Queen", 'Image/ANATO.png'),
(2, "Loom", '2024-06-28', "Album d'Imagine Dragons", 'Image/Loom.png'),
(3, "Evolve", '2017-06-23', "Album d'Imagine Dragons", NULL),
(4, "Made In Heaven", '1995-11-06', "Album de Queen", "Image/mih.png"),
(5, "Alors regarde", '1989-11-09', NULL, NULL);

INSERT INTO Morceau (idMorceau, Titre, Duree) VALUES
(1, "God Save the Queen", 200),
(2, "Bohemian Rhapsody", 355),
(3, "I'm in Love with My Car", 185),
(4, "Wake Up", 225),
(5, "Eyes Closed", 194),
(6, "Take Me to the Beach", 182),
(7, "Whatever It Takes", 201),
(8, "Believer", 204),
(9, "Thunder", 187),
(10, "Made in Heaven", 325),
(11, "Too Much Love Will Kill You", 260),
(12, "I Was Born to Love You", 289),
(13, "Casser la voix", 318),
(14, "Alors regarde", 298),
(15, "Place des Grands Hommes", 250),
(16, "Coraline", 302),
(17, "Come", 258),
(18, "Mr/Mme", 216),
(19, "Girl on Fire", 224),
(20, "Double je", 209);

INSERT INTO Utilisateur (idUtilisateur, Email, MotDePasse, Pseudo, Nom, Prenom, DateNaissance) VALUES
(1, "samantha.breneliere@gmail.com", "Armaggedon", "Sham","Breneliere", "Samantha", '2004-06-04'),
(2, "lucien.sevaultWolber@gmail.com","Lunamapetitebouledepoilspref", "Lulu", "Sevault Wolber", "Lucien", '2005-04-25'),
(3, "clervie.rannou@gmail.com","jaimelespapillons", "Klairvy", "Rannou", "Clervie", '2005-06-16');

INSERT INTO Playlist (idPlaylist, Nom, DateCreation, Description) VALUES
(1, "sa tue", '2000-01-14', "Musik a fond les balon"),
(2, "playlist pour ma maman", '2012-11-06', "Musiques anniversaire 45ans Catherine"),
(3, "jpp", '2026-04-28', NULL);

INSERT INTO Genre (idGenre, Nom, Description) VALUES
(1, "pop", "Musique populaire aux mélodies accrocheuses, souvent orientée vers le grand public."),
(2, "rock", "Genre énergique basé sur la guitare, avec des influences blues et une forte rythmique."),
(3, "variété française", "Chanson francophone accessible, mêlant textes soignés et styles musicaux variés."),
(4, "phonk", "Sous-genre du hip-hop inspiré des années 90, avec des samples lo-fi et une ambiance sombre.");

INSERT INTO Parole (idParole, Phrase, TimeCode, idMorceau) VALUES
(1, "Is this the real life?", '00:00:10', 2),
(2, "Mama, just killed a man", '00:00:45', 2),
(3, "I wake up to the sound", '00:00:05', 4),
(4, "Casser la voix", '00:00:20', 13);

INSERT INTO ArtisteAlbum (idArtiste, idAlbum) VALUES
(1, 1),
(2, 2),
(2, 3),
(1, 4),
(3, 5);

INSERT INTO MorceauArtiste (idMorceau, idArtiste) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 1),
(11, 1),
(12, 1),
(13, 3),
(14, 3),
(15, 3),
(16, 4),
(17, 5),
(18, 6),
(19, 7),
(20, 8);

INSERT INTO ArtisteGenre (idArtiste, idGenre) VALUES
(1,2),
(2,1),
(2,2),
(3,3),
(4,2),
(5,1),
(6,1),
(7,1),
(8,3);

INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES
(2,1),
(8,1),
(13,2),
(19,3);

INSERT INTO MorceauGenre (idMorceau, idGenre) VALUES
(1,2),
(2,2),
(3,2),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,1),
(10,2),
(11,2),
(12,2),
(13,3),
(14,3),
(15,3),
(16,2),
(17,1),
(18,1),
(19,1),
(20,3);

INSERT INTO MorceauPlaylist (idMorceau, idPlaylist, Position) VALUES
(2,1,1),
(8,1,2),
(13,1,3),
(13,2,1),
(19,2,2),
(2,3,1),
(8,3,2);

INSERT INTO MorceauAlbum (idMorceau, idAlbum, Position) VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,2,1),
(5,2,2),
(6,2,3),
(7,3,1),
(8,3,2),
(9,3,3),
(10,4,1),
(11,4,2),
(12,4,3),
(13,5,1),
(14,5,2),
(15,5,3);

INSERT INTO AlbumGenre (idAlbum, idGenre) VALUES
(1,2),
(2,1),
(2,2),
(3,1),
(3,2),
(4,2),
(5,3);

INSERT INTO UtilisateurGenre (idUtilisateur, idGenre) VALUES
(1,1),
(1,2),
(2,2),
(3,3);

INSERT INTO PlaylistGenre (idPlaylist, idGenre) VALUES
(1,1),
(2,3);

INSERT INTO AlbumUtilisateur (idAlbum, idUtilisateur) VALUES
(1,1),
(2,1),
(3,2),
(5,3);

INSERT INTO PlaylistUtilisateur (idPlaylist, idUtilisateur) VALUES
(1,1),
(2,2),
(2,3);

INSERT INTO GroupeArtiste (idArtiste_Artiste, idArtiste_Groupe, Leader) VALUES
(4,4,TRUE);

/******************************
*********** Requêtes **********
******************************/

-- Discographie d’un artiste : tous ses albums et morceaux
SELECT 
    al.Titre AS Album,
    al.DateSortie,
    ma.Position,
    m.Titre AS Morceau,
    m.Duree,
    m.NbEcoute
FROM Album al
JOIN ArtisteAlbum aa ON al.idAlbum = aa.idAlbum
JOIN Artiste ar ON aa.idArtiste = ar.idArtiste
JOIN MorceauAlbum ma ON ma.idAlbum = al.idAlbum
JOIN Morceau m ON ma.idMorceau = m.idMorceau
WHERE ar.NomDeScene = 'Imagine Dragons'
ORDER BY al.DateSortie, ma.Position;

-- Top 5 morceaux par genre (par nombre d’écoutes)
SELECT 
    g.Nom AS Genre,
    m.Titre,
    m.NbEcoute
FROM Morceau m
JOIN MorceauGenre mg 
    ON m.idMorceau = mg.idMorceau
JOIN Genre g 
    ON mg.idGenre = g.idGenre
WHERE (
    SELECT COUNT(*)
    FROM Morceau m2
    JOIN MorceauGenre mg2 
        ON m2.idMorceau = mg2.idMorceau
    WHERE mg2.idGenre = mg.idGenre
      AND m2.NbEcoute > m.NbEcoute
) < 5
ORDER BY g.Nom, m.NbEcoute DESC;

-- Morceaux présents dans plus d’une playlist
SELECT m.Titre, COUNT(DISTINCT mp.idPlaylist) AS nb_playlists
FROM Morceau m
JOIN MorceauPlaylist mp ON m.idMorceau = mp.idMorceau
GROUP BY m.idMorceau
HAVING COUNT(DISTINCT mp.idPlaylist) > 1
ORDER BY nb_playlists DESC;

-- Durée totale d’écoute d’un utilisateur sur les 30 derniers jours
SELECT u.Pseudo, COUNT(*) AS nb_ecoutes, SUM(m.Duree) / 60 AS minutes_ecoutees
FROM Utilisateur u
JOIN MorceauUtilisateur e ON u.idUtilisateur = e.idUtilisateur
JOIN Morceau m ON e.idMorceau = m.idMorceau
WHERE u.Pseudo = 'Sham'
  AND e.DateEcoute >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY u.idUtilisateur;

/******************************
****** Création des vues ******
******************************/

CREATE OR REPLACE VIEW v_top_morceaux_genre AS
    SELECT g.Nom AS genre,
           m.Titre AS morceau,
           ar.NomDeScene AS artiste,
           al.Titre AS album,
           m.NbEcoute
    FROM Morceau m
    INNER JOIN MorceauAlbum ON m.idMorceau=MorceauAlbum.idMorceau
    INNER JOIN Album al ON MorceauAlbum.idAlbum=al.idAlbum
    INNER JOIN MorceauGenre ON m.idMorceau=MorceauGenre.idMorceau
    INNER JOIN Genre g ON MorceauGenre.idGenre=g.idGenre
    INNER JOIN ArtisteAlbum aa ON al.idAlbum=aa.idAlbum AND aa.Principal=TRUE
    INNER JOIN Artiste ar ON aa.idArtiste=ar.idArtiste;

SELECT * FROM v_top_morceaux_genre
ORDER BY genre, NbEcoute DESC LIMIT 10;

CREATE OR REPLACE VIEW v_bibliotheque_utilisateur AS
SELECT u.idUtilisateur AS utilisateur, 
        p.Nom AS playlist, 
        COUNT(m.idMorceau) AS nbMorceaux, 
        SUM(m.Duree) AS duree
FROM Utilisateur u
INNER JOIN PlaylistUtilisateur up ON u.idUtilisateur = up.idUtilisateur
INNER JOIN Playlist p ON up.idPlaylist = p.idPlaylist
INNER JOIN MorceauPlaylist mp ON p.idPlaylist = mp.idPlaylist
INNER JOIN Morceau m ON mp.idMorceau = m.idMorceau
GROUP BY u.idUtilisateur, p.idPlaylist, p.Nom;

SELECT * FROM v_bibliotheque_utilisateur LIMIT 10;

CREATE OR REPLACE VIEW v_discographie_artiste AS
SELECT ar.NomDeScene AS artiste,
      al.Titre AS titre,
      al.DateSortie AS annee,
      g.Nom AS genre,
      COUNT(m.idMorceau) AS nbMorceaux
FROM Artiste ar
INNER JOIN ArtisteAlbum aral ON ar.idArtiste = aral.idArtiste
INNER JOIN Album al ON aral.idAlbum = al.idAlbum
INNER JOIN AlbumGenre alg ON al.idAlbum = alg.idAlbum
INNER JOIN Genre g ON alg.idGenre = g.idGenre
INNER JOIN MorceauAlbum mal ON al.idAlbum = mal.idAlbum
INNER JOIN Morceau m ON mal.idMorceau = m.idMorceau
GROUP BY ar.NomDeScene, al.idAlbum, al.Titre, al.DateSortie, g.Nom;

SELECT * FROM v_discographie_artiste LIMIT 10;

/******************************
**** Création des triggers ****
******************************/
CREATE TABLE AuditEcoute (
  idAudit INT PRIMARY KEY AUTO_INCREMENT,
  idUtilisateur INT,
  idMorceau INT,
  DateEcoute DATETIME,
  DateEnregistrement DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

DROP TRIGGER IF EXISTS trg_audit_ecoute;

CREATE TRIGGER trg_audit_ecoute
AFTER INSERT ON MorceauUtilisateur
FOR EACH ROW
INSERT INTO AuditEcoute (idUtilisateur, idMorceau, DateEcoute)
VALUES (NEW.idUtilisateur, NEW.idMorceau, NEW.DateEcoute);

-- Insérer 3 écoutes et vérifier qu'il y a bien 3 écoutes dans AuditÉcoute
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES
(2,2),
(3,2),
(5,1);

SELECT * FROM AuditEcoute ;

DROP TRIGGER IF EXISTS trg_increment_ecoutes ;

CREATE TRIGGER trg_increment_ecoutes
AFTER INSERT ON MorceauUtilisateur
FOR EACH ROW
  UPDATE Morceau
  SET NbEcoute = NbEcoute + 1
  WHERE idMorceau = NEW.idMorceau;

-- Insérer 5 écoutes sur un morceau et vérifier que le nombre d'écoutes a bien augmenté de 5.
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES (1,2);
DO SLEEP(1);
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES (1,2);
DO SLEEP(1);
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES (1,2);
DO SLEEP(1);
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES (1,2);
DO SLEEP(1);
INSERT INTO MorceauUtilisateur (idMorceau, idUtilisateur) VALUES (1,2);


SELECT Titre, NbEcoute FROM Morceau
WHERE idMorceau = 1 ;