package jpa;

import java.util.List;
import java.util.Scanner;

public class CLI {
  public static final Scanner scan = new Scanner(System.in);

  public static boolean choice() {
    System.out.println("\n-- Menu --");
    System.out.println("1. Rechercher tous les morceaux d'un artiste donné");
    System.out.println("2. Rechercher les morceaux d'un artiste avec leurs "
                       + "albums (JOIN FETCH)");
    System.out.println(
        "3. Enregistrer une écoute pour un utilisateur et un morceau donnés");
    System.out.println("4. Renommer une playlist d'un utilisateur");
    System.out.println("5. Retirer un morceau d'une playlist");
    System.out.println("6. Morceaux présents dans plus d'une playlist");
    System.out.println(
        "7. Top 5 des morceaux par nombre d'écoutes pour un genre donné");
    System.out.println(
        "8. Tester le dirty checking (modification d'une playlist)");
    System.out.println("9. Tester les triggers (écoute + NbEcoute)");
    System.out.println("10. Quitter");

    int v_choice = readInt("Choisir une option : ");
    switch (v_choice) {
    case 1:
      findByArtiste();
      break;
    case 2:
      findByArtisteWithAlbums();
      break;
    case 3:
      registerEcouteUtilisateurMorceau();
      break;
    case 4:
      renamePlaylistUtilisateur();
      break;
    case 5:
      removeMorceauPlaylist();
      break;
    case 6:
      findMorceauxInMultiplePlaylists();
      break;
    case 7:
      findTop5MorceauxByGenre();
      break;
    case 8:
      testDirtyChecking();
      break;
    case 9:
      testTriggerLimitation();
      break;
    case 10:
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
      List<Morceau> resultats = MorceauJpaRepository.findByArtiste(nomArtiste);

      if (resultats.isEmpty()) {
        System.out.println("Aucun morceau trouvé.");
      } else {
        System.out.println("\n--- Résultats ---");
        for (Morceau m : resultats) {
          System.out.println(m);
        }
      }
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void registerEcouteUtilisateurMorceau() {
    int idUtilisateur = readInt("Donner l'id de l'utilisateur : ");
    int idMorceau = readInt("Donner l'id du morceau : ");
    try {
      MorceauJpaRepository.enregistrerEcoute(idUtilisateur, idMorceau);
      System.out.println("Écoute enregistrée.");
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void renamePlaylistUtilisateur() {
    int idPlaylist = readInt("Donner l'id de la playlist à renommer : ");
    System.out.print("Donner le nouveau nom de la playlist : ");
    String nom = scan.nextLine();
    try {
      MorceauJpaRepository.renamePlaylistUtilisateur(idPlaylist, nom);
      System.out.println("Playlist renommée.");
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void removeMorceauPlaylist() {
    int idPlaylist = readInt("Donner l'id de la playlist : ");
    int idMorceau = readInt("Donner l'id du morceau à retirer : ");
    try {
      MorceauJpaRepository.removeMorceauPlaylist(idMorceau, idPlaylist);
      System.out.println("Morceau retiré de la playlist.");
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void findByArtisteWithAlbums() {
    System.out.print("Donner le nom de l'artiste : ");
    String nomArtiste = scan.nextLine();

    try {
      List<Morceau> resultats =
          MorceauJpaRepository.findMorceauxByArtisteWithAlbums(nomArtiste);

      if (resultats.isEmpty()) {
        System.out.println("Aucun morceau trouvé pour cet artiste.");
      } else {
        System.out.println("\n--- Morceaux de " + nomArtiste +
                           " avec leurs albums ---");
        for (Morceau m : resultats) {
          System.out.println("Morceau : " + m.getTitre());
          for (MorceauAlbum ma : m.getAlbumsMorceau()) {
            System.out.println("  -> Album : " + ma.getAlbum().getTitre() +
                               " (Position : " + ma.getPosition() + ")");
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void findMorceauxInMultiplePlaylists() {
    try {
      List<Morceau> resultats =
          MorceauJpaRepository.findMorceauxInMultiplePlaylists();

      if (resultats.isEmpty()) {
        System.out.println("Aucun morceau présent dans plus d'une playlist.");
      } else {
        System.out.println("\n--- Morceaux dans plus d'une playlist ---");
        for (Morceau m : resultats) {
          System.out.println(m.getTitre() + " (ID: " + m.getIdMorceau() + ")");
        }
      }
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void findTop5MorceauxByGenre() {
    System.out.print("Donner le nom du genre : ");
    String nomGenre = scan.nextLine();

    try {
      List<Morceau> resultats =
          MorceauJpaRepository.findTop5MorceauxByGenre(nomGenre);

      if (resultats.isEmpty()) {
        System.out.println("Aucun morceau trouvé pour ce genre.");
      } else {
        System.out.println("\n--- Top 5 des morceaux (" + nomGenre +
                           ") par écoutes ---");
        for (int i = 0; i < resultats.size(); i++) {
          Morceau m = resultats.get(i);
          System.out.println((i + 1) + ". " + m.getTitre() + " (" +
                             m.getNbEcoute() + " écoutes)");
        }
      }
    } catch (Exception e) {
      System.out.println("Erreur SQL : " + e.getMessage());
    }
  }

  public static void testDirtyChecking() {
    int idPlaylist = readInt("Donner l'id de la playlist à modifier : ");
    System.out.print("Donner le nouveau nom de la playlist : ");
    String nouveauNom = scan.nextLine();

    try {
      System.out.println("\n--- Test du Dirty Checking ---");
      System.out.println("Ancien nom : " +
                         MorceauJpaRepository.getPlaylistNom(idPlaylist));

      MorceauJpaRepository.testDirtyChecking(idPlaylist, nouveauNom);

      System.out.println("Nouveau nom (après commit) : " +
                         MorceauJpaRepository.getPlaylistNom(idPlaylist));
      System.out.println("→ Le dirty checking a fonctionné : la modification "
                         + "a été propagée en base !");
    } catch (Exception e) {
      System.out.println("Erreur : " + e.getMessage());
    }
  }

  public static void testTriggerLimitation() {
    int idUtilisateur = readInt("Donner l'id de l'utilisateur : ");
    int idMorceau = readInt("Donner l'id du morceau : ");

    try {
      System.out.println("\n--- Test des Triggers ---");
      Morceau morceau = MorceauJpaRepository.getMorceauById(idMorceau);
      System.out.println("NbEcoute AVANT écoute : " + morceau.getNbEcoute());

      MorceauJpaRepository.testTriggerLimitation(idUtilisateur, idMorceau);

      morceau = MorceauJpaRepository.getMorceauById(idMorceau);
      System.out.println("NbEcoute APRÈS écoute (après refresh) : " +
                         morceau.getNbEcoute());
      System.out.println("→ Le trigger a incrémenté NbEcoute en base, mais "
                         + "il a fallu un refresh() pour le voir en Java.");
    } catch (Exception e) {
      System.out.println("Erreur : " + e.getMessage());
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
}
