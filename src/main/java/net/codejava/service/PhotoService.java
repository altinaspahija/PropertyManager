package net.codejava.service;

import net.codejava.model.Photo;
import net.codejava.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Photo photo = new Photo(fileName, file.getContentType(), file.getBytes());

        return photoRepository.save(photo);
    }

    public Photo getFile(String id) {
        return photoRepository.findById(id).get();
    }

    public Stream<Photo> getAllFiles() {
        return photoRepository.findAll().stream();
    }
}
