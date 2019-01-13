package kz.ivc.games.controller;

import kz.ivc.games.dto.GameForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.dto.ResultDTO;
import kz.ivc.games.dto.ResultGameDTO;
import kz.ivc.games.entity.*;
import kz.ivc.games.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private DezhurnyRepo dezhurnyRepo;
    private PhotoRepo photoRepo;

    public GameController(CompetitationRepo competitationRepo, GameRepo gameRepo, GamerRepo gamerRepo, PartnerRepo partnerRepo,DezhurnyRepo dezhurnyRepo,PhotoRepo photoRepo) {
        this.gameRepo = gameRepo;
        this.gamerRepo = gamerRepo;
        this.partnerRepo = partnerRepo;
        this.competitationRepo = competitationRepo;
        this.dezhurnyRepo=dezhurnyRepo;
        this.photoRepo= photoRepo;
    }

    //***********************************GameCompetition*******************************************************

    @RequestMapping(value = "/Competition/{idCompetition}/showGames", method = RequestMethod.GET)
    public String showGame(@ModelAttribute("model") ModelMap model, @PathVariable Long idCompetition,final HttpServletRequest request) {
        Photo photo=this.photoRepo.findByIdCompetition(idCompetition);
        String container="ContainerIsEmpty";
        if (photo==null){
            model.put("container",container);
        }
        model.put("photo",photo);

        model.addAttribute("resource",resource);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        LOG.info("userName="+userName+" "+authentication.isAuthenticated());
        model.put("isAuthenticated",!userName.equals("anonymousUser"));



        Competition competition = this.competitationRepo.getOne(idCompetition);
        model.put("competition", competition);

        List<Partner> partnerList = this.partnerRepo.findByIdCompetition(idCompetition);

        List<ResultDTO> resultDTOList = new ArrayList<ResultDTO>() ;
        int agrBall;
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany

        for (Partner partner1 : partnerList) {


            agrBall = 0;
            agrPoint1 = 0;  // salgan sharlar sany
            agrPoint2 = 0;  // jibergen sharlar sany
            boolean dezh=false;

            //*********По партнерам найтти имена играков1
            ResultDTO resultDTO = new ResultDTO();
            Gamer gamer1 = this.gamerRepo.getOne(partner1.getIdGamer());
            resultDTO.setId(gamer1.getId());
            resultDTO.setNick(gamer1.getNick());


            // ********Игрок2
            List<ResultGameDTO> resultGameDTOS = new ArrayList<>();

            for (Partner partner2 : partnerList) {
                ResultGameDTO resultGameDTO = new ResultGameDTO();

                if (partner1.getId() != partner2.getId()) {

                    //******Create game where play Gamer1 and Gamer2
                    Game game=this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner1.getId(), partner2.getId(), idCompetition);
                    Game gameMirror = this.gameRepo.findByIdPartner1AndIdPartner2AndIdCompetition(partner2.getId(), partner1.getId(), idCompetition);


                    //***********Game
                    if (game != null) {
                        resultGameDTO.setId(game.getId());

                        Gamer gamer2 = this.gamerRepo.getOne(partner2.getIdGamer());
                        Dezhurny dezhurny=this.dezhurnyRepo.findByIdCompetitionAndIdGamer(idCompetition,3L);
                        if(dezhurny!=null){
                            dezh=true;
                        }
                        resultGameDTO.setNick(gamer2.getNick());
                        resultGameDTO.setId(game.getId());
                        resultGameDTO.setIdGamer(gamer2.getId());
                        resultGameDTO.setIdCompetition(idCompetition);
                        resultGameDTO.setPoint1(game.getPoint1());
                        resultGameDTO.setPoint2(game.getPoint2());

                        agrPoint1 = agrPoint1 + game.getPoint1();
                        agrPoint2 = agrPoint2 + game.getPoint2();

                        if (game.getPoint1() > game.getPoint2()) {
                            ++agrBall;
                        }

                    } else {

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
                        }else{
                            //resultGameDTO.setNick(gamer2.getNick());
                            //resultGameDTO.setId(newGame.getId());
                            resultGameDTO.setIdGamer(partner2.getIdGamer());
                            resultGameDTO.setNick(gamer1.getNick());
                            resultGameDTO.setIdCompetition(idCompetition);
                        }
                    }
                } else {
                    resultGameDTO.setIdGamer(gamer1.getId());
                }

                resultGameDTOS.add(resultGameDTO);
            }
            resultDTO.setDezhuril(dezh);
            resultDTO.setAgrPoint1(agrPoint1);
            resultDTO.setAgrPoint2(agrPoint2);

            resultDTO.setAgrBall(agrBall);

            resultDTO.setDeference(agrPoint1 - agrPoint2);

          /*resultGameDTOS.sort(Comparator.comparing(ResultGameDTO :: getPoint1)
                    .thenComparing(Comparator.comparing(ResultGameDTO :: getPoint2))
          );
         */

            resultDTO.setGameList(resultGameDTOS);
            LOG.info("*****this is gameList"+resultGameDTOS);
            resultDTOList.add(resultDTO);
        }

        //******* sort by deference and agrBall
        resultDTOList.sort(Comparator.comparing(ResultDTO::getAgrBall)
                .reversed()
                .thenComparing(Comparator.comparing(ResultDTO::getDeference)
                .reversed()) );
        LOG.info("***********************1");
        for (ResultDTO resultDTO : resultDTOList) {
            LOG.info(resultDTO.getNick());
            for (ResultGameDTO dto : resultDTO.getGameList()) {
                LOG.info("     "+dto.getNick() + " " + dto.getId() + " " + dto.getIdGamer());
            }
        }
        LOG.info("***********************2");
        for (ResultDTO resultDTO : resultDTOList) {
            resultDTO.getGameList().sort((o1, o2) -> {
                int answer=0;
                if (o1.getIdGamer()!=o2.getIdGamer()) {
                    for (ResultDTO resultDTO3 : resultDTOList) {
                        if (resultDTO3.getId() == o1.getIdGamer()) {
                            answer = -1;
                            break;
                        }
                        if (resultDTO3.getId() == o2.getIdGamer()) {
                            answer = 1;
                            break;
                        }
                    }
                }
                return answer;
            });
            LOG.info("***********************3");
            for (ResultGameDTO dto : resultDTO.getGameList()) {
                LOG.info(dto.getNick()+" "+dto.getId()+" "+dto.getIdGamer());
            }
        }





        model.addAttribute("results", resultDTOList);
        return "games";
    }

}

