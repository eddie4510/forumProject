
package ouhk.comps380f.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class PollController {
    
    @Resource
    private PollRepository pollRepo;
   
    @Resource
    private PollChoiceRepository pollChoiceRepo;
    
    @Resource
    private VoteRepository voteRepo;
    
    private Map<Long, PollEntry> pollDatabase = new Hashtable<>();
    private Map<Long, PollChoiceEntry> pollChoiceDatabase = new Hashtable<>();
    private Map<Long, VoteEntry> voteDatabase = new Hashtable<>();
    
    @GetMapping("/addPoll")
    public ModelAndView addPoll() {
        return new ModelAndView("addPoll", "addPollForm", new Form());
    }

    public static class Form {

        private String question;
        private String choiceOne;
        private String choiceTwo;
        private String choiceThree;
        private String choiceFour;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoiceOne() {
            return choiceOne;
        }

        public void setChoiceOne(String choiceOne) {
            this.choiceOne = choiceOne;
        }

        public String getChoiceTwo() {
            return choiceTwo;
        }

        public void setChoiceTwo(String choiceTwo) {
            this.choiceTwo = choiceTwo;
        }

        public String getChoiceThree() {
            return choiceThree;
        }

        public void setChoiceThree(String choiceThree) {
            this.choiceThree = choiceThree;
        }

        public String getChoiceFour() {
            return choiceFour;
        }

        public void setChoiceFour(String choiceFour) {
            this.choiceFour = choiceFour;
        }


        
    }
    
    @PostMapping("addPoll") 
    public ModelAndView addPoll(Form form) {
        ModelAndView mav = new ModelAndView();
        
        String question = form.getQuestion();
        String choiceOne = form.getChoiceOne();
        String choiceTwo = form.getChoiceTwo();
        String choiceThree = form.getChoiceThree();
        String choiceFour = form.getChoiceFour();
        
        List<String> choiceList = new ArrayList<>();
        choiceList.add(choiceOne);
        choiceList.add(choiceTwo);
        choiceList.add(choiceThree);
        choiceList.add(choiceFour);
        
        List<PollEntry> pollList = pollRepo.findAll();
        int currentPollId = pollList.size();
        currentPollId++;
        
       PollEntry poll = new PollEntry(question);
       pollRepo.save(poll);
       
       for(String achoice : choiceList) {
        PollChoiceEntry pollchoice = new PollChoiceEntry(achoice, currentPollId);
        pollChoiceRepo.save(pollchoice);
       }
 
        
       
        mav.setViewName("redirect:/index");
        return mav;
    }
    
    
    @GetMapping("/pollHistory")
    public ModelAndView pollHistory() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pollHistory");
        
        mav.addObject("pollQuestion", pollRepo.findAll());
        mav.addObject("pollChoice", pollChoiceRepo.findAll());
        mav.addObject("vote", voteRepo.findAll());
        
        List<Long> voteCountList = new ArrayList<>();
        for(PollChoiceEntry apollchoice : pollChoiceRepo.findAll()) {
            int choiceId = apollchoice.getPollChoiceId();
            voteCountList.add(voteRepo.countByChoiceId(choiceId));
        }
       
        
        
        mav.addObject("vote", voteCountList);
        
        return mav;
    }
}
