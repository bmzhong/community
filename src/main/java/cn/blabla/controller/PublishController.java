package cn.blabla.controller;

import cn.blabla.mapper.QuestionMapper;
import cn.blabla.mapper.UserMapper;
import cn.blabla.model.Question;
import cn.blabla.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
    @RequestMapping(value = "/abc",method = RequestMethod.GET)
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "tag") String tag,
                            HttpServletRequest httpServletRequest,
                            Model model
                            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        System.out.println("hello");
        User user=null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                     user= userMapper.findByToken(token);
                    if (user != null) {
                        System.out.println(user.getName());
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            model.addAttribute("success","添加成功");
            return "publish";

    }
}