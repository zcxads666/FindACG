package com.zcxads.springbootinit.controller;

import com.zcxads.springbootinit.common.BaseResponse;
import com.zcxads.springbootinit.model.dto.find.FindAnyRequest;
import com.zcxads.springbootinit.service.impl.FindAnyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/FingAny")
@Slf4j
public class FindAnyController {

    @Resource
    private FindAnyServiceImpl findAnyService;

    @PostMapping("/doFindAny")
    public BaseResponse<String> doFindAny(@RequestBody FindAnyRequest request)
    {
        String searchType = request.getSearchType();
        String searchText = request.getSearchText();
        BaseResponse<String> response = new BaseResponse<>(200, findAnyService.doFindAny(searchType, searchText));


        response.setMessage("success");

        return response;
    }

}
