package com.maplr.testhockeygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

  @Id
  @GeneratedValue
  private long id;
  @Column(name = "coach")
  private String coach;
  @Column(name = "year")
  private long year;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
  private List<Player> players = new ArrayList<>();

  public Team() {}

  public Team(String coach, long year) {
    this.coach = coach;
    this.year = year;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCoach() {
    return coach;
  }

  public void setCoach(String coach) {
    this.coach = coach;
  }

  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Team team = (Team) o;
    return id == team.id && year == team.year && Objects.equals(coach, team.coach) && Objects
        .equals(players, team.players);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coach, year, players);
  }

  @Override
  public String toString() {
    return "Team{" + "id=" + id + ", coach='" + coach + '\'' + ", year=" + year + ", players=" + players + '}';
  }
}