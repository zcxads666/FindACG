package com.zcxads.springbootinit.model.dto.find;

import lombok.Data;

@Data
public class FindAnyRequest {
    private String searchType;
    private String searchText;
}
