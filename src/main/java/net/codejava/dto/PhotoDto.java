package net.codejava.dto;

import lombok.Data;
import net.codejava.model.Photo;
import net.codejava.model.Property;

@Data
public class PhotoDto {
    private String photo_id;

    private String name;

    private String type;

    private byte[] data;

    public static Photo getPhoto(PhotoDto photoDto) {
        if (photoDto==null) return null;
        Photo photo = new Photo();
        photo.setPhoto_id(photoDto.getPhoto_id());
        photo.setName(photoDto.getName());
        photo.setType(photoDto.getType());

        return photo;
    }

    public static PhotoDto getPhotoDto(Photo photo){
        if (photo==null) return null;
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPhoto_id(photo.getPhoto_id());
        photoDto.setName(photo.getName());
        photoDto.setType(photo.getType());
        photoDto.setData(photo.getData());

        return photoDto;
    }

}
