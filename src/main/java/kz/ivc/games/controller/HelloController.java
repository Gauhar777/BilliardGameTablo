package kz.ivc.games.controller;

import kz.ivc.games.dto.Form;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import kz.ivc.games.entity.Partner;
import kz.ivc.games.repo.CompetitationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    private Logger LOG = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private final CompetitationRepo competitationRepo;

    public HelloController(CompetitationRepo competitationRepo) {
        this.competitationRepo = competitationRepo;
    }

    //***********************************Show Competition*******************************************************

    @GetMapping("/main")
    public String hello(@ModelAttribute("model") ModelMap model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<Competition> competitionList = this.competitationRepo.findAll();
        model.addAttribute("competitionList", competitionList);
        return "main";
    }


//***********************************DELETE Competition*******************************************************

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCompetition(@PathVariable Long id) {
        LOG.info("delete " + id);
        Competition competition = this.competitationRepo.getOne(id);
        this.competitationRepo.delete(competition);
        return "redirect:/main";
    }


//***********************************Edit  Competition*******************************************************


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCompetitionFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
        Competition competition = this.competitationRepo.getOne(id);
        model.put("competition", competition);
        return "editCompetition";
    }


    @RequestMapping(value = {"/editCompetitionSave"}, method = RequestMethod.POST)
    public String competitionFormSubmit(Model model, Form form) {
        LOG.info("competitionFormSubmit****");
        String id = form.getId();
        Long Rid = Long.parseLong(id);
        Competition competition = this.competitationRepo.getOne(Rid);
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main";
        } else {
            /*
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);
            */
            return "error";
        }

    }

    //***********************************Add new Competition*******************************************************
    public String addCompetitionFormGet(Model model) {
        return "addCompetition";
    }


    @RequestMapping(value = {"/addCompetition"}, method = RequestMethod.POST)
    public String addCompetition(Model model, Form form) {
        Competition competition = new Competition();
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main";
        } /*else {
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);

        }*/
        return "addCompetition";
    }
}


