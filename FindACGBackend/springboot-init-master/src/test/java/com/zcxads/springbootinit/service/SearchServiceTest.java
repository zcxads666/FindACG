package com.zcxads.springbootinit.service;

import com.zcxads.springbootinit.model.entity.User;
import com.zcxads.springbootinit.searchStrategy.impl.AllSearch;
import com.zcxads.springbootinit.searchStrategy.impl.AnimeSearch;
import com.zcxads.springbootinit.searchStrategy.impl.NovelSearch;
import com.zcxads.springbootinit.searchStrategy.impl.PictureSearch;
import com.zcxads.springbootinit.searchStrategy.opt.OptSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
public class SearchServiceTest {



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
}
