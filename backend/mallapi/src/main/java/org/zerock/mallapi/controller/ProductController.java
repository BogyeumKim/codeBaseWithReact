package org.zerock.mallapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.ProductDTO;
import org.zerock.mallapi.service.ProductService;
import org.zerock.mallapi.util.CustomFileUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;
    private final ProductService productService;

   /* @PostMapping("/")
    public Map<String,String> register(ProductDTO productDTO) {

        log.info("register : {}",productDTO);

        List<MultipartFile> files = productDTO.getFiles();

        List<String> uploadedFileNames = fileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadedFileNames);

        log.info(uploadedFileNames);

        return Map.of("RESULT","SUCCESS");
    }*/

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {
        return fileUtil.getFiles(fileName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO){

        return productService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String,Long> register(ProductDTO productDTO){

        List<MultipartFile> files = productDTO.getFiles();

        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        log.info(uploadFileNames);

        Long pno = productService.register(productDTO);

        return Map.of("result",pno);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable("pno") Long pno){
        return productService.get(pno);
    }

    @PutMapping("/{pno}")
    public Map<String,String> modify(@PathVariable("pno") Long pno, ProductDTO productDTO){

        productDTO.setPno(pno);

        // old product
        ProductDTO oldProductDTO = productService.get(pno);

        // file upload
        List<MultipartFile> files = productDTO.getFiles(); // New file
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);

        // Kepp files
        List<String> uploadedFileNames = productDTO.getUploadFileNames();

        // 새로운 파일이 있다면
        if(currentUploadFileNames != null && !currentUploadFileNames.isEmpty()) {
            uploadedFileNames.addAll(currentUploadFileNames);
        }

        productService.modify(productDTO);


        // 업로드된 파일에 없다면 삭제
        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        if(oldFileNames != null && !oldFileNames.isEmpty()){
            List<String> removeFiles = oldFileNames.stream().filter(fileName ->
                    !uploadedFileNames.contains(fileName)
            ).toList();

            fileUtil.deleteFiles(removeFiles);
        }

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{pno}")
    public Map<String,String> remove(@PathVariable("pno") Long pno){

        List<String> oldFileNames = productService.get(pno).getUploadFileNames();
        productService.remove(pno);

        // 파일도삭제
        fileUtil.deleteFiles(oldFileNames);
        return Map.of("RESULT", "SUCCESS");
    }

}
