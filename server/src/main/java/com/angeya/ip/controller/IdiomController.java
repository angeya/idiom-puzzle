package com.angeya.ip.controller;

import com.angeya.ip.pojo.Idiom;
import com.angeya.ip.pojo.IdiomParam;
import com.angeya.ip.service.IdiomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc:
 * Author: Angeya
 * DateTime: 2022-02-20 17:46
 */
@RestController
public class IdiomController {
    
    private final Logger logger = LoggerFactory.getLogger(IdiomController.class);
    @Autowired
    private IdiomService idiomService;

    @PostMapping("getNextIdiom")
    public List<Idiom> getNextIdiom(HttpServletRequest request, @RequestBody IdiomParam idiomParam) {
        logger.info("ip:{}, param:{}", request.getRemoteAddr(), idiomParam.toString());
        return idiomService.getNextIdiom(idiomParam);
    }
}
