package com.maplr.testhockeygame;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "player")
public class Player {

  @Id
  @GeneratedValue
  private long id;
  @Column(name = "number")
  private long number;
  @Column(name = "name")
  private String name;
  @Column(name = "lastname")
  private String lastname;
  @Column(name = "position")
  private String position;
  @Column(name = "isCaptain")
  @JsonProperty
  private boolean isCaptain;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Team team;

  public Player() {}

  public Player(long number, String name, String lastname, String position, boolean isCaptain) {
    this.number = number;
    this.name = name;
    this.lastname = lastname;
    this.position = position;
    this.isCaptain = isCaptain;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public boolean isCaptain() {
    return isCaptain;
  }

  public void setCaptain(boolean captain) {
    isCaptain = captain;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Player player = (Player) o;
    return number == player.number && isCaptain == player.isCaptain && Objects.equals(name, player.name) && Objects
        .equals(lastname, player.lastname) && Objects.equals(position, player.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, name, lastname, position, isCaptain);
  }

  @Override
  public String toString() {
    return "Player{" + "number=" + number + ", name='" + name + '\'' + ", lastname='" + lastname + '\'' + ", position='"
        + position + '\'' + ", isCaptain=" + isCaptain + '}';
  }
}