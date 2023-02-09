package com.example.FirstSpingProject.controller;

import com.example.FirstSpingProject.dto.ArticleForm;
import com.example.FirstSpingProject.entity.Article;
import com.example.FirstSpingProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller//컨트롤러 선언!!!
@Slf4j//로깅을 위한 골뱅이(어노테이션 문법)
public class ArticleController {

    @Autowired//스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결.
    private ArticleRepository articleRepository; // 객체 선언

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){//폼데이터를 파라매터(아티클폼)로 전달
        log.info(form.toString());
        //System.out.println(form.toString()); >>로깅으로 대체!

        //1.dto를 Entity로 변환
        Article article = form.toEntity();//Article이라는 entity 타입 (Entity/Article.java)에서 엔티티로 변환(dto/ArticleForm.java/toEntity)
        log.info(form.toString());
        //System.out.println(article.toString());

        //2. repository 에게 entity를 db안에 저장하게 함.
        Article saved = articleRepository.save(article);//인터페이스(Repository)생성. crud 기본정의 되어있는 sava 기능 사용
        log.info(saved.toString());
        //System.out.println(article.toString());

        return "redirect:/articles/" + saved.getId();
    }

    //데이터 조회 과정
    @GetMapping("/articles/{id}")//url 입력 {id값} 받아오기.
    public String show(@PathVariable Long id, Model model){//url요청을 파라매터로 받아올때 PathVariable 사용.
        log.info("id = "+id);

        //1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);

        //3. 보여줄 페이지를 설정
        return"articles/show";
    }

    //리스트로 정보 받기
    @GetMapping("/articles")
    public String index(Model model){

        //1. 모든 아티클을 가져온다.(repository)
        List<Article> articleEntityList = articleRepository.findAll();
            //리스트로 받아야 하는데 리턴 타입이 iterable이라서 리파지토리에서 어레이 리스트를 오버라이드 함.


        //2. 가져온 article 묶음을 뷰로 전달
        model.addAttribute("articlelist", articleEntityList);

        //3. view 페이지를 설정한다.
        return"articles/index";
    }

    //수정페이지
    @GetMapping("/articles/{id}/edit")// {~~}변수와 PathVariable 변수의 이름이 같아야함
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // model에 데이터 등록
        model.addAttribute("article",articleEntity);

        // view페이지 설정
        return"articles/edit";
    }
}
