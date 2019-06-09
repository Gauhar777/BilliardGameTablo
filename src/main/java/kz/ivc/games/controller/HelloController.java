package kz.ivc.games.controller;

import kz.ivc.games.dto.Form;
import kz.ivc.games.dto.GamerForm;
import kz.ivc.games.dto.GamerOfCompetition;
import kz.ivc.games.entity.*;
import kz.ivc.games.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PhotoRepo photoRepo;
    @Autowired
    private final GamerRepo gamerRepo;
    private final GameRepo gameRepo;
    private final DezhurnyRepo dezhurnyRepo;
    private Logger LOG = LoggerFactory.getLogger(HelloController.class);

    public HelloController(CompetitationRepo competitationRepo, PartnerRepo partnerRepo, GamerRepo gamerRepo, GameRepo gameRepo, DezhurnyRepo dezhurnyRepo, PhotoRepo photoRepo) {
        this.competitationRepo = competitationRepo;
        this.partnerRepo = partnerRepo;
        this.gamerRepo = gamerRepo;
        this.gameRepo = gameRepo;
        this.dezhurnyRepo = dezhurnyRepo;
        this.photoRepo = photoRepo;
        //this.
    }
    //*****************************************Admin***************************************************************************

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAdmission(@ModelAttribute("model") ModelMap model){
        model.addAttribute("resource", resource);
        return "admission";
    }

    @RequestMapping(value = "/chooseList")
    public String chooseList(@ModelAttribute("model") ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        LOG.info("userName="+userName+" "+authentication.isAuthenticated());
        model.put("isAuthenticated",!userName.equals("anonymousUser"));
        model.addAttribute("resource", resource);
        return "chooseList";
    }






//***********************************DELETE Competition*******************************************************

    @RequestMapping(value = "/delete/{id}")

    public String deleteCompetition(@PathVariable Long id, @ModelAttribute("model") ModelMap model) {
 //       Photo photoOfCompetition = photos.size() > 0 ? photos.get(0) : null;
//        Photo photoOfCompetition=photos.get(0);
        Dezhurny dezhurnyOfCompetition = this.dezhurnyRepo.findByIdCompetition(id);


        List<Partner> partnerOfCompetition = this.partnerRepo.findByIdCompetition(id);
        List<Game> gameOfCompetition = this.gameRepo.findByIdCompetition(id);


        if (!this.photoRepo.findByIdCompetition(id).isEmpty()) {
            List<Photo> photos = this.photoRepo.findByIdCompetition(id);
            for (Photo photoOfCompetition:photos) {
                    this.photoRepo.delete(photoOfCompetition);
                }
            }


            for (Game game : gameOfCompetition) {
                this.gameRepo.delete(game);
            }

            if (partnerOfCompetition != null) {
                for (Partner partner : partnerOfCompetition) {
                    this.partnerRepo.delete(partner);
                }
            }
            if(this.dezhurnyRepo.findByIdCompetition(id)!=null){
                this.dezhurnyRepo.delete(dezhurnyOfCompetition);
            }

        Competition competition = this.competitationRepo.getOne(id);
        this.competitationRepo.delete(competition);
        return "redirect:/main2";
    }


//***********************************Edit  Competition*******************************************************


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCompetitionFormGet(@ModelAttribute("model") ModelMap model, @PathVariable Long id) {
        Competition competition = this.competitationRepo.getOne(id);
        model.put("competition", competition);
        model.addAttribute("resource", resource);
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
            n = competition;
            return "redirect:/competition/" + n.getId() + "/addGamers";
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
        model.addAttribute("resource", resource);
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

    public Competition n = null;

    @RequestMapping(value = {"/addCompetition2"}, method = RequestMethod.POST)
    public String addCompetition2(Model model, Form form) {

        Competition competition = new Competition();
        String name = form.getName();
        if (name != null && name.length() > 0) {
            competition.setName(name);
            this.competitationRepo.save(competition);
            n = this.competitationRepo.findIdByName(form.getName());
            return "redirect:/competition/" + n.getId() + "/addGamers";
        }
        return "addCompetition";

       /*else {
            String error = "Name is required!";
            model.addAttribute("errorMessage", error);

        }*/

    }


    //*******************************************FromErrorPageToBack******************************
    @RequestMapping(value = {"/messages"}, method = RequestMethod.POST)
    public String goBack(Model model, Form form, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "main2";
        }
    }

    @GetMapping("/helloJs")
    public String helloJs() {
        return "testJS";
    }
}


