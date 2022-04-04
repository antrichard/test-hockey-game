package com.maplr.testhockeygame;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class TeamController {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @GetMapping("/team")
  List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  @PostMapping("/team")
  Team createTeam(@RequestBody Team team) {
    return teamRepository.save(team);
  }

  @GetMapping("/team/{id}")
  Team getTeamById(@PathVariable long id) {
    return teamRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Team with id = " + id));
  }

  @GetMapping("/team/year/{year}")
  Team getTeamByYear(@PathVariable long year) {
    return teamRepository.findByYear(year)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Team with year = " + year));
  }

  @PostMapping("/team/{year}")
  ResponseEntity<Player> addPlayerToTeam(@RequestBody Player playerRequest, @PathVariable long year) {
    Player player = teamRepository.findByYear(year).map(team -> {
      playerRequest.setTeam(team);
      return playerRepository.save(playerRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Team with year = " + year));
    return new ResponseEntity<>(player, HttpStatus.CREATED);
  }

  @PutMapping("/team/{id}")
  Team updateTeam(@RequestBody Team newTeam, @PathVariable long id) {
    return teamRepository.findById(id).map(team -> {
      team.setCoach(newTeam.getCoach());
      team.setYear(newTeam.getYear());
      return teamRepository.save(team);
    }).orElseGet(() -> teamRepository.save(newTeam));
  }

  @PutMapping("/team/player/{year}/{numberPlayer}")
  ResponseEntity<Player> updateTeamCaptain(@PathVariable long year, @PathVariable long numberPlayer) {
    teamRepository.findByYear(year).map(team -> {
      Player currentCaptain = team.getPlayers().stream().filter(player -> player.isCaptain()).findAny().orElse(null);
      if (currentCaptain != null) {
        currentCaptain.setCaptain(Boolean.FALSE);
        playerRepository.save(currentCaptain);
      }

      Player newCaptain =
          team.getPlayers().stream().filter(player -> Objects.equals(numberPlayer, player.getNumber())).findAny()
              .orElse(null);
      newCaptain.setCaptain(Boolean.TRUE);
      return playerRepository.save(newCaptain);
    }).orElseThrow(
        () -> new ResourceNotFoundException("Not found Player with year =" + year + " and number = " + numberPlayer));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/team/{id}")
  void deleteTeam(@PathVariable long id) {
    teamRepository.deleteById(id);
  }

}





















