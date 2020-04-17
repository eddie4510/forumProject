package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import ouhk.comps380f.dao.AttachmentRepository;
import ouhk.comps380f.dao.PostRepository;
import ouhk.comps380f.dao.ThreadRepository;
import ouhk.comps380f.dao.UserRepository;
import ouhk.comps380f.dao.UserRoleRepository;

import ouhk.comps380f.model.UserEntry;
import ouhk.comps380f.model.UserRoleEntry;
import ouhk.comps380f.model.AttachmentEntry;
import ouhk.comps380f.model.PostEntry;
import ouhk.comps380f.model.ThreadEntry;

@Controller
public class AdminController {
    
    @Resource
    private UserRepository userRepo;
    @Resource
    private UserRoleRepository roleRepo;
    @Resource
    private ThreadRepository threadRepo;
    @Resource
    private PostRepository postRepo;
    @Resource
    private AttachmentRepository attachmentRepo;

    @GetMapping("/adminpage")
    public ModelAndView viewUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewUser");

        List<String> usernames = new ArrayList<>();
        for (UserEntry users : userRepo.findAll()) {
            usernames.add(users.getUSERNAME());
        }
        mav.addObject("usernames", usernames);
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create() {
    return new ModelAndView("addUser", "addUser", new Form());
    }
    
    @PostMapping("/create")
    public ModelAndView create(Form form) throws IOException {
        ModelAndView mav = new ModelAndView();

        if (form.getUsername().isEmpty()||form.getPassword().isEmpty()) {
            mav.setViewName("addUser");
            mav.addObject("errorMessage", "Error: empty name/password");
            mav.addObject("addUser",new Form());
            return mav;
        }
        if(form.getRoles().length == 0){
            mav.setViewName("addUser");
            mav.addObject("errorMessage", "Error: empty roles");
            mav.addObject("addUser",new Form());
            return mav;
        }
        for (UserEntry aUser : userRepo.findAll()) {
            if (aUser.getUSERNAME().equals(form.getUsername())) {
                mav.setViewName("addUser");
                mav.addObject("errorMessage", "Error: name duplication");
                mav.addObject("addUser",new Form());
                return mav;
            }
        }
        UserEntry user = new UserEntry(form.getUsername(),"{noop}" + form.getPassword());
        userRepo.save(user);
        for (String role : form.getRoles()) {
            UserRoleEntry userrole = new UserRoleEntry(form.getUsername(),role);
            roleRepo.save(userrole);
        }
        mav.setViewName("redirect:/adminpage");
        return mav;
    }

    @GetMapping("/edit/{usernames}")
    public ModelAndView editUserInfo(@PathVariable(value = "usernames") String usernames) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("editUserInfo");
        for (UserRoleEntry userrole : roleRepo.findByUSERNAME(usernames)) {
            if(userrole.getROLE().equals("ROLE_ADMIN")){
                mav.addObject("role_adminchecked", "checked");
            }
        }

        UserEntry user = userRepo.findByUSERNAME(usernames);
        String username = user.getUSERNAME();
        String pw =  user.getPASSWORD();
        pw = pw.substring(6);
        mav.addObject("username", username);
        mav.addObject("pw", pw);
        
        mav.addObject("title", usernames);
        mav.addObject("editUserForm", new Form());
        return mav;
    }

    @PostMapping("/edit/{usernames}")
    public ModelAndView editUser(Form form) throws IOException {
        ModelAndView mav = new ModelAndView();

        if (form.getUsername().isEmpty()||form.getPassword().isEmpty()) {
            mav.setViewName("editUserInfo");
            mav.addObject("errorMessage", "Error: empty name/password");
            mav.addObject("editUserForm",new Form());
            return mav;
        }
        
        UserEntry user = new UserEntry(form.getUsername(),"{noop}" +form.getPassword());
        userRepo.save(user);

        List<String> userRole = new ArrayList<>();
        for (UserRoleEntry userrole : roleRepo.findByUSERNAME(form.getUsername())) {
            roleRepo.delete(userrole);
        }

        for (String role : form.getRoles()) {
            if(role.isEmpty()){
                mav.setViewName("editUserInfo");
                mav.addObject("errorMessage", "Error: empty roles");
                mav.addObject("editUserForm",new Form());
                return mav;
        }
            UserRoleEntry userrole = new UserRoleEntry(form.getUsername(),role);
            roleRepo.save(userrole);
        }

        mav.setViewName("redirect:/adminpage");
        return mav;
    }

    @GetMapping("/delete/{usernames}")
    public View deleteUser(@PathVariable(value = "usernames") String usernames) {
        int i;
        for (PostEntry userpost : postRepo.readEntriesByUSERNAME(usernames)) {    
            i = userpost.getThreadId();
            threadRepo.deleteById(i);
        }

        /*for (int userthread : postRepo.readEntriesThreadID(usernames)) {    
            threadRepo.deleteById(userthread);
        }*/

        for (PostEntry userpost : postRepo.readEntriesByUSERNAME(usernames)) {    
            postRepo.delete(userpost);
        }
        
        for (UserRoleEntry userrole : roleRepo.findByUSERNAME(usernames)) {
            roleRepo.delete(userrole);
        }
        UserEntry user = userRepo.findByUSERNAME(usernames);
        userRepo.delete(user);
        return new RedirectView("/adminpage", true);
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
   





}
