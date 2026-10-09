package jpa;

import java.io.Serializable;

public class MorceauPlaylistId implements Serializable {
  private int morceau;
  private int playlist;

  // Constructeurs, getters et setters
  public MorceauPlaylistId() {}

  public MorceauPlaylistId(int morceau, int playlist) {
    this.morceau = morceau;
    this.playlist = playlist;
  }

  // Getters et Setters
  public int getMorceau() { return morceau; }

  public void setMorceau(int morceau) { this.morceau = morceau; }

  public int getPlaylist() { return playlist; }

  public void setPlaylist(int playlist) { this.playlist = playlist; }

  // Méthodes hashCode et equals
  @Override
  public int hashCode() {
    return morceau + playlist;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    MorceauPlaylistId that = (MorceauPlaylistId)obj;
    return morceau == that.morceau && playlist == that.playlist;
  }
}
