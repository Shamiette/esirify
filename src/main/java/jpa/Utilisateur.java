package jpa;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idUtilisateur")
  private int idUtilisateur;

  @Column(name = "Email", nullable = false) private String email;

  @Column(name = "MotDePasse", nullable = false) private String motDePasse;

  @Column(name = "Pseudo", unique = true, nullable = false)
  private String pseudo;

  @Column(name = "Nom") private String nom;

  @Column(name = "Prenom") private String prenom;

  @Column(name = "DateNaissance")
  @Temporal(TemporalType.DATE)
  private Date dateNaissance;

  @Column(name = "DateCreation",
          columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreation;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany(mappedBy = "utilisateurs") private List<Album> albums;

  @ManyToMany(mappedBy = "utilisateurs") private List<Genre> genres;

  @ManyToMany
  @JoinTable(name = "PlaylistUtilisateur",
             joinColumns = @JoinColumn(name = "idUtilisateur"),
             inverseJoinColumns = @JoinColumn(name = "idPlaylist"))
  private List<Playlist> playlists;

  // Relation OneToMany avec MorceauUtilisateur
  @OneToMany(mappedBy = "utilisateur")
  private List<MorceauUtilisateur> morceauxUtilisateur;

  // Constructeurs, getters et setters
  public Utilisateur() {}

  // Getters et Setters
  public int getIdUtilisateur() { return idUtilisateur; }

  public void setIdUtilisateur(int idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getMotDePasse() { return motDePasse; }

  public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

  public String getPseudo() { return pseudo; }

  public void setPseudo(String pseudo) { this.pseudo = pseudo; }

  public String getNom() { return nom; }

  public void setNom(String nom) { this.nom = nom; }

  public String getPrenom() { return prenom; }

  public void setPrenom(String prenom) { this.prenom = prenom; }

  public Date getDateNaissance() { return dateNaissance; }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public Date getDateCreation() { return dateCreation; }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  public List<Album> getAlbums() { return albums; }

  public void setAlbums(List<Album> albums) { this.albums = albums; }

  public List<Genre> getGenres() { return genres; }

  public void setGenres(List<Genre> genres) { this.genres = genres; }

  public List<Playlist> getPlaylists() { return playlists; }

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }

  public List<MorceauUtilisateur> getMorceauxUtilisateur() {
    return morceauxUtilisateur;
  }

  public void
  setMorceauxUtilisateur(List<MorceauUtilisateur> morceauxUtilisateur) {
    this.morceauxUtilisateur = morceauxUtilisateur;
  }
}
