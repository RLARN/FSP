package com.example.FirstSpingProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor//>>생성자
@ToString
public class Article {

    @Id // 주민번호 같은것
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column //db에서 컬럼 인식 위해
    private String content;
}
