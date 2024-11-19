package com.zcxads.springbootinit.service;

import com.zcxads.springbootinit.esdao.PostRepository;
import com.zcxads.springbootinit.esdao.impl.PostFind;
import com.zcxads.springbootinit.model.dto.post.PostEsDTO;
import com.zcxads.springbootinit.model.entity.User;
import com.zcxads.springbootinit.searchStrategy.impl.AllSearch;
import com.zcxads.springbootinit.searchStrategy.impl.AnimeSearch;
import com.zcxads.springbootinit.searchStrategy.impl.NovelSearch;
import com.zcxads.springbootinit.searchStrategy.impl.PictureSearch;
import com.zcxads.springbootinit.searchStrategy.opt.OptSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SearchServiceTest {

     @Autowired
    private PostRepository postRepository;

    @Resource
    private PostFind postService;

    @Resource
    private OptSearch optSearch;

    private String searchOutCome;

    private String searchType = "picture";
    private String searchText = "hello";
   @Test
    public void doFindAny() {


        switch (searchType){
            case "novel":
                optSearch = new OptSearch(new AnimeSearch());
                break;
            case "picture":
                optSearch = new OptSearch(new NovelSearch());
                break;
            case "animi":
                optSearch = new OptSearch(new PictureSearch());
                break;
            default:
                optSearch = new OptSearch(new AllSearch());

        }

        optSearch.doSearch(searchText);


   }

   @Test
    public void test(){
       List<PostEsDTO> postGet =  postService.findByTitle("探索");
       System.out.println(postGet);
   }
}
