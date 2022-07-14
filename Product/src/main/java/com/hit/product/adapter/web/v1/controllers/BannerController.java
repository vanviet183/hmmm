package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.BannerService;
import com.hit.product.domains.dtos.BannerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestApiV1
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping(UrlConstant.Banner.DATA_BANNER)
    public ResponseEntity<?> getBanners() {
        return VsResponseUtil.ok(bannerService.getBanners());
    }

    @GetMapping(UrlConstant.Banner.DATA_BANNER_ID)
    public ResponseEntity<?> getBanner(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(bannerService.getBannerById(id));
    }

    @PostMapping(UrlConstant.Banner.DATA_BANNER_CREATE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createBanner(@RequestBody BannerDto bannerDto, @RequestParam("imgBanner") MultipartFile multipartFile) {
        return VsResponseUtil.ok(bannerService.createBanner(bannerDto, multipartFile));
    }

    @PatchMapping(UrlConstant.Banner.DATA_BANNER_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateBanner(@PathVariable("id") Long id, @RequestBody BannerDto bannerDto, @RequestParam("imgBanner") MultipartFile multipartFile) {
        return VsResponseUtil.ok(bannerService.updateBanner(id, bannerDto, multipartFile));
    }

    @DeleteMapping(UrlConstant.Banner.DATA_BANNER_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(bannerService.deleteBanner(id));
    }

}
