package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MorceauRepository {
  // // Lecture : rechercher tous les morceaux d’un artiste donné 
  // // (nom passé en paramètre) et les afficher avec leur album et leur durée.
  public static List<String> findByArtiste(String nomArtiste)
    throws SQLException {
    String sql = """
        SELECT
            m.Titre AS titre,
            al.Titre AS album,
            m.Duree AS duree_sec,
            m.NbEcoute AS nb_ecoutes
        FROM Morceau m
        JOIN MorceauAlbum ma
            ON m.idMorceau = ma.idMorceau
        JOIN Album al
            ON ma.idAlbum = al.idAlbum
        JOIN ArtisteAlbum aa
            ON al.idAlbum = aa.idAlbum
        JOIN Artiste ar
            ON aa.idArtiste = ar.idArtiste
        WHERE ar.NomDeScene = ?
        ORDER BY al.Titre, ma.Position;
        """;
    List<String> resultats = new ArrayList<>();
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, nomArtiste);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          String ligne = rs.getString("titre") + " - " + rs.getString("album") +
                         " (" + rs.getInt("duree_sec") + "s, " +
                         rs.getInt("nb_ecoutes") + " ecoutes)";
          resultats.add(ligne);
        }
      }
    }
    return resultats;
  }

  // Insertion : enregistrer une écoute pour un utilisateur et un morceau donnés
  public static void registerEcouteUtilisateurMorceau(int idUtilisateur, int idMorceau)
    throws SQLException {
    String sql = """
        INSERT INTO MorceauUtilisateur
        (idMorceau, idUtilisateur) VALUES
        (?, ?)
        """;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, idMorceau);
      ps.setInt(2, idUtilisateur);
      int rs = ps.executeUpdate();
      System.out.println(rs);
    }
  }

  // Mise à jour : renommer une playlist d’un utilisateur.
  public static void renamePlaylistUtilisateur(int idPlaylist, String Nom)
    throws SQLException {
    String sql = """
        UPDATE Playlist
        SET Nom = ?
        WHERE idPlaylist = ?
        """;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, Nom);
      ps.setInt(2, idPlaylist);
      int rs = ps.executeUpdate();
      System.out.println(rs);
    }
  }

  // Suppression : retirer un morceau d’une playlist.
  public static void removeMorceauPlaylist(int idMorceau, int idPlaylist)
    throws SQLException {
    String sql = """
        DELETE FROM MorceauPlaylist
        WHERE idMorceau = ?
        AND idPlaylist = ?
        """;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, idMorceau);
      ps.setInt(2, idPlaylist);
      ps.executeUpdate();
    }
  }
}