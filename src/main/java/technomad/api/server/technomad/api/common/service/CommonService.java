package technomad.api.server.technomad.api.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import technomad.api.server.technomad.api.common.dto.entity.FileEntity;
import technomad.api.server.technomad.api.common.dto.response.LoginResponseDto;
import technomad.api.server.technomad.api.common.query.CommonQuery;
import technomad.api.server.technomad.api.common.repository.FileRepository;
import technomad.api.server.technomad.core.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class CommonService {
    @Value("${upload.dir}")
    private String UPLOAD_DIR;
    @Value("${upload.base_url}")
    private String BASE_URL;
    private final CommonQuery commonQuery;
    private final FileRepository fileRepository;

    public CommonService(CommonQuery commonQuery, FileRepository fileRepository) {
        this.commonQuery = commonQuery;
        this.fileRepository = fileRepository;
    }

    public LoginResponseDto getUserLoginData(String accountId){
        return commonQuery.findUserLoginData(accountId);
    }


    public FileEntity uploadFile(MultipartFile file) {
        try {
            // 현재 날짜와 시간을 가져옴
            String timestamp = CommonUtil.getNowDatetimeString();

            // 파일명과 확장자 분리
            String originalFilename = file.getOriginalFilename();
            String saveFilename = originalFilename.substring(0, originalFilename.lastIndexOf('.')) + "_" + timestamp;
            String fileExtension = StringUtils.getFilenameExtension(originalFilename);

            // 파일을 저장할 디렉토리 생성
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일 저장
            String fileName = saveFilename + "." + fileExtension;
            String filePath = UPLOAD_DIR + File.separator + fileName;
            File destFile = new File(filePath);
            FileCopyUtils.copy(file.getBytes(), destFile);

            // DB에 파일 데이터 정보 저장
            return registerFile(originalFilename, filePath, saveFilename, fileExtension);
        } catch (IOException e) {
            return null;
        }
    }

    public Resource getFileResource(String filePath){
        // 파일 경로 생성
        return new ClassPathResource(filePath);
    }

    private FileEntity registerFile(String originName, String filePath, String saveName, String fileExtension){
        FileEntity fileEntity = FileEntity
                .builder()
                .baseUrl(BASE_URL)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .originName(originName)
                .filePath(filePath)
                .saveName(saveName)
                .fileExtension(fileExtension)
                .build();
        return fileRepository.save(fileEntity);
    }

    public FileEntity getFileEntityById(Long fileId){
        return fileRepository.findById(fileId).orElse(null);
    }
}
