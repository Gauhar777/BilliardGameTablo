package kz.ivc.games.controller;

import kz.ivc.games.entity.Competition;
import kz.ivc.games.repo.CompetitationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class CompititionController {

        private Logger LOG = LoggerFactory.getLogger(kz.ivc.games.controller.HelloController.class);

        private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
                new Locale("ru"));

        @Autowired
        private final CompetitationRepo competitationRepo;

        public CompititionController(CompetitationRepo competitationRepo) {
            this.competitationRepo = competitationRepo;
        }

        @GetMapping("/home")
        public String myHome(){
            return "home";
        }

    @GetMapping("/error")
    public String myError(){
        return "error";
    }

    //***********************************Show Competition*******************************************************

    @RequestMapping(value = "/main2", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView main2(Model model, final HttpServletRequest request) {
            List<Competition> competitionList = this.competitationRepo.findAll();
            model.addAttribute("competitionList", competitionList);

            model.addAttribute("resource", resource);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            LOG.info("userName="+userName+" "+authentication.isAuthenticated());
            model.addAttribute("isAuthenticated",!userName.equals("anonymousUser"));

            return new ModelAndView("listCompetition","model",model);
        }






        @RequestMapping(value = "/loginpage", method = {RequestMethod.GET,RequestMethod.POST})
        public ModelAndView getLoginPage(@RequestParam Optional<String> error,@ModelAttribute("model") ModelMap model) {

            model.addAttribute("resource", resource);

            return new ModelAndView("login", "error", error);
        }


}
