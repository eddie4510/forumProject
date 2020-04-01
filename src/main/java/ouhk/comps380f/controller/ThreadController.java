
package ouhk.comps380f.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ouhk.comps380f.dao.ThreadRepository;

@Controller
@RequestMapping("/thread")
public class ThreadController {
    
    @Resource
    private ThreadRepository threadRepo;
    
    @GetMapping("/view/{type}")
    public String view(@PathVariable(value="type") String type, ModelMap model){
        model.addAttribute("threads",threadRepo.readEntriesByTYPE(type));
        model.addAttribute("type",type);
        return "viewThread";
    }
    
}
