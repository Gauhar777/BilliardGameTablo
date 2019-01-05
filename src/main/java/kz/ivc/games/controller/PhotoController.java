package kz.ivc.games.controller;

import kz.ivc.games.dto.PhotoForm;
import kz.ivc.games.entity.Photo;
import kz.ivc.games.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/uploading")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, PhotoForm photoForm) throws IOException {
        String name=photoForm.getName();
        if (file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir=new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile= UUID.randomUUID().toString();
            String resFileName=uuidFile+"."+file.getOriginalFilename();
            /*try {
                file.transferTo(new File(uploadPath+"/"+resFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            file.transferTo(new File(uploadPath+"/"+resFileName));

            Photo newPhoto= new Photo();
            newPhoto.setIdCompetition(1L);
            newPhoto.setName(resFileName);

            photoRepo.save(newPhoto);
        }
        return "redirect:/photo/1";
    }

    @GetMapping("/photo/1")
    public String getPhoto(@ModelAttribute("model") ModelMap model){
        Photo photo = photoRepo.getOne(15L);
        model.put("photo",photo);
        return "photo";
    }
}
