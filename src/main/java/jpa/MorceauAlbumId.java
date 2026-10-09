package jpa;

import java.io.Serializable;

public class MorceauAlbumId implements Serializable {
  private int morceau;
  private int album;

  // Constructeurs, getters et setters
  public MorceauAlbumId() {}

  public MorceauAlbumId(int morceau, int album) {
    this.morceau = morceau;
    this.album = album;
  }

  // Getters et Setters
  public int getMorceau() { return morceau; }

  public void setMorceau(int morceau) { this.morceau = morceau; }

  public int getAlbum() { return album; }

  public void setAlbum(int album) { this.album = album; }

  // Méthodes hashCode et equals (obligatoires pour les clés composites)
  @Override
  public int hashCode() {
    return morceau + album;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    MorceauAlbumId that = (MorceauAlbumId)obj;
    return morceau == that.morceau && album == that.album;
  }
}
