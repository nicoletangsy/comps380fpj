package ouhk.comps380f.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Lecture;
import ouhk.comps380f.model.Polls;
import ouhk.comps380f.dao.lectureRepository;
import ouhk.comps380f.dao.PollsRepository;
import ouhk.comps380f.view.DownloadingView;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    lectureRepository lectureRepo;
    
    @Autowired
    PollsRepository pollRepo;
    
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("lecture", lectureRepo.findAll());
        model.addAttribute("poll", pollRepo.findAll());
        return "list";
    }
}