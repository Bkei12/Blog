package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.BlobImplementer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 title, content 추가 후 Repo에 저장
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }



    // 특정 블로그 글 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                // 엔티티를 조회하고 id가 없으면 IllegalArgumentException 예외를 발생 시킴
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 모든 블로그 글 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 수정
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id ));

        article.update(request.getTitle(), request.getContent());

        return article;
    }


    // 블로그 글 삭제
    public void delete(long id) {
        blogRepository.deleteById(id);
    }
}
