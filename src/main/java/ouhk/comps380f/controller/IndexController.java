package ouhk.comps380f.controller;

import java.security.Principal;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.PollChoiceRepository;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.UserRepository;
import ouhk.comps380f.dao.VoteRepository;
import ouhk.comps380f.model.PollChoiceEntry;
import ouhk.comps380f.model.PollEntry;
import ouhk.comps380f.model.UserEntry;
import ouhk.comps380f.model.VoteEntry;

@Controller
public class IndexController {
    
    @Resource
    private PollRepository pollRepo;
    
    @Resource
    private PollChoiceRepository pollChoiceRepo;
    
    @Resource
    private VoteRepository voteRepo;
    
    private volatile int POLL_ID = 1;
     
    
    
    
   
    @GetMapping({"", "/index"})
    public ModelAndView index() {
        //return "redirect:/ticket/list";
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        //question
        PollEntry apoll = pollRepo.findById(POLL_ID).orElse(null);
        mav.addObject("question", apoll.getQUESTION());
        //poll choice
        PollChoiceEntry apollchoiceone = pollChoiceRepo.findById(1).orElse(null);
        PollChoiceEntry apollchoicetwo = pollChoiceRepo.findById(2).orElse(null);
        PollChoiceEntry apollchoicethree = pollChoiceRepo.findById(3).orElse(null);
        PollChoiceEntry apollchoicefour = pollChoiceRepo.findById(4).orElse(null);
        mav.addObject("choiceone", apollchoiceone.getChoice());
        mav.addObject("choicetwo", apollchoicetwo.getChoice());
        mav.addObject("choicethree", apollchoicethree.getChoice());
        mav.addObject("choicefour", apollchoicefour.getChoice());
        
      
        mav.addObject("pollForm", new Form());
        return mav;
        //return "index";
    }
   
     public static class Form {

        private String pollChoice;

        public String getPollChoice() {
            return pollChoice;
        }

        public void setPollChoice(String pollChoice) {
            this.pollChoice = pollChoice;
        }


    }
    
    @PostMapping({"", "/index"})
    public ModelAndView poll(Form form, Principal principal) {
        ModelAndView mav = new ModelAndView();
        String username = principal.getName();
        String pollchoice = form.getPollChoice();
        int choiceid = 1;
        
        for(PollChoiceEntry apollChoice: pollChoiceRepo.findAll()) {
        if(apollChoice.getChoice().equals(pollchoice)) {
            choiceid = apollChoice.getPollChoiceId();
        }
        
        }
        VoteEntry vote = new VoteEntry(username,  choiceid);
        
        voteRepo.save(vote);
        mav.setViewName("redirect:/index");
        return mav;
        
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
