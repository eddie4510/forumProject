package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.AttachmentRepository;
import ouhk.comps380f.dao.PostRepository;
import ouhk.comps380f.dao.ThreadRepository;
import ouhk.comps380f.model.AttachmentEntry;
import ouhk.comps380f.model.PostEntry;
import ouhk.comps380f.model.ThreadEntry;

@Controller
@RequestMapping("/thread")
public class ThreadController {

    @Resource
    private ThreadRepository threadRepo;
    @Resource
    private PostRepository postRepo;
    @Resource
    private AttachmentRepository attachmentRepo;

    @GetMapping("/{type}")
    public ModelAndView view(@PathVariable(value = "type") String type) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewThread");
        mav.addObject("threads", threadRepo.readEntriesByTYPE(type));
        mav.addObject("type", type);
        mav.addObject("threadForm", new Form());
        return mav;
    }

    public static class Form {

        private String title;
        private String content;
        private List<MultipartFile> attachments;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    @PostMapping("/{type}")
    public ModelAndView create(@PathVariable(value = "type") String type, Form form, Principal principal) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewThread");
        String title = form.getTitle();
        String content = form.getContent();

        if (title.isEmpty() || content.isEmpty()) {
            mav.addObject("errorMessage", "Error: empty title/content");
            mav.addObject("threads", threadRepo.readEntriesByTYPE(type));
            mav.addObject("type", type);
            mav.addObject("threadForm", new Form());
            return mav;
        }

        ThreadEntry thread = new ThreadEntry(type, title);
        threadRepo.save(thread);
        int threadId = (int) threadRepo.readEntriesByTYPE(type).get(threadRepo.readEntriesByTYPE(type).size() - 1).getTHREAD_ID();
        PostEntry post = new PostEntry(principal.getName(), threadId, 0, content);

        
        for (MultipartFile filePart : form.getAttachments()) {
            AttachmentEntry attachment = new AttachmentEntry();
            attachment.setFILENAME(filePart.getOriginalFilename());
            attachment.setCONTENT_TYPE(filePart.getContentType());
            attachment.setCONTENT(filePart.getBytes());
            attachment.setPost(post);
            if (attachment.getFILENAME() != null && attachment.getFILENAME().length() > 0
                    && attachment.getCONTENT() != null && attachment.getCONTENT().length > 0) {
                //attachmentRepo.save(attachment);
                post.getAttachments().add(attachment);
            }
        }

        postRepo.save(post);

        mav.addObject("threads", threadRepo.readEntriesByTYPE(type));
        mav.addObject("type", type);
        mav.addObject("threadForm", new Form());

        return mav;
    }

    @GetMapping("/{type}/{threadId}")
    public ModelAndView posts(@PathVariable(value = "type") String type, @PathVariable(value = "threadId") int threadId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewPosts");
        mav.addObject("posts", postRepo.readEntriesByThreadId(threadId));
        String title = threadRepo.findById(threadId).get().getTITLE();
        mav.addObject("title", title);
        mav.addObject("postForm", new PostForm());
        return mav;
    }

    public static class PostForm {

        private String content;
        private List<MultipartFile> attachments;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

    @PostMapping("/{type}/{threadId}")
    public ModelAndView posts(@PathVariable(value = "type") String type,
            @PathVariable(value = "threadId") int threadId,
            PostForm form,
            Principal principal) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewPosts");
        String content = form.getContent();
        if (content.isEmpty()) {
            mav.addObject("errorMessage", "Error: empty content");
            mav.addObject("posts", postRepo.readEntriesByThreadId(threadId));
            String title = threadRepo.findById(threadId).get().getTITLE();
            mav.addObject("title", title);
            mav.addObject("postForm", new PostForm());
            return mav;
        }
        int nextPostSeq = postRepo.readEntriesByThreadId(threadId).size() + 1;
        PostEntry post = new PostEntry(principal.getName(), threadId, nextPostSeq, content);
        
        for (MultipartFile filePart : form.getAttachments()) {
            AttachmentEntry attachment = new AttachmentEntry();
            attachment.setFILENAME(filePart.getOriginalFilename());
            attachment.setCONTENT_TYPE(filePart.getContentType());
            attachment.setCONTENT(filePart.getBytes());
            attachment.setPost(post);
            if (attachment.getFILENAME() != null && attachment.getFILENAME().length() > 0
                    && attachment.getCONTENT() != null && attachment.getCONTENT().length > 0) {
                //attachmentRepo.save(attachment);
                post.getAttachments().add(attachment);
            }
        }

        postRepo.save(post);

        mav.addObject("posts", postRepo.readEntriesByThreadId(threadId));
        String title = threadRepo.findById(threadId).get().getTITLE();
        mav.addObject("title", title);
        mav.addObject("postForm", new PostForm());
        return mav;
    }
}
