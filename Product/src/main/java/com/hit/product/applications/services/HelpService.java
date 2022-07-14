package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.HelpDto;
import com.hit.product.domains.entities.Help;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface HelpService {

    List<Help> getHelps();

    Help getHelpById(Long id);

    Help createHelp(Long idUser, HelpDto helpDto);

    Help updateHelp(Long id, HelpDto helpDto);

    TrueFalseResponse deleteHelp(Long id);
}
