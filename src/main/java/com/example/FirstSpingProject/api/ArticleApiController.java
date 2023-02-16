package com.example.FirstSpingProject.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.FirstSpingProject.dto.ArticleForm;
import com.example.FirstSpingProject.entity.Article;
import com.example.FirstSpingProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // restAPI전용 컨트롤러
public class ArticleApiController {

    @Autowired // DI
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article:{}", id, article.toString());

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나 id가 다른경우)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답.
            log.info("잘못된 요청!!!! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);// 아티클 엔티티에서 가져온것을 붙여준다.(해당 항목(타이틀 이라던가..)이 비어있을때.)
        Article updated = articleRepository.save(target);
        log.info("아주 잘됨 id: {}, article: {}", id, article.toString());
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청처리
        if (target != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 삭제
        
        // 데이터 반환
        return null;
    }

}
