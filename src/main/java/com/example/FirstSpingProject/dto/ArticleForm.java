package com.example.FirstSpingProject.dto;

import com.example.FirstSpingProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor//롬북
@ToString

public class ArticleForm {

    private String title;
    private String content;

    //dto의 필드명과 동일한 이름으로 new.mustache 파일의 name tag로 작성해야한다.
    //<textarea class="form-control" rows="3" name="content"></textarea> 이런식으로

    public Article toEntity() {
        return new Article(null,title,content);
        //DTO인 폼에서 엔티티 메소드로 변환해주는 메소드
    }
}