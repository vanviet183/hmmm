package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.BannerDto;
import com.hit.product.domains.entities.Banner;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BannerService {

    List<Banner> getBanners();

    Banner getBannerById(Long id);

    Banner createBanner(BannerDto bannerDto, MultipartFile multipartFile);

    Banner updateBanner(Long id, BannerDto bannerDto, MultipartFile multipartFile);

    TrueFalseResponse deleteBanner(Long id);

}
