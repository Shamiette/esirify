package jpa;

import java.io.Serializable;

public class GroupeArtisteId implements Serializable {
  private int artisteArtiste;
  private int artisteGroupe;

  // Constructeurs, getters et setters
  public GroupeArtisteId() {}

  public GroupeArtisteId(int artisteArtiste, int artisteGroupe) {
    this.artisteArtiste = artisteArtiste;
    this.artisteGroupe = artisteGroupe;
  }

  // Getters et Setters
  public int getArtisteArtiste() { return artisteArtiste; }

  public void setArtisteArtiste(int artisteArtiste) {
    this.artisteArtiste = artisteArtiste;
  }

  public int getArtisteGroupe() { return artisteGroupe; }

  public void setArtisteGroupe(int artisteGroupe) {
    this.artisteGroupe = artisteGroupe;
  }

  // Méthodes hashCode et equals
  @Override
  public int hashCode() {
    return artisteArtiste + artisteGroupe;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    GroupeArtisteId that = (GroupeArtisteId)obj;
    return artisteArtiste == that.artisteArtiste &&
        artisteGroupe == that.artisteGroupe;
  }
}
