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
import ouhk.comps380f.dao.UsersRepository;
import ouhk.comps380f.model.Users;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UsersRepository usersRepo;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("Users", usersRepo.findAll());
        return "listUser";
    }

    public static class Form {

        private String username;
        private String password;
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("addUser", "Users", new Form());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public View create(Form form) throws IOException {
        Users user = new Users(form.getUsername(),
                form.getPassword(), form.getRoles()
        );
        usersRepo.save(user);
        return new RedirectView("/user/list", true);
    }

    @RequestMapping(value = "delete/{username}", method = RequestMethod.GET)
    public View deleteTicket(@PathVariable("username") String username) {
        usersRepo.delete(usersRepo.findOne(username));
        return new RedirectView("/user/list", true);
    }
}
