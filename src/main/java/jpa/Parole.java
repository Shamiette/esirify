package jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "Parole")
public class Parole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idParole")
  private int idParole;

  @Column(name = "Phrase", nullable = false) private String phrase;

  @Column(name = "TimeCode", nullable = false) private String timeCode;

  @ManyToOne
  @JoinColumn(name = "idMorceau", nullable = false)
  private Morceau morceau;

  // Constructeurs, getters et setters
  public Parole() {}

  // Getters et Setters
  public int getIdParole() { return idParole; }

  public void setIdParole(int idParole) { this.idParole = idParole; }

  public String getPhrase() { return phrase; }

  public void setPhrase(String phrase) { this.phrase = phrase; }

  public String getTimeCode() { return timeCode; }

  public void setTimeCode(String timeCode) { this.timeCode = timeCode; }

  public Morceau getMorceau() { return morceau; }

  public void setMorceau(Morceau morceau) { this.morceau = morceau; }
}
