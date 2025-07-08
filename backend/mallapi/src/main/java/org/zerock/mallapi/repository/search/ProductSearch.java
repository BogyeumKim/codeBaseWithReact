package org.zerock.mallapi.repository.search;

import org.springframework.data.domain.Page;

import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.ProductDTO;

public interface ProductSearch {

    Page<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
}
