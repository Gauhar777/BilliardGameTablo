package kz.ivc.games.controller;

import kz.ivc.games.entity.Dezhurny;
import kz.ivc.games.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class DezhurnyController {
    private Logger LOG = LoggerFactory.getLogger(GamerController.class);

    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));
    @Autowired
    private final GamerRepo gamerRepo;
    private final GameRepo gameRepo;
    @Autowired
    private final CompetitationRepo competitationRepo;
    @Autowired
    private PartnerRepo partnerRepo;
    private DezhurnyRepo dezhurnyRepo;

    public DezhurnyController(GamerRepo gamerRepo, GameRepo gameRepo, PartnerRepo partnerRepo, CompetitationRepo competitationRepo, DezhurnyRepo dezhurnyRepo) {
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
        this.competitationRepo = competitationRepo;
        this.partnerRepo=partnerRepo;
        this.dezhurnyRepo=dezhurnyRepo;
    }

    //***********************************************Dezhurny********************************************

    @RequestMapping(value = "{idC}/{idGamer}/dezhurit", method = RequestMethod.GET)
    public String dezhuritGamer( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Dezhurny dezhurny=new Dezhurny();
        dezhurny.setIdCompetition(idC);
        dezhurny.setIdGamer(idGamer);
        this.dezhurnyRepo.save(dezhurny);
        return "redirect:/Competition/{idC}/showGames";
    }



    @RequestMapping(value = "{idC}/{idGamer}/cancellDezhurny", method = RequestMethod.GET)
    public String deleteDezhurny( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Dezhurny dezhurny=this.dezhurnyRepo.findByIdCompetitionAndIdGamer(idC,idGamer);
        this.dezhurnyRepo.delete(dezhurny);

        return "redirect:/Competition/{idC}/showGames";
    }

}
