package kz.ivc.games.controller;

import kz.ivc.games.dto.GameForm;
import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.PointForm;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import kz.ivc.games.entity.Partner;
import kz.ivc.games.repo.CompetitationRepo;
import kz.ivc.games.repo.GameRepo;
import kz.ivc.games.repo.GamerRepo;
import kz.ivc.games.repo.PartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class point {
    @Autowired
    private GameRepo gameRepo;
    private GamerRepo gamerRepo;
    private CompetitationRepo competitionRepo;
    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));

    public point(GameRepo gameRepo,GamerRepo gamerRepo, CompetitationRepo competitionRepo,PartnerRepo partnerRepo){
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
        this.competitionRepo = competitionRepo;
        this.partnerRepo = partnerRepo;
    }







    private PartnerRepo partnerRepo;

    @RequestMapping(value = "{idC}/{idG1}/{idG2}/point",method = RequestMethod.POST)
    public String postPoint(@ModelAttribute("model") ModelMap model, @PathVariable Long idC, @PathVariable Long idG1, @PathVariable Long idG2,GameForm gameForm){

        Partner partner1=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG1);
        Partner partner2=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG2);

        Long partner1Id=partner1.getId();
        Long partner2Id=partner2.getId();
        String point1=gameForm.getPoint1();
        String point2=gameForm.getPoint2();
        Game oldGame=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1Id,partner2Id,idC);
        if (point1!=null && point2!=null && point1.length()>0 && point2.length()>0) {
            if (oldGame==null) {
                Game newGame= new Game();
                newGame.setPoint1(Long.parseLong(point1));
                newGame.setPoint2(Long.parseLong(point2));

                newGame.setIdPartner1(partner1Id);
                newGame.setIdPartner2(partner2Id);

                newGame.setIdCompetition(idC);
                this.gameRepo.save(newGame);
            }else{
                oldGame.setPoint1(Long.parseLong(point1));
                oldGame.setPoint2(Long.parseLong(point2));
                this.gameRepo.save(oldGame);
            }
        }
        return "redirect:/Competition/{idC}/showGames";
    }




    @RequestMapping(value = "{idC}/{idG1}/{idG2}/point",method = RequestMethod.GET)
    public String getPointPage (@ModelAttribute("model") ModelMap model, @PathVariable Long idC,@PathVariable Long idG1,@PathVariable Long idG2){
        //**************************Gamers**********************************
        Competition competition=this.competitionRepo.findOne(idC);
        Gamer gamer1=this.gamerRepo.getOne(idG1);
        Gamer gamer2=this.gamerRepo.getOne(idG2);

        model.put("gamer1",gamer1);
        model.put("gamer2",gamer2);
        model.put("competition",competition);
        Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idG1,idG2,idC);
        Game gameMirror=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idG2,idG1,idC);

        if (game==null && gameMirror==null) {
            int myPoint=0;
            model.addAttribute("point2",myPoint);
            model.addAttribute("point1",myPoint);
        }else if(game==null ){
            model.addAttribute("point1",gameMirror.getPoint2());
            model.addAttribute("point2",gameMirror.getPoint1());
        }else{
            model.put("point1",game.getPoint1());
            model.put("point2",game.getPoint2());
        }

        model.addAttribute("resource", resource);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        model.put("isAuthenticated",!userName.equals("anonymousUser"));

        return"postPoint";
    }
}
