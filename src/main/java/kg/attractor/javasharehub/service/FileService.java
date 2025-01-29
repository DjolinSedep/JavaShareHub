package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UploadFileDto;
import kg.attractor.javasharehub.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface FileService {

    Page<FileDto> getAllPublicFiles(Pageable pageable);

    Page<FileDto> getAllPublicFilesByCategoryId(Long categoryId, Pageable pageable);

    String generatePrivateKey(Long fileId);

    ResponseEntity<?> downloadByPrivateKey(String privateKey, Long fileId);

    void upload(UploadFileDto fileDto, Principal principal);

    ResponseEntity<?> downloadFile(Long fileId);


    File getFileById(Long fileId);

    FileDto convertToFileDto(File file);
}
