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
import ouhk.comps380f.dao.PollCommentRepository;
import ouhk.comps380f.model.Polls;
import ouhk.comps380f.dao.PollsRepository;
import ouhk.comps380f.model.PollComment;
import java.security.Principal;
import ouhk.comps380f.dao.PollResponsRepository;
import ouhk.comps380f.model.PollRespons;

@Controller
@RequestMapping("poll")
public class PollsController {

    @Autowired
    PollsRepository PollsRepo;

    @Autowired
    PollCommentRepository comRepo;

    @Autowired
    PollResponsRepository resRepo;

    public static class voteForm {

        private String option;
        private String username;
        private String pollId;

        public void setOption(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setPollId(String pollId) {
            this.pollId = pollId;
        }

        public String getPollId() {
            return pollId;
        }
    }

    public static class CommentForm {

        private String pollId;
        private String user;
        private String content;

        public void setPollId(String id) {
            pollId = id;
        }

        public String getPollId() {
            return pollId;
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

    @RequestMapping(value = "view/{poll}", method = RequestMethod.GET)
    public ModelAndView viewPoll(@PathVariable("poll") String poll, Principal principal) {
        ModelAndView MAV = new ModelAndView();
        MAV.setViewName("viewPolls");
        MAV.addObject("user", principal.getName());
        MAV.addObject("Poll", PollsRepo.findOne(Integer.parseInt(poll)));
        MAV.addObject("Vote", new voteForm());
        MAV.addObject("comments", comRepo.readPollCommentByPollOrderById(PollsRepo.findOne(Integer.parseInt(poll))));
        MAV.addObject("commentForm", new CommentForm());
        Polls temp = PollsRepo.findOne(Integer.parseInt(poll));
        if (temp != null) {
            if (PollsRepo.findOne(Integer.parseInt(poll)).getOption1()!=null) {
                MAV.addObject("option1Count", resRepo.countPollResponsByResponseAndPoll("1",temp));
            }
            if (PollsRepo.findOne(Integer.parseInt(poll)).getOption2()!=null) {
                MAV.addObject("option2Count", resRepo.countPollResponsByResponseAndPoll("2",temp));
            }
            if (PollsRepo.findOne(Integer.parseInt(poll)).getOption3()!=null) {
                MAV.addObject("option3Count", resRepo.countPollResponsByResponseAndPoll("3",temp));
            }
            if (PollsRepo.findOne(Integer.parseInt(poll)).getOption4()!=null){
                MAV.addObject("option4Count", resRepo.countPollResponsByResponseAndPoll("4",temp));
            }
            if (!resRepo.readPollResponsByUsernameAndPollOrderById(principal.getName(), PollsRepo.findOne(Integer.parseInt(poll))).isEmpty()) {
                MAV.addObject("userOption" + resRepo.readPollResponsByUsernameAndPollOrderById(principal.getName(), PollsRepo.findOne(Integer.parseInt(poll))).get(0).getResponse(), "checked");
            }
        }
        return MAV;
    }

    public static class Form {

        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public String getOption3() {
            return option3;
        }

        public void setOption3(String option3) {
            this.option3 = option3;
        }

        public String getOption4() {
            return option4;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("addPolls", "Poll", new Form());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View create(Form form) throws IOException {
        Polls poll = new Polls(form.getQuestion());
        int i = 0;
        if (!form.getOption1().trim().isEmpty()) {
            poll.setOption1(form.getOption1());
            i++;
        }
        if (!form.getOption2().trim().isEmpty()) {
            poll.setOption2(form.getOption2());
            i++;
        }
        if (!form.getOption3().trim().isEmpty()) {
            poll.setOption3(form.getOption3());
            i++;
        }
        if (!form.getOption4().trim().isEmpty()) {
            poll.setOption4(form.getOption4());
            i++;
        }
        if (i == 0) {
            return new RedirectView("/poll/create", true);
        } else {
            PollsRepo.save(poll);
            return new RedirectView("/", true);
        }
    }

    @RequestMapping(value = "delete/{poll}", method = RequestMethod.GET)
    public View deletePoll(@PathVariable("poll") String poll) {
        PollsRepo.delete(PollsRepo.findOne(Integer.parseInt(poll)));
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "view/comment/{poll}", method = RequestMethod.POST)
    public View createComment(CommentForm form) {
        if (form.getContent().trim().isEmpty()) {
            return new RedirectView("/poll/view/" + form.getPollId(), true);
        }
        PollComment com = new PollComment(PollsRepo.findOne(Integer.parseInt(form.getPollId())), form.getUser(), form.getContent());
        comRepo.save(com);
        return new RedirectView("/poll/view/" + form.getPollId(), true);
    }

    @RequestMapping(value = "comment/delete/{poll}/{comment}", method = RequestMethod.GET)
    public View deleteComment(@PathVariable("poll") String poll, @PathVariable("comment") String comment) {
        PollComment i = comRepo.findOne(Integer.parseInt(comment));
        i.setPoll(null);
        comRepo.save(i);
        comRepo.delete(Integer.parseInt(comment));
        return new RedirectView("/poll/view/" + poll, true);
    }

    @RequestMapping(value = "view/vote", method = RequestMethod.POST)
    public View Vote(voteForm form) {
        if (form.getOption()==null) {
            return new RedirectView("/poll/view/" + form.getPollId(), true);
        }
        PollRespons pollRes = new PollRespons(PollsRepo.findOne(Integer.parseInt(form.getPollId())), form.getOption(), form.getUsername());
        if (!resRepo.readPollResponsByUsernameAndPollOrderById(form.getUsername(), PollsRepo.findOne(Integer.parseInt(form.getPollId()))).isEmpty()) {
            pollRes = resRepo.readPollResponsByUsernameAndPollOrderById(form.getUsername(), PollsRepo.findOne(Integer.parseInt(form.getPollId()))).get(0);
            pollRes.setResponse(form.getOption());
        }
        resRepo.save(pollRes);
        return new RedirectView("/poll/view/" + form.getPollId(), true);
    }
}
