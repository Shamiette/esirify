package jpa;

import java.io.Serializable;
import java.util.Date;

public class MorceauUtilisateurId implements Serializable {
  private int morceau;
  private int utilisateur;
  private Date dateEcoute;

  // Constructeurs, getters et setters
  public MorceauUtilisateurId() {}

  public MorceauUtilisateurId(int morceau, int utilisateur, Date dateEcoute) {
    this.morceau = morceau;
    this.utilisateur = utilisateur;
    this.dateEcoute = dateEcoute;
  }

  // Getters et Setters
  public int getMorceau() { return morceau; }

  public void setMorceau(int morceau) { this.morceau = morceau; }

  public int getUtilisateur() { return utilisateur; }

  public void setUtilisateur(int utilisateur) {
    this.utilisateur = utilisateur;
  }

  public Date getDateEcoute() { return dateEcoute; }

  public void setDateEcoute(Date dateEcoute) { this.dateEcoute = dateEcoute; }

  // Méthodes hashCode et equals
  @Override
  public int hashCode() {
    return morceau + utilisateur +
        (dateEcoute != null ? dateEcoute.hashCode() : 0);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    MorceauUtilisateurId that = (MorceauUtilisateurId)obj;
    return morceau == that.morceau && utilisateur == that.utilisateur &&
        (dateEcoute != null ? dateEcoute.equals(that.dateEcoute)
                            : that.dateEcoute == null);
  }
}
