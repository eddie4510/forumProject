package ouhk.comps380f.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.PollChoiceRepository;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.VoteRepository;
import ouhk.comps380f.model.PollChoiceEntry;
import ouhk.comps380f.model.PollEntry;
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
        
        //question
        PollEntry apoll = pollRepo.findById(POLL_ID).orElse(null);
        mav.addObject("question", apoll.getQUESTION());
        //poll choice
        List<String> pollChoiceList = new ArrayList<String>();
        pollChoiceList.add(pollChoiceRepo.findById(1).orElse(null).getChoice());
        pollChoiceList.add(pollChoiceRepo.findById(2).orElse(null).getChoice());
        pollChoiceList.add(pollChoiceRepo.findById(3).orElse(null).getChoice());
        pollChoiceList.add(pollChoiceRepo.findById(4).orElse(null).getChoice());
        mav.addObject("pollChoiceList", pollChoiceList);
      
         
        //Each Choice Result
        List<Long> resultList = new ArrayList<>();
        for(PollChoiceEntry apollchoice : pollChoiceRepo.findAll()) {
            int choiceId = apollchoice.getPollChoiceId();
            resultList.add(voteRepo.countByChoiceId(choiceId));
        }
        
        mav.addObject("result", resultList);
        
        
        
        mav.addObject("pollForm", new Form());
        
        
        
        mav.setViewName("index");
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
    public ModelAndView index(Form form, Principal principal) {
        ModelAndView mav = new ModelAndView();
        String username = principal.getName();
        String pollchoice = form.getPollChoice();
        int choiceid = 1;
        
        //get choice id
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