package com.zcxads.springbootinit.model.dto.post;

import lombok.Data;
import java.util.List;

@Data
public class PostSummary {
    private Long Id;
    private String Title;
    private String Content;
    private List<String> Tags;
}

