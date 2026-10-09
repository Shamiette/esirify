package jpa;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AuditEcoute")
public class AuditEcoute {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAudit")
  private int idAudit;

  @Column(name = "idUtilisateur") private int idUtilisateur;

  @Column(name = "idMorceau") private int idMorceau;

  @Column(name = "DateEcoute")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateEcoute;

  @Column(name = "DateEnregistrement",
          columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateEnregistrement;

  // Constructeurs, getters et setters
  public AuditEcoute() {}

  // Getters et Setters
  public int getIdAudit() { return idAudit; }

  public void setIdAudit(int idAudit) { this.idAudit = idAudit; }

  public int getIdUtilisateur() { return idUtilisateur; }

  public void setIdUtilisateur(int idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public int getIdMorceau() { return idMorceau; }

  public void setIdMorceau(int idMorceau) { this.idMorceau = idMorceau; }

  public Date getDateEcoute() { return dateEcoute; }

  public void setDateEcoute(Date dateEcoute) { this.dateEcoute = dateEcoute; }

  public Date getDateEnregistrement() { return dateEnregistrement; }

  public void setDateEnregistrement(Date dateEnregistrement) {
    this.dateEnregistrement = dateEnregistrement;
  }
}
