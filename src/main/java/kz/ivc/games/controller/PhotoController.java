package kz.ivc.games.controller;

import kz.ivc.games.dto.PhotoForm;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Photo;
import kz.ivc.games.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Created by gauha on 1/5/2019.
 */
@Controller
public class PhotoController {
    private Logger LOG = LoggerFactory.getLogger(kz.ivc.games.controller.HelloController.class);
    @Autowired
    private GamerRepo gamerRepo;
    private GameRepo gameRepo;
    private PartnerRepo partnerRepo;
    private CompetitationRepo competitationRepo;
    private DezhurnyRepo dezhurnyRepo;
    private PhotoRepo photoRepo;

    public PhotoController(CompetitationRepo competitationRepo, GameRepo gameRepo, GamerRepo gamerRepo, PartnerRepo partnerRepo,DezhurnyRepo dezhurnyRepo,PhotoRepo photoRepo) {
        this.gameRepo = gameRepo;
        this.gamerRepo = gamerRepo;
        this.partnerRepo = partnerRepo;
        this.competitationRepo = competitationRepo;
        this.dezhurnyRepo=dezhurnyRepo;
        this.photoRepo=photoRepo;
    }

    @Value("${upload.path}")
    private String uploadPath;

    public Competition n=null;

    @PostMapping("/{idC}/uploading")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, PhotoForm photoForm, @PathVariable Long idC) throws IOException {

        String name=photoForm.getName();

        Photo photo = photoRepo.findByIdCompetition(idC);

        String uuidFile= UUID.randomUUID().toString();
        String resFileName=uuidFile+"."+file.getOriginalFilename();

        if (photo==null && file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir=new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            Photo newPhoto= new Photo();
            newPhoto.setIdCompetition(idC);
            newPhoto.setName(resFileName);
            photoRepo.save(newPhoto);
        }else if (photo!=null){
            photo.setName(resFileName);
            photoRepo.save(photo);
        }
        file.transferTo(new File(uploadPath+"/"+resFileName));
        n=this.competitationRepo.findOne(idC);
        return "redirect:/photo";
    }

    @GetMapping("/photo")
    public String getPhoto(@ModelAttribute("model") ModelMap model){
        Competition competition=n;
        Long idC=competition.getId();
        Photo photo = photoRepo.findByIdCompetition(idC);
        model.put("photo", photo);
        model.put("competition", competition);
        return "photo";
    }

    @GetMapping("/{idCom}/photo")
    public String getPhoto2(@ModelAttribute("model") ModelMap model,@PathVariable Long idCom){
        Photo photo = photoRepo.findByIdCompetition(idCom);
        Competition competition=competitationRepo.getOne(idCom);
        model.put("photo",photo);
        model.put("competition",competition);

        return "photo";
    }

}
