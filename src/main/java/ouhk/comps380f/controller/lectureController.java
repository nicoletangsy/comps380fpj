package ouhk.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.LectureCommentRepository;
import ouhk.comps380f.model.Lecture;
import ouhk.comps380f.dao.lectureRepository;
import ouhk.comps380f.model.LectureComment;
import java.security.Principal;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.dao.AttachmentRepository;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.view.DownloadingView;

@Controller
@RequestMapping("lecture")
public class lectureController {

    @Autowired
    lectureRepository lectureRepo;

    @Autowired
    LectureCommentRepository comRepo;

    @Autowired
    AttachmentRepository attRepo;

    public static class CommentForm {

        private String lectureId;
        private String user;
        private String content;

        public void setLectureId(String id) {
            lectureId = id;
        }

        public String getLectureId() {
            return lectureId;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    @RequestMapping(value = "view/{lecture}", method = RequestMethod.GET)
    public ModelAndView viewPoll(@PathVariable("lecture") String lecture, Principal principal) {
        ModelAndView MAV = new ModelAndView();
        MAV.setViewName("viewLecture");
        MAV.addObject("lecture", lectureRepo.findOne(Integer.parseInt(lecture)));
        MAV.addObject("comments", comRepo.readLectureCommentByLectureOrderById(lectureRepo.findOne(Integer.parseInt(lecture))));
        MAV.addObject("user", principal.getName());
        MAV.addObject("commentForm", new CommentForm());
        MAV.addObject("Attachments", attRepo.readAttachmentByLectureOrderById(lectureRepo.findOne(Integer.parseInt(lecture))));
        return MAV;
    }

    public static class Form {

        private String title;
        private List<MultipartFile> files;

        public List<MultipartFile> getFiles() {
            return files;
        }

        public void setFiles(List<MultipartFile> files) {
            this.files = files;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("addLecture", "lecture", new Form());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View create(Form form) throws IOException {
        Lecture lecture = new Lecture(form.getTitle());
        lectureRepo.save(lecture);
        if (form.getFiles() != null && form.getFiles().size() > 0) {
            for (MultipartFile file : form.getFiles()) {
                if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 0) {
                    Attachment att = new Attachment(file.getOriginalFilename(), file.getContentType(), file.getBytes(), lecture);
                    attRepo.save(att);
                }
            }
        }
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "delete/{lecture}", method = RequestMethod.GET)
    public View deleteLecture(@PathVariable("lecture") String lecture) {
        lectureRepo.delete(lectureRepo.findOne(Integer.parseInt(lecture)));
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "view/comment/{lecture}", method = RequestMethod.POST)
    public View createComment(CommentForm form) {
        if (form.getContent().trim().isEmpty()) {
            return new RedirectView("/lecture/view/" + form.getLectureId(), true);
        }
        LectureComment com = new LectureComment(lectureRepo.findOne(Integer.parseInt(form.getLectureId())), form.getUser(), form.getContent());
        comRepo.save(com);
        return new RedirectView("/lecture/view/" + form.getLectureId(), true);
    }

    @RequestMapping(value = "comment/delete/{lecture}/{comment}", method = RequestMethod.GET)
    public View deleteComment(@PathVariable("lecture") String lecture, @PathVariable("comment") String comment) {
        LectureComment i = comRepo.findOne(Integer.parseInt(comment));
        i.setLecture(null);
        comRepo.save(i);
        comRepo.delete(Integer.parseInt(comment));
        return new RedirectView("/lecture/view/" + lecture, true);
    }

    @RequestMapping(value = "Attachment/delete/{lecture}/{attachment}", method = RequestMethod.GET)
    public View deleteAttachment(@PathVariable("lecture") String lecture, @PathVariable("attachment") String attachment) {
        Attachment i = attRepo.findOne(Integer.parseInt(attachment));
        i.setLecture(null);
        attRepo.save(i);
        attRepo.delete(Integer.parseInt(attachment));
        return new RedirectView("/lecture/view/" + lecture, true);
    }

    public static class attForm {

        private List<MultipartFile> files;

        public List<MultipartFile> getFiles() {
            return files;
        }

        public void setFiles(List<MultipartFile> files) {
            this.files = files;
        }
    }

    @RequestMapping(value = "Attachment/add/{lecture}", method = RequestMethod.GET)
    public ModelAndView Attachment(@PathVariable("lecture") String lecture) {
        ModelAndView MAV = new ModelAndView();
        MAV.setViewName("addAttachment");
        MAV.addObject("lecture", lectureRepo.findOne(Integer.parseInt(lecture)));
        MAV.addObject("att", new attForm());
        return MAV;
    }

    @RequestMapping(value = "Attachment/add/{lecture}", method = RequestMethod.POST)
    public View addAttachment(@PathVariable("lecture") String lecture, attForm form) throws IOException {
        Lecture i = lectureRepo.findOne(Integer.parseInt(lecture));
        if (form.getFiles() != null && form.getFiles().size() > 0) {
            for (MultipartFile file : form.getFiles()) {
                Attachment att = new Attachment(file.getOriginalFilename(), file.getContentType(), file.getBytes(), i);
                attRepo.save(att);
            }
        }
        return new RedirectView("/lecture/view/" + i.getId(), true);
    }

    @RequestMapping(value = "Attachment/download/{attachment}", method = RequestMethod.GET)
    public View downloadAttachment(@PathVariable("attachment") String att) {
        Attachment i = attRepo.findOne(Integer.parseInt(att));
        if (i != null) {
            return new DownloadingView(i.getName(), i.getMimeContentType(), i.getContents());
        }
        return new RedirectView("/", true);
    }
}
