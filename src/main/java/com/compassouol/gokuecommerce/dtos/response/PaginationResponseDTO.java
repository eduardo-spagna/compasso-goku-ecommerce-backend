package com.compassouol.gokuecommerce.dtos.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaginationResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long total;
    private long perPage;
    private long page;
    private long lastPage;
    private List<Object> pagedData;
}
