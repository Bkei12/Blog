package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Resoonse Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // 요청 본문 값 매핑
    @PostMapping("/api/articles")
    // 클라이언트에서 전송된 JSON 형식의 데이터를 AddArticleRequest 객체로 변환하여 받음
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        // CREATED == 201: 요청이 성공적으로 수행되었고, 새로운 리로스 생성
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

}
