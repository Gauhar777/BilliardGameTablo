package kz.ivc.games.controller;

import kz.ivc.games.entity.Competition;
import kz.ivc.games.repo.CompetitationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class CompititionController {
    @Controller
    public class HelloController {

        private Logger LOG = LoggerFactory.getLogger(kz.ivc.games.controller.HelloController.class);
        private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
                new Locale("ru"));
        @Autowired
        private final CompetitationRepo competitationRepo;

        public HelloController(CompetitationRepo competitationRepo) {
            this.competitationRepo = competitationRepo;
        }

        //***********************************Show Competition*******************************************************

        @GetMapping("/main2")
        public String hello(@ModelAttribute("model") ModelMap model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
            List<Competition> competitionList = this.competitationRepo.findAll();
            model.addAttribute("competitionList", competitionList);

            model.addAttribute("resource", resource);

            return "listCompetition";
        }
    }

    }
