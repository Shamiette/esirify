package jpa;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Album")
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAlbum")
  private int idAlbum;

  @Column(name = "Titre", nullable = false) private String titre;

  @Column(name = "DateSortie", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date dateSortie;

  @Column(name = "Description") private String description;

  @Column(name = "LienImage") private String lienImage;

  // Relations ManyToMany (via tables de jointure)
  @ManyToMany(mappedBy = "albums") private List<Genre> genres;

  @ManyToMany(mappedBy = "albums") private List<Artiste> artistes;

  @ManyToMany
  @JoinTable(name = "AlbumUtilisateur",
             joinColumns = @JoinColumn(name = "idAlbum"),
             inverseJoinColumns = @JoinColumn(name = "idUtilisateur"))
  private List<Utilisateur> utilisateurs;

  // Relation OneToMany avec MorceauAlbum (Morceau est du côté "Many")
  @OneToMany(mappedBy = "album") private List<MorceauAlbum> morceauxAlbum;

  // Constructeurs, getters et setters
  public Album() {}

  // Getters et Setters
  public int getIdAlbum() { return idAlbum; }

  public void setIdAlbum(int idAlbum) { this.idAlbum = idAlbum; }

  public String getTitre() { return titre; }

  public void setTitre(String titre) { this.titre = titre; }

  public Date getDateSortie() { return dateSortie; }

  public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLienImage() { return lienImage; }

  public void setLienImage(String lienImage) { this.lienImage = lienImage; }

  public List<Genre> getGenres() { return genres; }

  public void setGenres(List<Genre> genres) { this.genres = genres; }

  public List<Artiste> getArtistes() { return artistes; }

  public void setArtistes(List<Artiste> artistes) { this.artistes = artistes; }

  public List<Utilisateur> getUtilisateurs() { return utilisateurs; }

  public void setUtilisateurs(List<Utilisateur> utilisateurs) {
    this.utilisateurs = utilisateurs;
  }

  public List<MorceauAlbum> getMorceauxAlbum() { return morceauxAlbum; }

  public void setMorceauxAlbum(List<MorceauAlbum> morceauxAlbum) {
    this.morceauxAlbum = morceauxAlbum;
  }
}
