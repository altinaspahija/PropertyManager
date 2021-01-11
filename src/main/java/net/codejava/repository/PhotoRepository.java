package net.codejava.repository;

import net.codejava.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository <Photo, String> {
}
