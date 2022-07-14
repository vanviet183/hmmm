package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.HelpService;
import com.hit.product.domains.dtos.HelpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class HelpController {

    @Autowired
    HelpService helpService;

    @GetMapping(UrlConstant.Help.DATA_HELP)
    public ResponseEntity<?> getHelps() {
        return VsResponseUtil.ok(helpService.getHelps());
    }

    @GetMapping(UrlConstant.Help.DATA_HELP_ID)
    public ResponseEntity<?> getHelpById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(helpService.getHelpById(id));
    }

    @PostMapping(UrlConstant.Help.DATA_HELP_CREATE)
    public ResponseEntity<?> createHelp(@PathVariable("idUser") Long idUser, @RequestBody HelpDto helpDto) {
        return VsResponseUtil.ok(helpService.createHelp(idUser, helpDto));
    }

    @PatchMapping(UrlConstant.Help.DATA_HELP_ID)
    public ResponseEntity<?> updateHelp(@PathVariable("id") Long id, @RequestBody HelpDto helpDto) {
        return VsResponseUtil.ok(helpService.updateHelp(id, helpDto));
    }

    @DeleteMapping(UrlConstant.Help.DATA_HELP_ID)
    public ResponseEntity<?> deleteHelp(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(helpService.deleteHelp(id));
    }

}
