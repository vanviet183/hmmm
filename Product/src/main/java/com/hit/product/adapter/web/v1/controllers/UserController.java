package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.PasswordResetTokenService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domains.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestApiV1
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private ApplicationEventPublisher publisher;

//    @GetMapping("")
//    public ResponseEntity<?> getUsers(@RequestParam Integer page) {
//        return ResponseEntity.ok().body(userService.getAccounts(page));
//    }

    @GetMapping(UrlConstant.User.DATA_USER)
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getListUser());
    }

    @GetMapping(UrlConstant.User.DATA_USER_ID)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping(UrlConstant.User.DATA_USER_VOUCHERS)
    public ResponseEntity<?> getVouchersByUserId(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(userService.getListVoucher(id));
    }

    @PostMapping(UrlConstant.User.DATA_USER_USE_VOUCHER)
    public ResponseEntity<?> useVoucher(@PathVariable("id") Long id, @PathVariable("idVoucher") Long idVoucher) {
        return VsResponseUtil.ok(userService.useVoucher(id, idVoucher));
    }

    @PostMapping(UrlConstant.User.DATA_USER_AVATAR)
    public ResponseEntity<?> uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile multipartFile) {
        return VsResponseUtil.ok(userService.setAvatar(id, multipartFile));
    }

    @PatchMapping(UrlConstant.User.DATA_USER_ID)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return VsResponseUtil.ok((userService.updateUser(id, userDto)));
    }

    @DeleteMapping(UrlConstant.User.DATA_USER_ID)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(userService.deleteUserById(id));
    }

}
