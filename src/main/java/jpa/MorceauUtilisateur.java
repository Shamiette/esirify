package jpa;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MorceauUtilisateur")
@IdClass(MorceauUtilisateurId.class) // Clé composite
public class MorceauUtilisateur {
  @Id
  @ManyToOne
  @JoinColumn(name = "idMorceau", nullable = false)
  private Morceau morceau;

  @Id
  @ManyToOne
  @JoinColumn(name = "idUtilisateur", nullable = false)
  private Utilisateur utilisateur;

  @Id
  @Column(name = "DateEcoute", nullable = false,
          columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateEcoute;

  // Constructeurs, getters et setters
  public MorceauUtilisateur() {}

  // Getters et Setters
  public Morceau getMorceau() { return morceau; }

  public void setMorceau(Morceau morceau) { this.morceau = morceau; }

  public Utilisateur getUtilisateur() { return utilisateur; }

  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }

  public Date getDateEcoute() { return dateEcoute; }

  public void setDateEcoute(Date dateEcoute) { this.dateEcoute = dateEcoute; }
}
