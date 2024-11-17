package com.zcxads.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcxads.springbootinit.mapper.PostMapper;
import com.zcxads.springbootinit.model.entity.Post;
import com.zcxads.springbootinit.searchStrategy.impl.AllSearch;
import com.zcxads.springbootinit.searchStrategy.impl.AnimeSearch;
import com.zcxads.springbootinit.searchStrategy.impl.NovelSearch;
import com.zcxads.springbootinit.searchStrategy.impl.PictureSearch;
import com.zcxads.springbootinit.searchStrategy.opt.OptSearch;
import com.zcxads.springbootinit.service.FindAnyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;



@Service
public class FindAnyServiceImpl extends ServiceImpl<PostMapper, Post> implements FindAnyService {

    @Resource
    private OptSearch optSearch;

    private String searchOutCome;

    @Resource
    private PostMapper postMapper;

    @Override
    public String doFindAny(String searchType, String searchText) {


        switch (searchType){

            case "anime":
                optSearch = new OptSearch(new AnimeSearch());
                break;
            case "novel":
                optSearch = new OptSearch(new NovelSearch());
                break;
            case "picture":
                optSearch = new OptSearch(new PictureSearch());
                break;
            default:
                optSearch = new OptSearch(new AllSearch());

        }

        List<Map<String, Object>> saveSearch =  optSearch.doSearch(searchText);
        for (Map<String, Object> map : saveSearch) {
            List<String> titles = (List<String>) map.get("titles");
            List<String> hrefs = (List<String>) map.get("hrefs");
            List<String> dynamicTag = (List<String>) map.get("tags");
            // 遍历titles和hrefs并创建新的Map
            for (int i = 0; i < titles.size(); i++) {
                Post post = new Post();
               System.out.println(titles.get(i));
               System.out.println(hrefs.get(i));
                post.setContent(hrefs.get(i));
                post.setTitle(titles.get(i));
                post.setUserId(1L);

                post.setTags("[\"OutSide\",\"" + dynamicTag.get(i) + "\"]");
                //boolean isSave = this.save(post);
               int isSave = postMapper.insert(post);
//                if (isSave) {
//                    searchOutCome = "success";
//                } else {
//                    searchOutCome = "fail";
//                }
                System.out.println(isSave);
            }
        }


        return searchOutCome;
    }


    @Override
    public boolean saveBatch(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Post entity) {
        return false;
    }

    @Override
    public Post getOne(Wrapper<Post> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Post> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Post> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public PostMapper getBaseMapper() {
        return null;
    }

    @Override
    public Class<Post> getEntityClass() {
        return null;
    }
}
