package com.stream.app.service.imp;

import ch.qos.logback.core.util.StringUtil;
import com.stream.app.entity.Video;
import com.stream.app.repositories.VideoRepository;
import com.stream.app.service.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImp implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Value("${files.video}")
    String DIR ;

    @Override
    public Video save(Video video, MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(DIR);

            Path paths = Paths.get(cleanFolder,cleanFileName);
            System.out.println(contentType);
            System.out.print(paths);

            Files.copy(inputStream,paths, StandardCopyOption.REPLACE_EXISTING);

            video.setContentType(contentType);
            video.setFilePath(paths.toString());

            return videoRepository.save(video);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostConstruct
    public void init(){
        File file = new File(DIR);

        if(!file.exists()){
            file.mkdir();
            System.out.println("New Folder Created !!");
        }
        else{
            System.out.print("Folder already created ! ");
        }
    }



    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }

}
