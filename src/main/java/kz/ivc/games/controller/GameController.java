package kz.ivc.games.controller;

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
import sun.swing.BakedArrayList;

import java.sql.ResultSet;
import java.util.*;

@Controller
public class GameController {
    private Logger LOG = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private  GamerRepo gamerRepo;
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

    @RequestMapping(value = "/competition/{idCompetition}/showGames", method = RequestMethod.GET)
    public String showGame(@ModelAttribute("model") ModelMap model, @PathVariable Long idCompetition) {
        Competition competition = this.competitationRepo.getOne(idCompetition);
        model.put("competition", competition);

        Long IdCompetition=1L;
        List<Partner> partnerList=this.partnerRepo.findByIdCompetition(IdCompetition);

        List<ResultDTO> resultDTOList = new ArrayList<ResultDTO>();
        int agrBall;
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany

        for (Partner partner1 : partnerList) {
            agrBall = 0;
            agrPoint1=0;  // salgan sharlar sany
            agrPoint2=0;  // jibergen sharlar sany

            ResultDTO resultDTO = new ResultDTO();
            Gamer gamer1 = this.gamerRepo.getOne(partner1.getIdGamer());
            resultDTO.setId(gamer1.getId());
            resultDTO.setNick(gamer1.getNick());
            // *****
            List<ResultGameDTO> resultGameDTOS=new ArrayList<>();
            for (Partner partner2 : partnerList) {
                ResultGameDTO resultGameDTO = new ResultGameDTO();
                //ResultGameDTO resultGameDTO2 = new ResultGameDTO();
                if (partner1.getId()!=partner2.getId()){
                    Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1.getId(),partner2.getId(),IdCompetition);
                    if (game!=null) {
                        Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                        resultGameDTO.setNick(gamer2.getNick());

                        resultGameDTO.setPoint1(game.getPoint1());
                        resultGameDTO.setPoint2(game.getPoint2());
                        agrPoint1 = agrPoint1 + game.getPoint1();
                        agrPoint2 = agrPoint2 + game.getPoint2();
                        if (game.getPoint1() > game.getPoint2()) {
                            ++agrBall;
                        }
                    }else {
                        Game gameMirror=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner2.getId(),partner1.getId(),IdCompetition);
                        if (gameMirror!=null) {
                            Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                            resultGameDTO.setIdGamer(partner2.getIdGamer());
                            resultGameDTO.setNick(gamer2.getNick());
                            resultGameDTO.setPoint1(gameMirror.getPoint2());
                            resultGameDTO.setPoint2(gameMirror.getPoint1());

                            agrPoint1 = agrPoint1 + gameMirror.getPoint2();
                            agrPoint2 = agrPoint2 + gameMirror.getPoint1();
                            if (gameMirror.getPoint2() > gameMirror.getPoint1()) {
                                ++agrBall;
                            }
                        }else{
                            LOG.error("error "+partner2.getId(),partner1.getId());
                        }
                    }
                }else{
                    resultGameDTO.setIdGamer(gamer1.getId());
                }
                resultGameDTOS.add(resultGameDTO);
            }
            resultDTO.setAgrPoint1(agrPoint1);
            resultDTO.setAgrPoint2(agrPoint2);
            resultDTO.setAgrBall(agrBall);
            resultDTO.setDeference(agrPoint1-agrPoint2);
            resultDTO.setGameList(resultGameDTOS);
            resultDTOList.add(resultDTO);
        }

        //resultDTOList.sort((o1, o2) -> new Integer(o2.getAgrBall()).compareTo(o1.getAgrBall()));

        /**/
        Collections.sort(resultDTOList, new sortByDef());
        model.addAttribute("results", resultDTOList);
        return "game";

    }

}
