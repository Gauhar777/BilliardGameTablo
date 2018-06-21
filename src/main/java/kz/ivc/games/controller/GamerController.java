package kz.ivc.games.controller;

import kz.ivc.games.dto.Form;
import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Gamer;
import kz.ivc.games.entity.Partner;
import kz.ivc.games.repo.CompetitationRepo;
import kz.ivc.games.repo.GamerRepo;
import kz.ivc.games.repo.PartnerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class GamerController {

    private Logger LOG = LoggerFactory.getLogger(GamerController.class);

    @Autowired
    private final GamerRepo gamerRepo;
    @Autowired
    private final CompetitationRepo competitationRepo;
    @Autowired
    private PartnerRepo partnerRepo;

    public GamerController(GamerRepo gamerRepo, CompetitationRepo competitationRepo) {
        this.gamerRepo = gamerRepo;
        this.competitationRepo = competitationRepo;
        this.partnerRepo=partnerRepo;
    }

    /*    @GetMapping("/gamers")
        public String gamers(@ModelAttribute("model") ModelMap model) {
            List<Gamer> gamerList = this.gamerRepo.findAll();
            model.addAttribute("gamers", gamerList);

            return "gamers";
        }
    */

    //***************************************************AddGAmersToCompetition*********************************
    @RequestMapping(value = "/competition/{id}", method = RequestMethod.GET)
    public String signInCompetitionFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
    Competition competition = this.competitationRepo.getOne(id);
    model.put("competition", competition);
    List<Gamer> gamerList = this.gamerRepo.findAll();
    model.addAttribute("gamers", gamerList);
    return "gamers";
}

    //*****************************************Select Partners********************************************
    @RequestMapping(value = "{idC}/{idG}", method = RequestMethod.GET)
    public String addPartner(@PathVariable Long idG, @PathVariable Long idC  ) {
        Long competitionId=idC;
        Long gamerId=idG;
          LOG.info("gamer Id=" + gamerId);
          LOG.info("competition Id=" + competitionId);
        Partner partner=new Partner();
        partner.setIdCompetition(competitionId);
        partner.setIdGamer(gamerId);
        this.partnerRepo.save(partner);
        return "redirect:/competition/{idC}";
    }

    //*********************************************************NewGamer*******************************************
    @GetMapping("{idC}/addGamers")
    public String formGet(Model model) {
        model.addAttribute("gamerForm", new GamerForm());
        return "addGamers";
    }



    @RequestMapping(value = {"{idC}/addGamers"}, method = RequestMethod.POST)
    public String gamersSave(@PathVariable Long idC, Model model, GamerForm gamersForm) {
        Gamer gamer = new Gamer();
        String FIO = gamersForm.getFIO();
        String nick = gamersForm.getNick();
        LOG.info("gamersForm.getNick=" + gamersForm.getNick());
        LOG.info("gamersForm.getFIO()=" + gamersForm.getFIO());
        if ((FIO != null && nick != null) && (FIO.length() > 0 && nick.length() > 0)) {
            LOG.info("GamersSave****1");
            gamer.setFIO(FIO);
            gamer.setNick(nick);
            this.gamerRepo.save(gamer);
            return "redirect:/competition/{idC}";
        } else {
            return "editGamer";
        }
    }



    //***********************************************Delete********************************************
    @RequestMapping(value = "/{idC}/deleteGamer/{id}", method = RequestMethod.GET)
    public String deleteGamer(@PathVariable Long id,@PathVariable Long idC) {
        Gamer gamer = this.gamerRepo.getOne(id);
        this.gamerRepo.delete(gamer);
        return "redirect:/competition/{idC}";
    }






    @GetMapping("/partners")
    public String partner(@ModelAttribute("model") ModelMap model) {
        List<Partner> partnerList = this.partnerRepo.findAll();
        model.addAttribute("partners", partnerList);

        return "partners";
    }


}