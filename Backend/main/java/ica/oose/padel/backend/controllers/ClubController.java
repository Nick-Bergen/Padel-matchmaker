package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.ClubService;
import ica.oose.padel.backend.services.dto.ClubDTO;
import ica.oose.padel.backend.services.dto.ClubsDTO;
import ica.oose.padel.backend.services.dto.LoginClubDTO;
import ica.oose.padel.backend.services.dto.RegisterClubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(path = "login")
    @ResponseBody
    public ClubDTO registerClub(@RequestBody LoginClubDTO loginClubDTO) {
        return clubService.getClubByName(loginClubDTO.getName());
    }

    @PostMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ClubDTO registerClub(@RequestBody RegisterClubDTO registerClubDTO) {
        return clubService.insertNewClub(registerClubDTO.getName(), registerClubDTO.getLocation(), registerClubDTO.getOpeningHours());
    }

    @GetMapping(path = "overview", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ClubsDTO getAllClubs() {
        return clubService.getAllClubs();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ClubDTO getClubByName(@RequestParam String clubName) {
        return clubService.getClubByName(clubName);
    }

}