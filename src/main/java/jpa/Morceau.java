package jpa;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Morceau")
public class Morceau {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idMorceau")
  private int idMorceau;

  @Column(name = "Titre", nullable = false) private String titre;

  @Column(name = "Duree") private int duree;

  @Column(name = "NbEcoute", columnDefinition = "INT DEFAULT 0")
  private int nbEcoute;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany(mappedBy = "morceaux") private List<Genre> genres;

  @ManyToMany(mappedBy = "morceaux") private List<Artiste> artistes;

  // Relation OneToMany avec MorceauAlbum
  @OneToMany(mappedBy = "morceau") private List<MorceauAlbum> albumsMorceau;

  // Relation OneToMany avec MorceauPlaylist
  @OneToMany(mappedBy = "morceau")
  private List<MorceauPlaylist> playlistsMorceau;

  // Relation OneToMany avec MorceauUtilisateur
  @OneToMany(mappedBy = "morceau")
  private List<MorceauUtilisateur> utilisateursMorceau;

  // Relation OneToMany avec Parole
  @OneToMany(mappedBy = "morceau") private List<Parole> paroles;

  // Constructeurs, getters et setters
  public Morceau() {}

  // Getters et Setters
  public int getIdMorceau() { return idMorceau; }

  public void setIdMorceau(int idMorceau) { this.idMorceau = idMorceau; }

  public String getTitre() { return titre; }

  public void setTitre(String titre) { this.titre = titre; }

  public int getDuree() { return duree; }

  public void setDuree(int duree) { this.duree = duree; }

  public int getNbEcoute() { return nbEcoute; }

  public void setNbEcoute(int nbEcoute) { this.nbEcoute = nbEcoute; }

  public List<Genre> getGenres() { return genres; }

  public void setGenres(List<Genre> genres) { this.genres = genres; }

  public List<Artiste> getArtistes() { return artistes; }

  public void setArtistes(List<Artiste> artistes) { this.artistes = artistes; }

  public List<MorceauAlbum> getAlbumsMorceau() { return albumsMorceau; }

  public void setAlbumsMorceau(List<MorceauAlbum> albumsMorceau) {
    this.albumsMorceau = albumsMorceau;
  }

  public List<MorceauPlaylist> getPlaylistsMorceau() {
    return playlistsMorceau;
  }

  public void setPlaylistsMorceau(List<MorceauPlaylist> playlistsMorceau) {
    this.playlistsMorceau = playlistsMorceau;
  }

  public List<MorceauUtilisateur> getUtilisateursMorceau() {
    return utilisateursMorceau;
  }

  public void
  setUtilisateursMorceau(List<MorceauUtilisateur> utilisateursMorceau) {
    this.utilisateursMorceau = utilisateursMorceau;
  }

  public List<Parole> getParoles() { return paroles; }

  public void setParoles(List<Parole> paroles) { this.paroles = paroles; }
}
