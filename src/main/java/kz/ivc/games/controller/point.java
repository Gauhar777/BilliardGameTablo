package kz.ivc.games.controller;

import kz.ivc.games.dto.*;
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

import java.util.*;

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




    @RequestMapping(value = "/saveInput",method = RequestMethod.POST)
    public String postInput (){
        Game p=new Game();
        p.setIdCompetition(1L);
        p.setIdPartner1(1L);
        p.setIdPartner2(2L);
        p.setPoint1(8L);
        p.setPoint2(5L);
        this.gameRepo.save(p);
        return "/chooseList";
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
        winCounter();
        return "redirect:/Competition/{idC}/showGames";
    }




    public void winCounter(){
        List<Competition> competitionList = this.competitionRepo.findAll();
        for (Competition competition:competitionList) {
            List<Partner> partnerList = this.partnerRepo.findByIdCompetition(competition.getId());
        List<ResultDTO> resultDTOList = new ArrayList<ResultDTO>() ;
        long idOfBestGamer;
        int agrBall;
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany

        for (Partner partner1 : partnerList) {

            idOfBestGamer=0;
            agrBall = 0;
            agrPoint1 = 0;  // salgan sharlar sany
            agrPoint2 = 0;  // jibergen sharlar sany


            //*********По партнерам найтти имена играков1
            ResultDTO resultDTO = new ResultDTO();
            Gamer gamer1 = this.gamerRepo.getOne(partner1.getIdGamer());
            resultDTO.setId(gamer1.getId());

            // ********Игрок2
            List<ResultGameDTO> resultGameDTOS = new ArrayList<>();

            for (Partner partner2 : partnerList) {
                ResultGameDTO resultGameDTO = new ResultGameDTO();

                if (partner1.getId() != partner2.getId()) {

                    Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                    Long idGamer=gamer2.getId();
                    Gamer gamer=this.gamerRepo.findOne(idGamer);

                    //******Create game where play Gamer1 and Gamer2
                    Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1.getId(), partner2.getId(), competition.getId());

                    //***********Game
                    if (game != null) {
                        resultGameDTO.setId(game.getId());

                        resultGameDTO.setId(game.getId());
                        resultGameDTO.setIdGamer(idGamer);
                        resultGameDTO.setPoint1(game.getPoint1());
                        resultGameDTO.setPoint2(game.getPoint2());
                        agrPoint1 = agrPoint1 + game.getPoint1();
                        agrPoint2 = agrPoint2 + game.getPoint2();

                        if (game.getPoint1() > game.getPoint2()) {
                            ++agrBall;
                        }

                    } else {
                            //resultGameDTO.setNick(gamer2.getNick());
                            //resultGameDTO.setId(newGame.getId());
                            resultGameDTO.setIdGamer(partner2.getIdGamer());
                        }

                } else {
                    resultGameDTO.setIdGamer(gamer1.getId());
                }

                resultGameDTOS.add(resultGameDTO);
            }
            resultDTO.setAgrPoint1(agrPoint1);
            resultDTO.setAgrPoint2(agrPoint2);

            resultDTO.setAgrBall(agrBall);

            resultDTO.setDeference(agrPoint1 - agrPoint2);


            resultDTO.setGameList(resultGameDTOS);
            resultDTOList.add(resultDTO);
        }

            //******* sort by deference and agrBall
            resultDTOList.sort(Comparator.comparing(ResultDTO::getAgrBall)
                    .reversed()
                    .thenComparing(Comparator.comparing(ResultDTO::getDeference)
                            .reversed()) );

            if (!resultDTOList.isEmpty()) {
                idOfBestGamer=resultDTOList.get(0).getId();
                System.out.print("///////////////////////////////////////" + idOfBestGamer);
                competition.setWinner_game_id(idOfBestGamer);
            }
            this.competitionRepo.save(competition);
        }
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
