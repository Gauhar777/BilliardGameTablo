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
import java.util.*;

@Controller
public class HelloController {

    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));
    @Autowired
    private final CompetitationRepo competitationRepo;
    private Logger LOG = LoggerFactory.getLogger(HelloController.class);

    public HelloController(CompetitationRepo competitationRepo) {
        this.competitationRepo = competitationRepo;
    }

    //***********************************Show Competition*******************************************************

    @GetMapping("/main")
    public String hello(@ModelAttribute("model") ModelMap model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<Competition> competitionList = this.competitationRepo.findAllByOrderByIdDesc();
        model.addAttribute("competitionList", competitionList);

        model.addAttribute("resource",resource);

        return "main";
    }


//***********************************DELETE Competition*******************************************************

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCompetition(@PathVariable Long id) {
        Competition competition = this.competitationRepo.getOne(id);
        this.competitationRepo.delete(competition);
        return "redirect:/main2";
    }


//***********************************Edit  Competition*******************************************************


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCompetitionFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
        Competition competition = this.competitationRepo.getOne(id);
        model.put("competition", competition);
        model.addAttribute("resource",resource);
        return "editCompetition";
    }


    @RequestMapping(value = {"/editCompetitionSave"}, method = RequestMethod.POST)
    public String competitionFormSubmit(Model model, Form form) {
        String id = form.getId();
        Long Rid = Long.parseLong(id);
        Competition competition = this.competitationRepo.getOne(Rid);
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main2";
        } else {
            /*
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);
            */
            return "error";
        }

    }

    //***********************************Add new Competition*******************************************************
    @GetMapping("/addCompetition")
    public String addCompetitionFormGet(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("resource",resource);
        return "addCompetition";
    }


    @RequestMapping(value = {"/addCompetition"}, method = RequestMethod.POST)
    public String addCompetition(Model model, Form form) {
        Competition competition = new Competition();
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main2";
        } /*else {
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);

        }*/
        return "addCompetition";
    }
}


