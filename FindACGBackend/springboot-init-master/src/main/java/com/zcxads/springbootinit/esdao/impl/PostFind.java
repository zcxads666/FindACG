package com.zcxads.springbootinit.esdao.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zcxads.springbootinit.constant.CommonConstant;
import com.zcxads.springbootinit.esdao.PostEsDao;
import com.zcxads.springbootinit.model.dto.post.PostEsDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.SearchHit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostFind implements PostEsDao {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<PostEsDTO> findByTitle(String title) {
         BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                   .must(QueryBuilders.matchQuery("title", title));
           NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                   .withQuery(queryBuilder)
                   .withPageable(org.springframework.data.domain.PageRequest.of(0, 100))
                   .build();
           SearchHits<PostEsDTO> searchHits = elasticsearchOperations.search(searchQuery, PostEsDTO.class);
           return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public List<PostEsDTO> findByUserId(Long userId) {
        return null;
    }

    @Override
    public Page<PostEsDTO> searchSimilar(PostEsDTO entity, String[] fields, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<PostEsDTO> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PostEsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PostEsDTO> S save(S entity) {
        return null;
    }

    @Override
    public <S extends PostEsDTO> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PostEsDTO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PostEsDTO> findAll() {
        return null;
    }

    @Override
    public Iterable<PostEsDTO> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PostEsDTO entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PostEsDTO> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
