package jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "MorceauAlbum")
@IdClass(MorceauAlbumId.class) // Clé composite
public class MorceauAlbum {
  @Id
  @ManyToOne
  @JoinColumn(name = "idMorceau", nullable = false)
  private Morceau morceau;

  @Id
  @ManyToOne
  @JoinColumn(name = "idAlbum", nullable = false)
  private Album album;

  @Column(name = "Position", nullable = false) private int position;

  // Constructeurs, getters et setters
  public MorceauAlbum() {}

  // Getters et Setters
  public Morceau getMorceau() { return morceau; }

  public void setMorceau(Morceau morceau) { this.morceau = morceau; }

  public Album getAlbum() { return album; }

  public void setAlbum(Album album) { this.album = album; }

  public int getPosition() { return position; }

  public void setPosition(int position) { this.position = position; }
}
