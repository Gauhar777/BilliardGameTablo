package kz.ivc.games.controller;

import kz.ivc.games.dto.Form;
import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.entity.*;
import kz.ivc.games.repo.CompetitationRepo;
import kz.ivc.games.repo.DezhurnyRepo;
import kz.ivc.games.repo.GamerRepo;
import kz.ivc.games.repo.PartnerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class HelloController {

    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));
    @Autowired
    private final CompetitationRepo competitationRepo;
    private final PartnerRepo partnerRepo;
    @Autowired
    private final GamerRepo gamerRepo;
    private final DezhurnyRepo dezhurnyRepo;
    private Logger LOG = LoggerFactory.getLogger(HelloController.class);

    public HelloController(CompetitationRepo competitationRepo, PartnerRepo partnerRepo, GamerRepo gamerRepo, DezhurnyRepo dezhurnyRepo) {
        this.competitationRepo = competitationRepo;
        this.partnerRepo = partnerRepo;
        this.gamerRepo = gamerRepo;
        this.dezhurnyRepo = dezhurnyRepo;
    }

    //***********************************Show Competition*******************************************************

    /*@GetMapping("/main")
    public String hello(@ModelAttribute("model") ModelMap model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<Competition> competitionList = this.competitationRepo.findAllByOrderByIdDesc();
        model.addAttribute("competitionList", competitionList);

        model.addAttribute("resource",resource);

        return "main";
    }

*/


    @GetMapping("/main")
    public String home(@ModelAttribute("model") ModelMap model,
                       @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("resource",resource);
        return "home";
    }


//***********************************DELETE Competition*******************************************************

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)

    public String deleteCompetition(@PathVariable Long id,@ModelAttribute("model") ModelMap model) {
     try {
         Competition competition = this.competitationRepo.getOne(id);
         this.competitationRepo.delete(competition);
     }catch (Exception ex){
         model.put("mess","Competition did not finish!");
         return "messages";
     }
        return "redirect:/main2";
    }


//***********************************Edit  Competition*******************************************************


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCompetitionFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
        Competition competition = this.competitationRepo.getOne(id);
        model.put("competition", competition);
        model.addAttribute("resource",resource);
        return "editCompetition";
    }


    @RequestMapping(value = {"/editCompetitionSave"}, method = RequestMethod.POST)
    public String competitionFormSubmit(Model model, Form form) {
        String id = form.getId();
        Long Rid = Long.parseLong(id);
        Competition competition = this.competitationRepo.getOne(Rid);
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main2";
        } else {
            /*
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);
            */
            return "error";
        }

    }

    @RequestMapping(value = {"/ediCompetition2"}, method = RequestMethod.POST)
    public String editCompetition2(Model model, Form form) {
        String id = form.getId();
        Long Rid = Long.parseLong(id);
        Competition competition = this.competitationRepo.getOne(Rid);
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            n=competition;
            return "redirect:/addGamers";
        } else {
            /*
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);
            */
            return "error";
        }

    }


    //***********************************Add new Competition*******************************************************


    @GetMapping("/addCompetition")
    public String addCompetitionFormGet(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("resource",resource);
        return "addCompetition";
    }

   @RequestMapping(value = {"/addCompetition"}, method = RequestMethod.POST)
    public String addCompetition(Model model, Form form) {

        Competition competition = new Competition();
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            return "redirect:/main2";
        }
        /*else {
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);

        }*/
       return "addCompetition";
    }

    public Competition n=null;
    @RequestMapping(value = {"/addCompetition2"}, method = RequestMethod.POST)
    public String addCompetition2(Model model, Form form) {

        Competition competition = new Competition();
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
        }
        n=this.competitationRepo.findIdByName(form.getName());
        /*else {
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);

        }*/
        return "redirect:/addGamers";
    }




    //***************************************************ShowGamers*********************************
    @RequestMapping(value = "/addGamers", method = RequestMethod.GET)
    public String signInCompetitionFormGet(@ModelAttribute("model") ModelMap model,   GamerForm gamerForm) {
        Competition competition = n;
        Long idCompetition=n.getId();
        model.put("competition", competition);

        model.addAttribute("resource",resource);

        List<GamerOfCompetition> answer=new ArrayList<>();

        List<Gamer> gamerList = this.gamerRepo.findAll();
        for (Gamer gamer : gamerList) {
            GamerOfCompetition gamerOfCompetition = new GamerOfCompetition();

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

    
    //*******************************************FromErrorPageToBack******************************
    @RequestMapping(value = {"/messages"}, method = RequestMethod.POST)
    public String goBack(Model model, Form form, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if(referer!=null){
            return "redirect:" + referer;
        }else {
            return "main2";
        }
    }

    @GetMapping("/helloJs")
    public String helloJs() {
        return "testJS";
    }
}


