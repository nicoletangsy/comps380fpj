package ouhk.comps380f.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
import ouhk.comps380f.model.course;
import ouhk.comps380f.view.DownloadingView;

@Controller
@RequestMapping("course")
public class CourseController {

    private volatile long TICKET_ID_SEQUENCE = 1;
    private Map<Long, course> courseDatabase = new Hashtable<>();

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("courseDatabase", courseDatabase);
        return "list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("add", "courseForm", new Form());
    }

    public static class Form {

        private String customerName;
        private String subject;
        private String body;
        private List<MultipartFile> attachments;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View create(Form form) throws IOException {
        course course = new course();
        //course.setId(this.getNextcourseId());
        //course.setCustomerName(form.getCustomerName());
        //course.setSubject(form.getSubject());
        //course.setBody(form.getBody());

        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                //course.addAttachment(attachment);
            }
        }
        //this.courseDatabase.put(course.getId(), course);
        return new RedirectView("/course/view/" + "course.getId()", true);
    }

    private synchronized long getNextcourseId() {
        return this.TICKET_ID_SEQUENCE++;
    }

    @RequestMapping(value = "view/{courseId}", method = RequestMethod.GET)
    public String view(@PathVariable("courseId") long courseId,
            ModelMap model) {
        course course = this.courseDatabase.get(courseId);
        if (course == null) {
            return "redirect:/course/list";
        }
        model.addAttribute("courseId", Long.toString(courseId));
        model.addAttribute("course", course);
        return "view";
    }

    @RequestMapping(
            value = "/{courseId}/attachment/{attachment:.+}",
            method = RequestMethod.GET
    )
    public View download(@PathVariable("courseId") long courseId,
            @PathVariable("attachment") String name) {
        course course = this.courseDatabase.get(courseId);
        if (course != null) {
            /*Attachment attachment = course.getAttachment(name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }*/
        }
        return new RedirectView("/course/list", true);
    }

}