package jpa;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Playlist")
public class Playlist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idPlaylist")
  private int idPlaylist;

  @Column(name = "Nom", nullable = false) private String nom;

  @Column(name = "DateCreation")
  @Temporal(TemporalType.DATE)
  private Date dateCreation;

  @Column(name = "Description") private String description;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany(mappedBy = "playlists") private List<Genre> genres;

  @ManyToMany(mappedBy = "playlists") private List<Utilisateur> utilisateurs;

  // Relation OneToMany avec MorceauPlaylist
  @OneToMany(mappedBy = "playlist")
  private List<MorceauPlaylist> morceauxPlaylist;

  // Constructeurs, getters et setters
  public Playlist() {}

  // Getters et Setters
  public int getIdPlaylist() { return idPlaylist; }

  public void setIdPlaylist(int idPlaylist) { this.idPlaylist = idPlaylist; }

  public String getNom() { return nom; }

  public void setNom(String nom) { this.nom = nom; }

  public Date getDateCreation() { return dateCreation; }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Genre> getGenres() { return genres; }

  public void setGenres(List<Genre> genres) { this.genres = genres; }

  public List<Utilisateur> getUtilisateurs() { return utilisateurs; }

  public void setUtilisateurs(List<Utilisateur> utilisateurs) {
    this.utilisateurs = utilisateurs;
  }

  public List<MorceauPlaylist> getMorceauxPlaylist() {
    return morceauxPlaylist;
  }

  public void setMorceauxPlaylist(List<MorceauPlaylist> morceauxPlaylist) {
    this.morceauxPlaylist = morceauxPlaylist;
  }
}
