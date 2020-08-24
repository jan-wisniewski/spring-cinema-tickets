package com.app.service;

import com.app.dto.CreateNewsDto;
import com.app.exception.AdminServiceException;
import com.app.exception.NewsServiceException;
import com.app.mappers.Mapper;
import com.app.model.News;
import com.app.repository.NewsRepository;
import com.app.validator.CreateNewsDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public List<News> findAllAndSortByDate() {
        return newsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(News::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public Integer deleteById(Integer id) {
        return (newsRepository.deleteById(id)) ? 1 : 0;
    }

    public Integer add(CreateNewsDto newsDto) {
        if (newsDto == null) {
            throw new NewsServiceException("News is null");
        }
        var validator = new CreateNewsDtoValidator();
        var errors = validator.validate(newsDto);
        if (!errors.isEmpty()) {
            String errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new AdminServiceException("Add news errors: " + errorsMessage);
        }
        News news = Mapper.fromNewsDtoToNews(newsDto);
        var addedNews = newsRepository
                .add(news)
                .orElseThrow();
        return addedNews.getId();
    }

    public News findById(Integer id) {
        return newsRepository.findById(id).orElseThrow();
    }

    public News editNews(News news) {
        return newsRepository.update(news).orElseThrow();
    }
}
