package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping({"", "/index"})
    public ModelAndView index(Principal principal) throws IOException{
        //return "redirect:/ticket/list";
        ModelAndView mav = new ModelAndView();
        int currentPollId = pollRepo.findAll().size();

        //question
        PollEntry apoll = pollRepo.findById(currentPollId).orElse(null);
        mav.addObject("question", apoll.getQUESTION());
        //poll choice

        List<String> pollChoiceList = new ArrayList<>();
        List<Long> resultList = new ArrayList<>();
        for (PollChoiceEntry apollChoice : pollChoiceRepo.readEntriesByPollId(currentPollId)) {
            pollChoiceList.add(apollChoice.getChoice());
            int choiceId = apollChoice.getPollChoiceId();
            resultList.add(voteRepo.countByChoiceId(choiceId));
        }

        mav.addObject("pollChoiceList", pollChoiceList);
        
        //Each Choice Result
        mav.addObject("voteList", voteRepo.findAll());
        mav.addObject("currentPollId", currentPollId);
        mav.addObject("result", resultList);
        
        Boolean isVoted = false;
        //check user voted or not
        if(principal != null) {
        for (VoteEntry avote : voteRepo.readEntriesByUsername(principal.getName())) {
            for (PollChoiceEntry achoice : pollChoiceRepo.readEntriesByPollId(currentPollId)) {
                if (avote.getChoiceId() == achoice.getPollChoiceId()) {
                    isVoted = true;
                }
            }
        }
        }
        //number of user vote 
        int countTotal = 0;
        for(long result: resultList) {
            countTotal = countTotal + (int)result;
        }
       
        if (isVoted) {
            mav.setViewName("index");
            mav.addObject("total", countTotal);
            mav.addObject("question", apoll.getQUESTION());
            mav.addObject("isVoted", isVoted);
            mav.addObject("errorMessage", "Vote Success! Current Poll Result:");
            mav.addObject("pollChoiceList", pollChoiceList);
            mav.addObject("result", resultList);
            mav.addObject("pollForm", new Form());
            return mav;
        }
        mav.addObject("total", countTotal);
        
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
        int currentPollId = pollRepo.findAll().size();


        //get choice id
        for (PollChoiceEntry apollChoice : pollChoiceRepo.findAll()) {
            if (apollChoice.getChoice().equals(pollchoice)) {
                choiceid = apollChoice.getPollChoiceId();
            }
        }

        //check user vote
        VoteEntry vote = new VoteEntry(username, choiceid);

        voteRepo.save(vote);
        
        
        mav.setViewName("redirect:/index");
        return mav;

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
