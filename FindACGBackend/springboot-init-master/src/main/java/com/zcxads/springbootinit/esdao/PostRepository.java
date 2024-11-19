package com.zcxads.springbootinit.esdao;

import com.zcxads.springbootinit.model.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
}