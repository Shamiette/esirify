package jdbc;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class CLI {
  public static final Scanner scan = new Scanner(System.in);

  public static boolean choice() {
    System.out.println("-- Menu --");
    System.out.println("1. Rechercher tous les morceaux d'un artiste donné");
    System.out.println(
        "2. Enregistrer une écoute pour un utilisateur et un morceau donnés");
    System.out.println("3. Renommer une playlist d'un utilisateur");
    System.out.println("4. Retirer un morceau d'une playlist");
    System.out.println("5. Quitter");
    int v_choice = readInt("Choisir une option : ");
    switch (v_choice) {
      case 1:
        findByArtiste();
        break;
      case 2:
        registerEcouteUtilisateurMorceau();
        break;
      case 3:
        renamePlaylistUtilisateur();
        break;
      case 4:
        removeMorceauPlaylist();
        break;
      case 5:
        System.out.println("Au revoir !");
        return false;
      default:
        System.out.println("Option invalide. Veuillez réessayer.");
        break;
    }
    return true;
  }

  public static void findByArtiste() {
      System.out.print("Donner le nom de l'artiste : ");
      String nomArtiste = scan.nextLine();

      try {
          List<String> resultats = MorceauRepository.findByArtiste(nomArtiste);

          if (resultats.isEmpty()) {
              System.out.println("Aucun morceau trouvé.");
          } else {
              System.out.println("\n--- Résultats ---");
              for (String m : resultats) {
                  System.out.println(m);
              }
          }

      } catch (SQLException e) {
          System.out.println("Erreur SQL : " + e.getMessage());
      }
  }

  public static void registerEcouteUtilisateurMorceau() {
    int idUtilisateur = readInt("Donner l'id de l'utilisateur : ");
    int idMorceau = readInt("Donner l'id du morceau : ");
    try {
      MorceauRepository.registerEcouteUtilisateurMorceau(idUtilisateur, idMorceau);
      System.out.println("Écoute enregistrée");
    } catch (SQLException e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void renamePlaylistUtilisateur() {
    int idPlaylist = readInt("Donner l'id de la playlist à renommer : ");
    System.out.print("Donner le nouveau nom de la playlist : ");
    String Nom = scan.nextLine();
    try {
      MorceauRepository.renamePlaylistUtilisateur(idPlaylist, Nom);
      System.out.println("Playlist renommée");
    } catch (SQLException e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void removeMorceauPlaylist() {
    int idPlaylist = readInt("Donner l'id de la playlist de laquelle le morceau sera retiré : ");
    int idMorceau = readInt("Donner l'id du morceau à retirer : ");
    try {
      MorceauRepository.removeMorceauPlaylist(idMorceau, idPlaylist);
      System.out.println("Morceau retiré");
    } catch (SQLException e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(scan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Erreur : Veuillez entrer un nombre valide.");
      }
    }
  }
};
