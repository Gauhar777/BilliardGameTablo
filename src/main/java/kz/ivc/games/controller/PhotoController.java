package kz.ivc.games.controller;

import kz.ivc.games.dto.PhotoForm;
import kz.ivc.games.entity.Competition;
import kz.ivc.games.entity.Photo;
import kz.ivc.games.repo.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by gauha on 1/5/2019.
 */
@Controller
public class PhotoController {
    private final ResourceBundle resource = ResourceBundle.getBundle("kz.ivc.games.inter",
            new Locale("ru"));
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

//    @Value("${upload.path}")
//    private String uploadPath;

    public Competition n=null;



    @RequestMapping(value = "/{idC}/uploading", method =RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String upload(HttpServletResponse response,
                               @RequestParam ("file") MultipartFile file,
                               @PathVariable Long idC) throws IOException {

        List<Photo>existPhoto=this.photoRepo.findByIdCompetition(idC);

        if (existPhoto==null){
            Photo photo = new Photo();
            photo.setData(IOUtils.toByteArray(file.getInputStream()));
            photo.setIdCompetition(idC);
            photo.setName(file.getOriginalFilename());
            photoRepo.save(photo);
        }else {
            for (Photo photo:existPhoto){
                this.photoRepo.delete(photo.getId());
            }
            Photo photo = new Photo();
            photo.setData(IOUtils.toByteArray(file.getInputStream()));
            photo.setIdCompetition(idC);
            photo.setName(file.getOriginalFilename());
            photoRepo.save(photo);
        }

        response.sendRedirect(String.format("/%d/photo", idC));
        return "redirect:/{idC}/photo";
    }

    @RequestMapping(value = "/{idC}/deletePhoto", method = RequestMethod.GET)
    public  String deletePhoto(@ModelAttribute("model") ModelMap model,@PathVariable Long idC){
        List<Photo> photos = this.photoRepo.findByIdCompetition(idC);
        Photo photo = photos.size() > 0 ? photos.get(0) : null;

        this.photoRepo.delete(photo);
        return "redirect:/Competition/{idC}/showGames";
    }


    @GetMapping("/{idCom}/photo")
    public String getPhoto2(@ModelAttribute("model") ModelMap model,@PathVariable Long idCom){
        List<Photo> photos = this.photoRepo.findByIdCompetition(idCom);
        Photo photo = photos.size() > 0 ? photos.get(0) : null;

        Competition competition=competitationRepo.getOne(idCom);
        model.put("photo",photo);
        model.put("competition",competition);

        return "photo";
    }


    @RequestMapping(value = "/{idC}/img", method = RequestMethod.GET)
    public void download(HttpServletResponse response,
                         @PathVariable("idC") Long idC) throws IOException {
//        Photo photo=photoRepo.findByIdCompetition(idC);
        Photo photo=photoRepo.getOne(idC);
        byte[] f = photo.getData();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(f);
        //IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/{idPh}/CompetitionPhoto", method = RequestMethod.GET)
    public void ddd(HttpServletResponse response,
                         @PathVariable("idPh") Long idPh) throws IOException {
//        Photo photo=photoRepo.findByIdCompetition(idC);
        List<Photo> photos=photoRepo.findByIdCompetition(idPh);
        Photo photo = photos.size() > 0 ? photos.get(0) : null;
        byte[] f = photo.getData();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(f);
        //IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/{idC}/img2")
    @ResponseBody
    public byte[] servePhoto(@PathVariable("idC") Long idC){
        List<Photo> photos = this.photoRepo.findByIdCompetition(idC);
        Photo photo = photos.size() > 0 ? photos.get(0) : null;
        byte[] f =photo.getData();
        return f;
     }


    @RequestMapping({"/galery"})
    public String showGalery(@ModelAttribute("model") ModelMap model) {
        List<Photo> photoList = this.photoRepo.findAll();
        model.addAttribute("resource", this.resource);
        model.addAttribute("photoList", photoList);
        return "galery";
    }
}
