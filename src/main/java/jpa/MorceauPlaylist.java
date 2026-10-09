package jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "MorceauPlaylist")
@IdClass(MorceauPlaylistId.class) // Clé composite
public class MorceauPlaylist {
  @Id
  @ManyToOne
  @JoinColumn(name = "idMorceau", nullable = false)
  private Morceau morceau;

  @Id
  @ManyToOne
  @JoinColumn(name = "idPlaylist", nullable = false)
  private Playlist playlist;

  @Column(name = "Position", nullable = false) private int position;

  // Constructeurs, getters et setters
  public MorceauPlaylist() {}

  // Getters et Setters
  public Morceau getMorceau() { return morceau; }

  public void setMorceau(Morceau morceau) { this.morceau = morceau; }

  public Playlist getPlaylist() { return playlist; }

  public void setPlaylist(Playlist playlist) { this.playlist = playlist; }

  public int getPosition() { return position; }

  public void setPosition(int position) { this.position = position; }
}
