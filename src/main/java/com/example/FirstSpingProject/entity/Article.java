package com.example.FirstSpingProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 엔티티 선언 (article 이라는 테이블)
@AllArgsConstructor//생성자 생성
@NoArgsConstructor//디폴트 생성자 생성
@ToString
public class Article {

    @Id // 주민번호 같은것 
    @GeneratedValue//자동 생성을 위한 어노테이션
    private Long id;

    @Column // 컬럼 단위
    private String title;

    @Column //db에서 컬럼 인식 위해
    private String content;

    //생성자 추가, tosting 메소드 추가
}
