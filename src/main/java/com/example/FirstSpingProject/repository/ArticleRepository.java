package com.example.FirstSpingProject.repository;

import com.example.FirstSpingProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
