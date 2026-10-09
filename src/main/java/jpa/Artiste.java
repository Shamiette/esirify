package jpa;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Artiste")
public class Artiste {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idArtiste")
  private int idArtiste;

  @Column(name = "NomDeScene", nullable = false) private String nomDeScene;

  @Column(name = "Prenom") private String prenom;

  @Column(name = "Nom") private String nom;

  @Column(name = "Nationalite") private String nationalite;

  @Column(name = "Description") private String description;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany
  @JoinTable(name = "ArtisteAlbum",
             joinColumns = @JoinColumn(name = "idArtiste"),
             inverseJoinColumns = @JoinColumn(name = "idAlbum"))
  private List<Album> albums;

  @ManyToMany
  @JoinTable(name = "ArtisteGenre",
             joinColumns = @JoinColumn(name = "idArtiste"),
             inverseJoinColumns = @JoinColumn(name = "idGenre"))
  private List<Genre> genres;

  @ManyToMany
  @JoinTable(name = "MorceauArtiste",
             joinColumns = @JoinColumn(name = "idArtiste"),
             inverseJoinColumns = @JoinColumn(name = "idMorceau"))
  private List<Morceau> morceaux;

  // Relation ManyToMany pour les groupes (auto-référence)
  @ManyToMany
  @JoinTable(name = "GroupeArtiste",
             joinColumns = @JoinColumn(name = "idArtiste_Artiste"),
             inverseJoinColumns = @JoinColumn(name = "idArtiste_Groupe"))
  private List<Artiste> groupes;

  @ManyToMany(mappedBy = "groupes") private List<Artiste> membres;

  // Constructeurs, getters et setters
  public Artiste() {}

  // Getters et Setters
  public int getIdArtiste() { return idArtiste; }

  public void setIdArtiste(int idArtiste) { this.idArtiste = idArtiste; }

  public String getNomDeScene() { return nomDeScene; }

  public void setNomDeScene(String nomDeScene) { this.nomDeScene = nomDeScene; }

  public String getPrenom() { return prenom; }

  public void setPrenom(String prenom) { this.prenom = prenom; }

  public String getNom() { return nom; }

  public void setNom(String nom) { this.nom = nom; }

  public String getNationalite() { return nationalite; }

  public void setNationalite(String nationalite) {
    this.nationalite = nationalite;
  }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Album> getAlbums() { return albums; }

  public void setAlbums(List<Album> albums) { this.albums = albums; }

  public List<Genre> getGenres() { return genres; }

  public void setGenres(List<Genre> genres) { this.genres = genres; }

  public List<Morceau> getMorceaux() { return morceaux; }

  public void setMorceaux(List<Morceau> morceaux) { this.morceaux = morceaux; }

  public List<Artiste> getGroupes() { return groupes; }

  public void setGroupes(List<Artiste> groupes) { this.groupes = groupes; }

  public List<Artiste> getMembres() { return membres; }

  public void setMembres(List<Artiste> membres) { this.membres = membres; }
}
