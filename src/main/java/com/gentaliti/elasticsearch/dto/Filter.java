package com.gentaliti.elasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filter
 *
 * @author Gent Aliti <alitigenti@gmail.com/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String key;
    private Object value;
}
