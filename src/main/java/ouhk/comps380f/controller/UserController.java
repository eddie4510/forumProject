package ouhk.comps380f.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.UserRepository;
import ouhk.comps380f.dao.UserRoleRepository;
import ouhk.comps380f.model.UserEntry;
import ouhk.comps380f.model.UserRoleEntry;

@Controller
public class UserController {

    @Resource
    private UserRepository userRepo;
    @Resource
    private UserRoleRepository roleRepo;

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "userForm", new Form());
    }

    public static class Form {

        private String username;
        private String password;

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

    }

    @PostMapping("/register")
    public ModelAndView register(Form form, ModelMap map) {
        ModelAndView mav = new ModelAndView();
        String username = form.getUsername();
        String password = form.getPassword();
        if (username.isEmpty()||password.isEmpty()) {
            mav.setViewName("register");
            mav.addObject("errorMessage", "Error: empty name/password");
            mav.addObject("userForm",new Form());
            return mav;
        }

        for (UserEntry aUser : userRepo.findAll()) {
            if (aUser.getUSERNAME().equals(username)) {
                mav.setViewName("register");
                mav.addObject("errorMessage", "Error: name duplication");
                mav.addObject("userForm",new Form());
                return mav;
            }
        }
        UserEntry user = new UserEntry(username, "{noop}" + password);
        UserRoleEntry role = new UserRoleEntry(username, "ROLE_USER");

        userRepo.save(user);
        roleRepo.save(role);
        mav.setViewName("redirect:/login");
        return mav;
    }
}
