package jpa;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

public class MorceauJpaRepository {
  public static List<Morceau> findByArtiste(String nomArtiste) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      return em.createQuery("""
          SELECT m
          FROM Morceau m
          JOIN m.albumsMorceau ma
          JOIN ma.album al
          JOIN al.artistes ar
          WHERE ar.nomDeScene = :nom
          ORDER BY al.titre, ma.position
          """, Morceau.class)
          .setParameter("nom", nomArtiste)
          .getResultList();
    } finally {
      em.close();
    }
  }

  public static void enregistrerEcoute(Integer idUtilisateur,
                                       Integer idMorceau) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      Utilisateur u = em.find(Utilisateur.class, idUtilisateur);
      Morceau m = em.find(Morceau.class, idMorceau);

      MorceauUtilisateur e = new MorceauUtilisateur();
      e.setUtilisateur(u);
      e.setMorceau(m);
      e.setDateEcoute(new Date()); // <-- Ajoute la date actuelle

      em.persist(e);
      tx.commit();
    } catch (Exception ex) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw ex;
    } finally {
      em.close();
    }
  }

  public static void renamePlaylistUtilisateur(Integer idPlaylist, String nom) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      Playlist p = em.find(Playlist.class, idPlaylist);
      p.setNom(nom);
      em.merge(p);
      tx.commit();

    } catch (Exception ex) {
      tx.rollback();
      throw ex;
    } finally {
      em.close();
    }
  }

  public static void removeMorceauPlaylist(Integer idMorceau,
                                           Integer idPlaylist) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();

      MorceauPlaylistId id = new MorceauPlaylistId(idMorceau, idPlaylist);

      MorceauPlaylist mp = em.find(MorceauPlaylist.class, id);

      if (mp != null) {
        em.remove(mp);
      }
      tx.commit();
    } catch (Exception ex) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw ex;
    } finally {
      em.close();
    }
  }

  public static List<Morceau>
  findMorceauxByArtisteWithAlbums(String nomArtiste) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      return em.createQuery("""
          SELECT DISTINCT m
          FROM Morceau m
          JOIN FETCH m.albumsMorceau ma
          JOIN FETCH ma.album al
          JOIN al.artistesAlbum aa
          JOIN aa.artiste ar
          WHERE ar.nomDeScene = :nom
          ORDER BY al.titre, ma.position
          """, Morceau.class)
          .setParameter("nom", nomArtiste)
          .getResultList();
    } finally {
      em.close();
    }
  }

  public static List<Morceau> findMorceauxInMultiplePlaylists() {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      return em.createQuery("""
          SELECT m
          FROM Morceau m
          JOIN m.playlistsMorceau mp
          GROUP BY m
          HAVING COUNT(DISTINCT mp.playlist) > 1
          """, Morceau.class).getResultList();
    } finally {
      em.close();
    }
  }

  public static List<Morceau> findTop5MorceauxByGenre(String nomGenre) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      return em.createQuery("""
          SELECT m
          FROM Morceau m
          JOIN m.genres g
          WHERE g.nom = :nom
          ORDER BY m.nbEcoute DESC
          """, Morceau.class)
          .setParameter("nom", nomGenre)
          .setMaxResults(5)
          .getResultList();
    } finally {
      em.close();
    }
  }

  public static void testDirtyChecking(Integer idPlaylist, String nouveauNom) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      Playlist playlist = em.find(Playlist.class, idPlaylist);
      playlist.setNom(nouveauNom);
      tx.commit();
    } catch (Exception ex) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw ex;
    } finally {
      em.close();
    }
  }

  public static void testTriggerLimitation(Integer idUtilisateur,
                                           Integer idMorceau) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      Utilisateur u = em.find(Utilisateur.class, idUtilisateur);
      Morceau m = em.find(Morceau.class, idMorceau);

      MorceauUtilisateur ecoute = new MorceauUtilisateur();
      ecoute.setUtilisateur(u);
      ecoute.setMorceau(m);
      ecoute.setDateEcoute(new Date());

      em.persist(ecoute);
      em.flush();
      tx.commit();
    } catch (Exception ex) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw ex;
    } finally {
      em.close();
    }
  }

  public static String getPlaylistNom(Integer idPlaylist) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      Playlist p = em.find(Playlist.class, idPlaylist);
      return p != null ? p.getNom() : "Playlist introuvable";
    } finally {
      em.close();
    }
  }

  public static Morceau getMorceauById(Integer idMorceau) {
    EntityManager em = ESIRifyPersistence.getEMF().createEntityManager();
    try {
      return em.find(Morceau.class, idMorceau);
    } finally {
      em.close();
    }
  }
}
