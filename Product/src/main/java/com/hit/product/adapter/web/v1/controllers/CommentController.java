package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.CommentService;
import com.hit.product.domains.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(UrlConstant.Comment.DATA_COMMENT)
    public ResponseEntity<?> getComments() {
        return VsResponseUtil.ok(commentService.getComments());
    }

    @GetMapping(UrlConstant.Comment.DATA_COMMENT_ID)
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(commentService.getCommentById(id));
    }

    @PostMapping(UrlConstant.Comment.DATA_COMMENT_RATE_BY_USER)
    public ResponseEntity<?> createCommentLv1(@PathVariable("idUser") Long idUser,
                                              @PathVariable("idProductRate") Long idProductRate,
                                              @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.createComment(idUser, idProductRate, commentDto));
    }

    @PostMapping(UrlConstant.Comment.DATA_COMMENT_CHILD_RATE_BY_USER)
    public ResponseEntity<?> createCommentLvN(@PathVariable("idUser") Long idUser,
                                              @PathVariable("idProductRate") Long idProductRate,
                                              @PathVariable("idCommentParent") Long idCommentParent,
                                              @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.createCommentChild(idUser, idProductRate, idCommentParent, commentDto));
    }

    @PatchMapping(UrlConstant.Comment.DATA_COMMENT_ID)
    public ResponseEntity<?> updateCommentLvN(@PathVariable("id") Long idComment, @RequestBody CommentDto commentDto) {
        return VsResponseUtil.ok(commentService.updateComment(idComment, commentDto));
    }

    @DeleteMapping(UrlConstant.Comment.DATA_COMMENT_ID)
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(commentService.deleteComment(id));
    }

}
