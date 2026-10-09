package jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "GroupeArtiste")
@IdClass(GroupeArtisteId.class) // Clé composite
public class GroupeArtiste {
  @Id
  @ManyToOne
  @JoinColumn(name = "idArtiste_Artiste", nullable = false)
  private Artiste artisteArtiste;

  @Id
  @ManyToOne
  @JoinColumn(name = "idArtiste_Groupe", nullable = false)
  private Artiste artisteGroupe;

  @Column(name = "Leader") private Boolean leader;

  // Constructeurs, getters et setters
  public GroupeArtiste() {}

  // Getters et Setters
  public Artiste getArtisteArtiste() { return artisteArtiste; }

  public void setArtisteArtiste(Artiste artisteArtiste) {
    this.artisteArtiste = artisteArtiste;
  }

  public Artiste getArtisteGroupe() { return artisteGroupe; }

  public void setArtisteGroupe(Artiste artisteGroupe) {
    this.artisteGroupe = artisteGroupe;
  }

  public Boolean getLeader() { return leader; }

  public void setLeader(Boolean leader) { this.leader = leader; }
}
