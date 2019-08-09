package cn.blabla.community.controller;

import cn.blabla.community.dto.PaginationDto;
import cn.blabla.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "6") Integer size
                        ){
        PaginationDto pagination = questionService.list(page, size);
        model.addAttribute("pagination",pagination);

        return "index";
    }

}
