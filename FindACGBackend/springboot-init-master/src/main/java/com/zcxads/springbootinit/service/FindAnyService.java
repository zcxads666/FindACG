package com.zcxads.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcxads.springbootinit.model.entity.Post;


public interface FindAnyService extends IService<Post> {

    String doFindAny(String text, String searchType);

}
