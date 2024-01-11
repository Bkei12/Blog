package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.PrimitiveIterator;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // 블로그에 글을 추가할 때 데이터를 엔티티로 변환하는 DTO
    public Article toEntity() { // toEntity는 빌더 패턴을 사용해 DTO를 엔티티로 만들어 주는 메서드
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
