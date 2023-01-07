package com.example.FirstSpingProject.controller;

import com.example.FirstSpingProject.dto.ArticleForm;
import com.example.FirstSpingProject.entity.Article;
import com.example.FirstSpingProject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j//로깅을 위한 골뱅이(어노테이션 문법)
public class ArticleController {

    @Autowired//스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결.
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString()); >>로깅으로 대체!

        //1.dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(form.toString());
        //System.out.println(article.toString());

        //2. repository 에게 entity를 db안에 저장하게 함.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(article.toString());

        return "";
    }
}
