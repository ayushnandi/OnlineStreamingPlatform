package com.stream.app.repositories;

import com.stream.app.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,String> {

}
