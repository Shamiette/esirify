package jpa;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genre")
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idGenre")
  private int idGenre;

  @Column(name = "Nom", nullable = false, unique = true) private String nom;

  @Column(name = "Description") private String description;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany
  @JoinTable(name = "AlbumGenre", joinColumns = @JoinColumn(name = "idGenre"),
             inverseJoinColumns = @JoinColumn(name = "idAlbum"))
  private List<Album> albums;

  @ManyToMany
  @JoinTable(name = "ArtisteGenre", joinColumns = @JoinColumn(name = "idGenre"),
             inverseJoinColumns = @JoinColumn(name = "idArtiste"))
  private List<Artiste> artistes;

  @ManyToMany
  @JoinTable(name = "MorceauGenre", joinColumns = @JoinColumn(name = "idGenre"),
             inverseJoinColumns = @JoinColumn(name = "idMorceau"))
  private List<Morceau> morceaux;

  @ManyToMany
  @JoinTable(name = "PlaylistGenre",
             joinColumns = @JoinColumn(name = "idGenre"),
             inverseJoinColumns = @JoinColumn(name = "idPlaylist"))
  private List<Playlist> playlists;

  @ManyToMany
  @JoinTable(name = "UtilisateurGenre",
             joinColumns = @JoinColumn(name = "idGenre"),
             inverseJoinColumns = @JoinColumn(name = "idUtilisateur"))
  private List<Utilisateur> utilisateurs;

  // Constructeurs, getters et setters
  public Genre() {}

  // Getters et Setters
  public int getIdGenre() { return idGenre; }

  public void setIdGenre(int idGenre) { this.idGenre = idGenre; }

  public String getNom() { return nom; }

  public void setNom(String nom) { this.nom = nom; }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Album> getAlbums() { return albums; }

  public void setAlbums(List<Album> albums) { this.albums = albums; }

  public List<Artiste> getArtistes() { return artistes; }

  public void setArtistes(List<Artiste> artistes) { this.artistes = artistes; }

  public List<Morceau> getMorceaux() { return morceaux; }

  public void setMorceaux(List<Morceau> morceaux) { this.morceaux = morceaux; }

  public List<Playlist> getPlaylists() { return playlists; }

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }

  public List<Utilisateur> getUtilisateurs() { return utilisateurs; }

  public void setUtilisateurs(List<Utilisateur> utilisateurs) {
    this.utilisateurs = utilisateurs;
  }
}
