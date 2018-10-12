package kz.ivc.games.controller;

import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.dto.BestGamersDTO;
import kz.ivc.games.dto.ResultDTO;
import kz.ivc.games.entity.*;
import kz.ivc.games.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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
    private DezhurnyRepo dezhurnyRepo;

    public GamerController(GamerRepo gamerRepo,GameRepo gameRepo,PartnerRepo partnerRepo, CompetitationRepo competitationRepo,DezhurnyRepo dezhurnyRepo) {
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
        this.competitationRepo = competitationRepo;
        this.partnerRepo=partnerRepo;
        this.dezhurnyRepo=dezhurnyRepo;
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
    public String signInCompetitionFormGet(@ModelAttribute("model") ModelMap model,
                                           GamerForm gamerForm,@PathVariable Long idCompetition) {
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
            Dezhurny dezhurny=this.dezhurnyRepo.findByIdCompetitionAndIdGamer(idCompetition,gamer.getId());
            if(dezhurny!=null){
                gamerOfCompetition.setDezhuril(true);
            }else{
                gamerOfCompetition.setDezhuril(false);
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
        System.out.println("Deletes"+partner1);
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
    public String gamersSave( @ModelAttribute("newGamer") Gamer newGamer,@PathVariable Long idC, GamerForm gamersForm,ModelMap model) {
        model.addAttribute("name", newGamer.getFIO());
        model.addAttribute("id", newGamer.getNick());
        this.gamerRepo.save(newGamer);
        return "redirect:/competition/{idC}/addGamers";
    }


    //***********************************************Delete********************************************
    @RequestMapping(value = "/{idC}/deleteGamer/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    public String deleteGamer(@ModelAttribute("model") ModelMap model, @PathVariable Long id,@PathVariable Long idC) {
        try {
            Gamer gamer = this.gamerRepo.getOne(id);
            this.gamerRepo.delete(gamer);
        }catch(Exception ex){
            String mess="This gamer plays in other game!";
            model.put("mess",mess);
            return "messages";
        }
        return "redirect:/competition/{idC}/addGamers";
    }
    //***********************************************Dezhurny********************************************

    @RequestMapping(value = "{idC}/{idGamer}/dezhurit", method = RequestMethod.GET)
    public String dezhuritGamer( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Dezhurny dezhurny=new Dezhurny();
        dezhurny.setIdCompetition(idC);
        dezhurny.setIdGamer(idGamer);
        this.dezhurnyRepo.save(dezhurny);
        return "redirect:/competition/{idC}/addGamers";
    }



    @RequestMapping(value = "{idC}/{idGamer}/cancellDezhurny", method = RequestMethod.GET)
    public String deleteDezhurny( @PathVariable Long idC,@PathVariable Long idGamer ) {
        Dezhurny dezhurny=this.dezhurnyRepo.findByIdCompetitionAndIdGamer(idC,idGamer);
        this.dezhurnyRepo.delete(dezhurny);

        return "redirect:/competition/{idC}/addGamers";
    }

    //***********************************************scoreOfGamers********************************************

    @RequestMapping(value = "/listGamer", method = RequestMethod.GET)
    public String listGamer(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("resource",resource);

        List<BestGamersDTO> bestGamerslist=new ArrayList<BestGamersDTO>();
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany
        long def;

        List<Gamer> gamerList=this.gamerRepo.findAll();
        for(Gamer gamer:gamerList) {
            agrPoint2 = 0;  // jibergen sharlar sany
            agrPoint1 = 0;  // salgan sharlar sany
            int agrBall;
            agrBall=0;
            LOG.info("----"+gamer);
            BestGamersDTO bestGamer = new BestGamersDTO();

            bestGamer.setId(gamer.getId());
            bestGamer.setNick(gamer.getNick());
            bestGamer.setFIO(gamer.getFIO());

            List<Partner> partnerList=this.partnerRepo.findByIdGamer(gamer.getId());
            for(Partner partner1:partnerList) {
                List<Game> gameList=this.gameRepo.findByIdPartner1(partner1.getId());
                List<Game> gameMirrorList=this.gameRepo.findByIdPartner2(partner1.getId());

                if(gameList!=null){
                    for (Game game:gameList){
                        if(game.getPoint1()>game.getPoint2()){
                            ++agrBall;
                        }
                        agrPoint1=agrPoint1+game.getPoint1();
                        agrPoint2=agrPoint2+game.getPoint2();
                    }
                }
                if(gameMirrorList!=null){
                    for (Game gameMirror:gameMirrorList){
                        if(gameMirror.getPoint2()>gameMirror.getPoint1()){
                            ++agrBall;
                        }
                        agrPoint1=agrPoint1+gameMirror.getPoint2();
                        agrPoint2=agrPoint2+gameMirror.getPoint1();
                    }
                }

            }
            def=agrPoint1-agrPoint2;
            bestGamer.setAgrBall(agrBall);
            bestGamer.setDeference(def);

            bestGamerslist.add(bestGamer);
        }
        bestGamerslist.sort(Comparator.comparing(BestGamersDTO::getAgrBall)
                .reversed()
                .thenComparing(Comparator.comparing(BestGamersDTO::getDeference)
                .reversed()) );
        model.addAttribute("gamer",bestGamerslist);
        return"listGamer";
    }
}