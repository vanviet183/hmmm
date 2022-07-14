package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.ImageRepository;
import com.hit.product.applications.repositories.NewsRepository;
import com.hit.product.applications.services.NewsService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.NewsDto;
import com.hit.product.domains.entities.Image;
import com.hit.product.domains.entities.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UploadFile uploadFile;

    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        checkNewsException(news);
        return news.get();
    }

//    @Override
//    @Transactional
//    public List<Image> uploadImgNews(Long id, List<MultipartFile> multipartFiles) {
//        Optional<News> news = newsRepository.findById(id);
//        checkNewsException(news);
//
//        List<Image> imageList = new ArrayList<>();
//        multipartFiles.forEach(multipartFile -> {
//            imageList.add(createImgNews(news.get(), new Image(), multipartFile));
//        });
//        return imageList;
//    }

    public Image createImgNews(Image image, MultipartFile multipartFile) {
        image.setImageUrl(uploadFile.getUrlFromFile(multipartFile));
        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public News createNews(NewsDto newsDto, List<MultipartFile> multipartFiles) {
        News news = modelMapper.map(newsDto, News.class);

        List<Image> imageList = new ArrayList<>();
        multipartFiles.forEach(multipartFile -> {
            imageList.add(createImgNews(new Image(), multipartFile));
        });
        news.setImages(imageList);
        return newsRepository.save(news);
    }

    @Override
    @Transactional
    public News updateNews(Long id, NewsDto newsDto, List<MultipartFile> multipartFiles) {
        Optional<News> news = newsRepository.findById(id);
        checkNewsException(news);

        modelMapper.map(newsDto, news.get());
        List<Image> imageList = new ArrayList<>();
        multipartFiles.forEach(multipartFile -> {
            imageList.add(createImgNews(new Image(), multipartFile));
        });
        news.get().setImages(imageList);
        return newsRepository.save(news.get());
    }

    @Override
    public TrueFalseResponse deleteNews(Long id) {
        Optional<News> news = newsRepository.findById(id);
        checkNewsException(news);
        newsRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkNewsException(Optional<News> news) {
        if(news.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
