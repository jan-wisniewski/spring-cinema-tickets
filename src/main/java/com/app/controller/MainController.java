package com.app.controller;

import com.app.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final NewsService newsService;

    @GetMapping("/")
    public String getNews(Model model){
        model.addAttribute("newsAll",newsService.findAllAndSortByDate());
        return "index";
    }


}
