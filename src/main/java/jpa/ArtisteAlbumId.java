package jpa;

import java.io.Serializable;

public class ArtisteAlbumId implements Serializable {
  private int artiste;
  private int album;

  // Constructeurs, getters et setters
  public ArtisteAlbumId() {}

  public ArtisteAlbumId(int artiste, int album) {
    this.artiste = artiste;
    this.album = album;
  }

  // Getters et Setters
  public int getArtiste() { return artiste; }

  public void setArtiste(int artiste) { this.artiste = artiste; }

  public int getAlbum() { return album; }

  public void setAlbum(int album) { this.album = album; }

  // Méthodes hashCode et equals
  @Override
  public int hashCode() {
    return artiste + album;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    ArtisteAlbumId that = (ArtisteAlbumId)obj;
    return artiste == that.artiste && album == that.album;
  }
}
