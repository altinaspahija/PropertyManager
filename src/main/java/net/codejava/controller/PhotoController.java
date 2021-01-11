package net.codejava.controller;

import net.codejava.ResponseFile;
import net.codejava.ResponseMessage;
import net.codejava.model.Photo;
import net.codejava.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    //http://localhost:9090/uploadPhoto POST
    @PostMapping("uploadPhoto")
    public ResponseEntity<ResponseMessage> uploadPhoto(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            photoService.store(file);

            message = "Uploaded the photo successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the photo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //http://localhost:9090/downloadPhoto GET
    @GetMapping("downloadPhoto")
    public ResponseEntity<List<ResponseFile>> getPhoto() {
        List<ResponseFile> files = photoService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getPhoto_id())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    //http://localhost:9090/photos/photo_id GET
    @GetMapping("photos/{photo_id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String photo_id) {
        Photo photo = photoService.getFile(photo_id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .body(photo.getData());
    }
}
