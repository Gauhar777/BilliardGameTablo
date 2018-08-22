package kz.ivc.games.controller;

import kz.ivc.games.dto.GameForm;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import kz.ivc.games.entity.Partner;
import kz.ivc.games.repo.CompetitationRepo;
import kz.ivc.games.repo.GameRepo;
import kz.ivc.games.repo.GamerRepo;
import kz.ivc.games.repo.PartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PartnerRepo partnerRepo;
    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));

    public point(GameRepo gameRepo,GamerRepo gamerRepo, CompetitationRepo competitionRepo,PartnerRepo partnerRepo){
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
        this.competitionRepo = competitionRepo;
        this.partnerRepo = partnerRepo;
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
        model.addAttribute("resource", resource);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        model.put("isAuthenticated",!userName.equals("anonymousUser"));


/*
        Partner partner1=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG1);
        Partner partner2=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG2);
        Long idPartner1=partner1.getId();
        Long idPartner2=partner2.getId();

        Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idPartner1,idPartner2,idC);
        Game gameMirror=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idPartner2,idPartner1,idC);

       /* if (game==null && gameMirror==null){
            Game newGame=new Game();
            newGame.setIdPartner1(idPartner1);
            newGame.setIdPartner2(idPartner2);
            newGame.setIdCompetition(Long.parseLong(String.valueOf(idC)));
            this.gameRepo.save(newGame);
            model.put("game",newGame);
        }else if (game==null){
            model.put("game",gameMirror);
        }else {
            model.put("game",game);
        }
*/
    return"postPoint";
    }

    @RequestMapping(value = "{idC}/{idG1}/{idG2}/point",method = RequestMethod.POST)
    public String setPointPage (@ModelAttribute("model") ModelMap model, @PathVariable Long idC,
                                @PathVariable Long idG1,@PathVariable Long idG2,
                                @ModelAttribute("employee") GameForm gameForm) {
        Partner partner1=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG1);
        Partner partner2=this.partnerRepo.findByIdCompetitionAndIdGamer(idC,idG2);
        System.out.println("////////"+partner1+" "+gameForm.getPoint1());
        System.out.println("\\\\\\\\"+partner2);

        Long idPartner1=partner1.getId();
        Long idPartner2=partner2.getId();

        Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idPartner1,idPartner2,idC);
        Game gameMirror=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(idPartner2,idPartner1,idC);
        if (game==null && gameMirror==null) {
            Game newGame = new Game();
            newGame.setIdPartner1(idPartner1);
            newGame.setIdPartner2(idPartner2);
            newGame.setIdCompetition(Long.parseLong(String.valueOf(idC)));
            newGame.setPoint1(Long.parseLong(gameForm.getPoint1()));
            newGame.setPoint2(Long.parseLong(gameForm.getPoint2()));
            this.gameRepo.save(newGame);
        }else if (game!=null) {
            game.setPoint1(Long.parseLong(gameForm.getPoint1()));
            game.setPoint2(Long.parseLong(gameForm.getPoint2()));
            this.gameRepo.save(game);

        }else {
            gameMirror.setPoint1(Long.parseLong(gameForm.getPoint1()));
            gameMirror.setPoint2(Long.parseLong(gameForm.getPoint2()));
            this.gameRepo.save(gameMirror);
        }

        return "redirect:/Competition/{idC}/showGames";
    }





/*    @RequestMapping(value = "{idC}/{idG1}/{idG2}/point",method = RequestMethod.POST)
    public String setPointPage (@ModelAttribute("model") ModelMap model, @PathVariable Long idC,GameForm gameForm){
        Long idGame=Long.parseLong(gameForm.getId());
          Game game = this.gameRepo.getOne(idGame);

        System.out.println("/////////////"+game);
        String point1 = gameForm.getPoint1();
        String point2 =gameForm.getPoint2();
        System.out.println("/////////////"+point1);
        System.out.println("///////\\\\\\\\"+point2);
        if ((point1 == null && point2 == null) && (point1.length() > 0 && point2.length() > 0)) {
            return"postPoint";
        }else{
            game.setPoint1(Long.parseLong(point1));
            game.setPoint2(Long.parseLong(point2));
            this.gameRepo.save(game);
            return "redirect:/Competition/{idC}/showGames";
        }

    }*/
}
