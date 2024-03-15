package com.kafedra.WebSite.controllers.news;


import com.kafedra.WebSite.response.news.NewsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/{id}")
    public NewsResponse getAllNews(@PathVariable("id") long id)
    {
        return null;
    }
}
