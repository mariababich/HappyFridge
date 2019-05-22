package ua.iasa.happyfridge.controllers;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.iasa.happyfridge.entities.File;
import ua.iasa.happyfridge.repositories.FileRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;

import static java.util.Objects.requireNonNull;
import static org.h2.store.fs.FileUtils.getName;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequiredArgsConstructor
public class ImagesController {

    private final FileRepository fileRepository;

    @Transactional(readOnly = true)
    @GetMapping(path = "images/{imageFileId}")
    public void getImage(@PathVariable Long imageFileId, HttpServletResponse httpServletResponse) throws IOException {
        File image = fileRepository.findById(imageFileId)
                .orElseThrow(() -> new IllegalArgumentException("There is no file with id:" + imageFileId));


        String cacheControl = String.format("max-age=%s, no-transform, public", "86400");
        String contentDisposition = String.format("inline; filename=%s", image.getFileName());
        String contentTypeValue = URLConnection.guessContentTypeFromName(image.getFileName());

        httpServletResponse.setContentType(contentTypeValue);
        httpServletResponse.setContentLengthLong(image.getLength());
        httpServletResponse.setHeader(CACHE_CONTROL, cacheControl);
        httpServletResponse.setHeader(CONTENT_DISPOSITION, contentDisposition);

        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
            IOUtils.copy(new ByteArrayInputStream(image.getFile()), outputStream);
            outputStream.flush();
        }
    }

    @PostMapping(value = "files/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<File> uploadImage(@RequestBody MultipartFile file) throws IOException {
        requireNonNull(file, "File is not provided");
        String originalFilename = requireNonNull(file.getOriginalFilename(), "Original file name is not provided");

        String fileName = getName(originalFilename);
        File fileToSave = File.builder()
                .file(file.getBytes())
                .fileName(fileName)
                .length(file.getSize())
                .build();

        File savedFile = fileRepository.save(fileToSave);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("images/" + savedFile.getId())
                .build()
                .toUri();
        return ResponseEntity.created(location)
                .body(savedFile);
    }
}
