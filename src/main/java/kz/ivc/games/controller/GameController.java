package kz.ivc.games.controller;

import kz.ivc.games.dto.GameForm;
import kz.ivc.games.dto.ResultDTO;
import kz.ivc.games.dto.ResultGameDTO;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Controller
public class GameController {
    private Logger LOG = LoggerFactory.getLogger(HelloController.class);
    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));
    @Autowired
    private GamerRepo gamerRepo;
    private GameRepo gameRepo;
    private PartnerRepo partnerRepo;
    private CompetitationRepo competitationRepo;

    public GameController(CompetitationRepo competitationRepo, GameRepo gameRepo, GamerRepo gamerRepo, PartnerRepo partnerRepo) {
        this.gameRepo = gameRepo;
        this.gamerRepo = gamerRepo;
        this.partnerRepo = partnerRepo;
        this.competitationRepo = competitationRepo;
    }

    //***********************************GameCompetition*******************************************************

    @RequestMapping(value = "/Competition/{idCompetition}/showGames", method = RequestMethod.GET)
    public String showGame(@ModelAttribute("model") ModelMap model, @PathVariable Long idCompetition) {
        Competition competition = this.competitationRepo.getOne(idCompetition);
        model.put("competition", competition);
        model.addAttribute("resource",resource);
        List<Partner> partnerList = this.partnerRepo.findByIdCompetition(idCompetition);

        List<ResultDTO> resultDTOList = new ArrayList<ResultDTO>();
        int agrBall;
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany

        for (Partner partner1 : partnerList) {
            agrBall = 0;
            agrPoint1 = 0;  // salgan sharlar sany
            agrPoint2 = 0;  // jibergen sharlar sany

            ResultDTO resultDTO = new ResultDTO();
            Gamer gamer1 = this.gamerRepo.getOne(partner1.getIdGamer());
            resultDTO.setId(gamer1.getId());
            resultDTO.setNick(gamer1.getNick());
            // *****
            List<ResultGameDTO> resultGameDTOS = new ArrayList<>();
            for (Partner partner2 : partnerList) {
                ResultGameDTO resultGameDTO = new ResultGameDTO();
                //ResultGameDTO resultGameDTO2 = new ResultGameDTO();
                if (partner1.getId() != partner2.getId()) {
                    Game searchGame=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1.getId(), partner2.getId(), idCompetition);
                    Game searchGameMirror = this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner2.getId(), partner1.getId(), idCompetition);
                    if(searchGame == null) {
                        if (searchGameMirror==null) {
                            Game newGame = new Game();
                            newGame.setIdCompetition(idCompetition);
                            newGame.setIdPartner1(partner1.getId());
                            newGame.setIdPartner2(partner2.getId());
                            newGame.setPoint1(0L);
                            newGame.setPoint2(0L);
                            this.gameRepo.save(newGame);
                        }
                    }
                    Game game = this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1.getId(), partner2.getId(), idCompetition);

                    if (game != null) {
                        long idGame = game.getId();
                        resultGameDTO.setId(idGame);
                        Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                        resultGameDTO.setNick(gamer2.getNick());
                        resultGameDTO.setIdCompetition(idCompetition);

                        resultGameDTO.setPoint1(game.getPoint1());
                        resultGameDTO.setPoint2(game.getPoint2());
                        agrPoint1 = agrPoint1 + game.getPoint1();
                        agrPoint2 = agrPoint2 + game.getPoint2();
                        if (game.getPoint1() > game.getPoint2()) {
                            ++agrBall;
                        }
                    } else {
                        Game gameMirror = this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner2.getId(), partner1.getId(), idCompetition);

                        if (gameMirror != null) {
                            long idGame = gameMirror.getId();
                            Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                            resultGameDTO.setId(idGame);
                            resultGameDTO.setIdGamer(partner2.getIdGamer());
                            resultGameDTO.setNick(gamer2.getNick());
                            resultGameDTO.setIdCompetition(idCompetition);

                            resultGameDTO.setPoint1(gameMirror.getPoint2());
                            resultGameDTO.setPoint2(gameMirror.getPoint1());

                            agrPoint1 = agrPoint1 + gameMirror.getPoint2();
                            agrPoint2 = agrPoint2 + gameMirror.getPoint1();
                            if (gameMirror.getPoint2() > gameMirror.getPoint1()) {
                                ++agrBall;
                            }
                        } else {
                        }
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

            //resultDTOList.sort(Comparator.comparing(resultDTO::getAgrBall).thenComparing(resultDTO::getDeference));

        }


        for (int i = 0; i < resultDTOList.size(); i++) {
            System.out.println(resultDTOList.get(i));
        }
        System.out.println(resultDTOList);
        model.addAttribute("results", resultDTOList);
        return "game";

    }


    //*********************************************Add points********************************************

    @RequestMapping(value = "/{idC}/{idG}/addPoint", method = RequestMethod.GET)
    public String addPointFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long idG,  @PathVariable Long idC) {

        Game game = this.gameRepo.getOne(idG);
        model.addAttribute("resource",resource);
        model.put("game", game);

        Long idPartner1=game.getIdPartner1();
        Long idPartner2=game.getIdPartner2();

        Partner partner1=this.partnerRepo.getOne(idPartner1);
        Partner partner2=this.partnerRepo.getOne(idPartner2);

        Long idGamer1=partner1.getIdGamer();
        Long idGamer2=partner2.getIdGamer();

        Gamer gamer1=this.gamerRepo.getOne(idGamer1);
        Gamer gamer2=this.gamerRepo.getOne(idGamer2);

        Competition competition = this.competitationRepo.getOne(idC);
        model.put("competition",competition);

        model.put("gamer1",gamer1);
        model.put("gamer2",gamer2);
        return "addPoint";
    }


    @RequestMapping(value = {"/{idC}/{idG}/addPoint"}, method = RequestMethod.POST)
    public String gameFormSubmit(@ModelAttribute("model") ModelMap model,  GameForm gameForm,Game game,@PathVariable Long idG,Long idC) {

        long rId=Long.parseLong(String.valueOf(idG));
        game = this.gameRepo.getOne(rId);

        String point1 = gameForm.getPoint1();
        String point2 = gameForm.getPoint2();
        if ((point1 != null && point2 != null) && (point1.length() > 0 && point2.length() > 0)) {
            game.setPoint1(Long.parseLong(point1));
            game.setPoint2(Long.parseLong(point2));
            this.gameRepo.save(game);
            return "redirect:/Competition/{idC}/showGames";
        }
        return "redirect:/editGameSave";

    }
}
