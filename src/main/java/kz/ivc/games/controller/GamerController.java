package kz.ivc.games.controller;

import kz.ivc.games.dto.BestGamersDTO;
import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.entity.*;
import kz.ivc.games.repo.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    //*********************************************************ShowGamersList*******************************************

    @RequestMapping(value = "/competition/{idCompetition}/addGamers", method = RequestMethod.GET)
    public String showAllGamers (@ModelAttribute("model") ModelMap model,@PathVariable Long idCompetition){
        model.addAttribute("resource",resource);
        Competition competition=this.competitationRepo.getOne(idCompetition);
        model.put("competition",competition);

        int agrGame;
        agrGame=0;

        List<GamerOfCompetition> gamers=new ArrayList<>();
        List<Gamer> gamerList = this.gamerRepo.findAll();
        for (Gamer gamer : gamerList) {
            GamerOfCompetition gamerOfCompetition = new GamerOfCompetition();
            List<Partner> games=this.partnerRepo.findByIdGamer(gamer.getId());
            for (Partner game:games){
                agrGame++;
                gamerOfCompetition.setAgrGame(agrGame);
            }

            gamerOfCompetition.setNick(gamer.getNick());
            gamerOfCompetition.setIdGamer(gamer.getId());
            Partner parner=this.partnerRepo.findByIdCompetitionAndIdGamer(idCompetition,gamer.getId());
            if (parner!=null){
                gamerOfCompetition.setChoosed(true);
            }else{
                gamerOfCompetition.setChoosed(false);
            }
            gamers.add(gamerOfCompetition);
            model.put("gamers",gamers);
        }

        return "gamers";
    }








    //*****************************************Edit Gamer********************************************

    @RequestMapping(value = "/editGamer")
    public String editGamer(@PathVariable Long idG){
        Gamer gamer=this.gamerRepo.findOne(idG);
        return "redirect:listGamer";
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

        return "redirect:/Competition/{idC}/showGames";
    }

    @RequestMapping(value = "{idC}/{idGamer}/excludePartnerFromList", method = RequestMethod.GET)
    public String deletePartnerFromList( @PathVariable Long idC,@PathVariable Long idGamer ) {
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

        return "redirect:redirect:/competition/{idC}/addGamers";
    }



    //*********************************************************NewGamer*******************************************

    @RequestMapping(value = {"{idC}/addGamers"}, method = RequestMethod.POST)
    public String gamersSave(ModelMap model,@PathVariable Long idC,GamerForm gamerForm) {

        Gamer newGamer= new Gamer();

        String nick=gamerForm.getNick();
//        System.out.println("************/nick "+nick+"/*********");
        if (nick!=null && nick.length()>0) {
            newGamer.setNick(nick);
            this.gamerRepo.save(newGamer);
        }

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

    @RequestMapping(value = "/deleteGamer", method = {RequestMethod.GET,RequestMethod.POST})
    public String dltGamer(GamerForm gamerForm,@ModelAttribute("model") ModelMap model) {
        try {
            Long id =Long.parseLong(gamerForm.getId());
           // LOG.info("**********************"+String.valueOf(id));
            Gamer gamer = this.gamerRepo.getOne(id);
            this.gamerRepo.delete(gamer);
        }catch(Exception ex){
            String mess="This gamer plays in other game!";
            model.put("mess",mess);
            return "messages";
        }
        return "redirect:/listGamer";
    }
    //***********************************************scoreOfGamers********************************************

    @RequestMapping(value = "/listGamer", method = RequestMethod.GET)
    public String listGamer(@ModelAttribute("model") ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
      //  LOG.info("userName="+userName+" "+authentication.isAuthenticated());
        model.addAttribute("isAuthenticated",!userName.equals("anonymousUser"));

        model.addAttribute("resource",resource);

        List<BestGamersDTO> bestGamerslist=new ArrayList<BestGamersDTO>();
        long agrPoint1;  // salgan sharlar sany
        long agrPoint2;  // jibergen sharlar sany
        long def;
        int agrGame;

        List<Gamer> gamerList=this.gamerRepo.findAll();
        for(Gamer gamer:gamerList) {
            agrPoint2 = 0;  // jibergen sharlar sany
            agrPoint1 = 0;  // salgan sharlar sany
            agrGame=0;
            int agrBall;
            agrBall=0;
            BestGamersDTO bestGamer = new BestGamersDTO();


                GamerOfCompetition gamerOfCompetition = new GamerOfCompetition();
                List<Partner> games=this.partnerRepo.findByIdGamer(gamer.getId());
                for (Partner game:games) {
                    agrGame++;
                    bestGamer.setGameCount(agrGame);
                }
            byte[] ava =gamer.getAvatar();
            if (ava!=null){
               bestGamer.setAvatar(true);
            }
            int cWin;
            cWin=0;
            List<Competition> competitionList=this.competitationRepo.findAll();
            for (Competition competition:competitionList){
                long winner=competition.getWinner_game_id();
                if (winner==gamer.getId()){
                    ++cWin;
                }
            }
            bestGamer.setWin(cWin);
            bestGamer.setId(gamer.getId());
            bestGamer.setNick(gamer.getNick());
            bestGamer.setDescription(gamer.getDescription());


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


        bestGamerslist.get(0);

        model.addAttribute("gamer",bestGamerslist);



        return"listGamer";
    }



    @RequestMapping("/newGamer")
    public String addGamer(GamerForm gamerForm){
        //LOG.info(gamerForm.getId()+" "+gamerForm.getDescription()+" "+gamerForm.getNick());
        if (!"".equals(gamerForm.getId())){
            Long id =Long.parseLong(gamerForm.getId());
            Gamer oldGamer=this.gamerRepo.findOne(id);
            oldGamer.setNick(gamerForm.getNick());
            oldGamer.setDescription(gamerForm.getDescription());
            this.gamerRepo.save(oldGamer);
        }else {
            Gamer g = new Gamer();
            String nick = gamerForm.getNick();
            String description = gamerForm.getDescription();

            if (nick != null && nick.length() > 0) {
                g.setNick(nick);
                g.setDescription(description);
                this.gamerRepo.save(g);
            }
        }
        return "redirect:/listGamer";
    }





    @RequestMapping(value = "/{idG}/uploadAvatar", method =RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String upload(HttpServletResponse response,
                         @PathVariable("idG") Long idG,@RequestParam ("file") MultipartFile file) throws IOException {
       Gamer gamer=this.gamerRepo.findOne(idG);
       gamer.setAvatar(IOUtils.toByteArray(file.getInputStream()));
       gamerRepo.save(gamer);
       response.sendRedirect(String.format("/listGamer", idG));
       return "redirect:/listGamer";
    }





    @RequestMapping(value = "/{idG}/avatar")
    public  void showAvatar(HttpServletResponse response,@PathVariable("idG")  Long idG) throws IOException {
        Gamer gamerAvatar = this.gamerRepo.findOne(idG);
        byte[] avatar = gamerAvatar.getAvatar();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        if (avatar != null){
            outputStream.write(avatar);
        }
        response.flushBuffer();
    }

//    @RequestMapping(value = "/{idG}/saveDescriptionFromArea")
//    public String showAvatar(@PathVariable("idG")  Long idG,GamerForm form){
//        Gamer gamerDescription=this.gamerRepo.findOne(idG);
//        String description=form.getDescription();
//        gamerDescription.setDescription(description);
//        gamerRepo.save(gamerDescription);
//        return "redirect:/listGamer";
//    }

}