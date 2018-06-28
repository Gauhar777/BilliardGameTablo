package kz.ivc.games.controller;

import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import kz.ivc.games.entity.Partner;
import kz.ivc.games.repo.CompetitationRepo;
import kz.ivc.games.repo.GameRepo;
import kz.ivc.games.repo.GamerRepo;
import kz.ivc.games.repo.PartnerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Controller
public class GamerController {

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

    public GamerController(GamerRepo gamerRepo,GameRepo gameRepo, CompetitationRepo competitationRepo) {
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
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

    //***************************************************ShowGamers*********************************
    @RequestMapping(value = "/competition/{idCompetition}/addGamers", method = RequestMethod.GET)
    public String signInCompetitionFormGet(@ModelAttribute("model") ModelMap model, GamerForm gamerForm,@PathVariable Long idCompetition,Long idG) {
        Competition competition = this.competitationRepo.getOne(idCompetition);
        model.put("competition", competition);

        model.addAttribute("resource",resource);

        List<GamerOfCompetition> answer=new ArrayList<>();

        List<Gamer> gamerList = this.gamerRepo.findAll();
        for (Gamer gamer : gamerList) {
            GamerOfCompetition gamerOfCompetition = new GamerOfCompetition();
            gamerOfCompetition.setFIO(gamer.getFIO());
            gamerOfCompetition.setNick(gamer.getNick());
            gamerOfCompetition.setIdGamer(gamer.getId());
            Partner partner=this.partnerRepo.findByIdCompetitionAndIdGamer(idCompetition,gamer.getId());
            if(partner!=null) {
                gamerOfCompetition.setChoosed(true);
            }else{
                gamerOfCompetition.setChoosed(false);
            }
            answer.add(gamerOfCompetition);
        }

        model.addAttribute("answers", answer);
        return "gamers";
    }

    //*****************************************Select Partners********************************************


    @RequestMapping(value = "{idC}/{idGamer}/choosePartner", method = RequestMethod.GET)
    public String addPartner( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Long competitionId=idC;
        Partner partner=new Partner();
        partner.setIdCompetition(competitionId);
        partner.setIdGamer(idGamer);
        this.partnerRepo.save(partner);

        return "redirect:/competition/{idC}/addGamers";
    }

    @RequestMapping(value = "{idC}/{idGamer}/excludePartner", method = RequestMethod.GET)
    public String deletePartner( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Partner partner=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idGamer);

        Long idPartner=partner.getId();

        List<Game> game=this.gameRepo.findByIdPartner1AndIdCompetition(idPartner,idC);
        if (game != null)  {
            for (Game game1 : game) {
                this.gameRepo.delete(game1);
            }

        }
        List<Game> game2 = this.gameRepo.findByIdPartner2AndIdCompetition(idPartner, idC);
        if (game2 != null) {
            for (Game game1 : game2) {
                this.gameRepo.delete(game1);
            }
        }

        Partner partner1=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idGamer);
        this.partnerRepo.delete(partner1);

        return "redirect:/competition/{idC}/addGamers";
    }

        //*********************************************************NewGamer*******************************************
    @GetMapping("{idC}/addGamers")
    public String formGet(@ModelAttribute("model") ModelMap model,@PathVariable Long idC) {
        Competition competition = this.competitationRepo.getOne(idC);
        model.put("competition", competition);

        model.addAttribute("gamerForm", new GamerForm());

        model.addAttribute("resource",resource);

        return "addGamers";
    }



    @RequestMapping(value = {"{idC}/addGamers"}, method = RequestMethod.POST)
    public String gamersSave(@PathVariable Long idC, @ModelAttribute("model") ModelMap model, GamerForm gamersForm) {


        Gamer gamer = new Gamer();
        String FIO = gamersForm.getFIO();
        String nick = gamersForm.getNick();
        if ((FIO != null && nick != null) && (FIO.length() > 0 && nick.length() > 0)) {
            gamer.setFIO(FIO);
            gamer.setNick(nick);
            this.gamerRepo.save(gamer);
            return "redirect:/competition/{idC}/addGamers";
        } else {
            return "editGamer";
        }
    }


    //***********************************************Delete********************************************
    @RequestMapping(value = "/{idC}/deleteGamer/{id}", method = RequestMethod.GET)
    public String deleteGamer(@PathVariable Long id,@PathVariable Long idC,@ModelAttribute("model") ModelMap model) {
        Gamer gamer = this.gamerRepo.getOne(id);
        this.gamerRepo.delete(gamer);

        return "redirect:/competition/{idC}/addGamers";
    }


}