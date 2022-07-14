package com.hit.product.adapter.web.v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
  @GetMapping("/")
  public String app() {
    return "App đã hoạt động";
  }

  @GetMapping("/author")
  public String getAuthor() {
    return "Product Hit - Hãy để chúng tôi cùng góp phần trên con đường của bạn :v";
  }

}
