package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.CommentRepository;
import com.hit.product.applications.repositories.ProductRateRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.CommentService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.CommentDto;
import com.hit.product.domains.entities.Comment;
import com.hit.product.domains.entities.ProductRate;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRateRepository productRateRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        checkCommentException(comment);
        return comment.get();
    }

    @Override
    public Comment createComment(Long idUser, Long idProductRate, CommentDto commentDto) {
        return commentRepository.save(createCmt(idUser, idProductRate, new Comment(), commentDto));
    }

    @Override
    public Comment createCommentChild(Long idUser, Long idProductRate, Long idCmtParent, CommentDto commentDto) {
        Optional<Comment> commentParent = commentRepository.findById(idCmtParent);
        checkCommentException(commentParent);

        Comment comment = createCmt(idUser, idProductRate, new Comment(), commentDto);
        comment.setCommentParent(commentParent.get());

        return commentRepository.save(comment);
    }

    public Comment createCmt(Long idUser, Long idProductRate, Comment comment, CommentDto commentDto) {
        Optional<User> user = userRepository.findById(idUser);
        checkUserException(user);

        Optional<ProductRate> productRate = productRateRepository.findById(idProductRate);
        checkProductRateException(productRate);

        modelMapper.map(commentDto, comment);
        comment.setProductRate(productRate.get());
        comment.setUser(user.get());

        return comment;
    }

    @Override
    public Comment updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        checkCommentException(comment);

        modelMapper.map(commentDto, comment.get());

        return commentRepository.save(comment.get());
    }



    @Override
    public TrueFalseResponse deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        checkCommentException(comment);
        commentRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkCommentException(Optional<Comment> comment) {
        if(comment.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductRateException(Optional<ProductRate> productRate) {
        if(productRate.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
