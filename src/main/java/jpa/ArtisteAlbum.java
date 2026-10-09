package jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "ArtisteAlbum")
@IdClass(ArtisteAlbumId.class) // Clé composite
public class ArtisteAlbum {
  @Id
  @ManyToOne
  @JoinColumn(name = "idArtiste", nullable = false)
  private Artiste artiste;

  @Id
  @ManyToOne
  @JoinColumn(name = "idAlbum", nullable = false)
  private Album album;

  @Column(name = "Principal", columnDefinition = "BOOLEAN DEFAULT TRUE")
  private boolean principal;

  // Constructeurs, getters et setters
  public ArtisteAlbum() {}

  // Getters et Setters
  public Artiste getArtiste() { return artiste; }

  public void setArtiste(Artiste artiste) { this.artiste = artiste; }

  public Album getAlbum() { return album; }

  public void setAlbum(Album album) { this.album = album; }

  public boolean isPrincipal() { return principal; }

  public void setPrincipal(boolean principal) { this.principal = principal; }
}
