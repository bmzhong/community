package cn.blabla.community.controller;

import cn.blabla.community.dto.QuestionDto;
import cn.blabla.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDto questionDto = questionService.getById(id);
        questionService.increaseView(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
