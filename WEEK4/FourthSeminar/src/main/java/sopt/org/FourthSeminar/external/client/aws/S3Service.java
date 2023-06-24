package sopt.org.FourthSeminar.external.client.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.model.BadRequestException;
import sopt.org.FourthSeminar.exception.model.NotFoundException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("%{cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("{cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public String uploadImage(MultipartFile multipartFile, String folder) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket + "/" + folder + "/image", fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return amazonS3.getUrl(bucket + "/" + folder + "/image", fileName).toString();
        } catch (IOException e) {
            throw new NotFoundException(Error.NOT_FOUND_SAVE_IMAGE_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
        }
    }

    // @ModelAttribute는 리스트에 대한 바인딩을 지원하지 않아, setter 메서드를 통해 데이터를 바인딩해줘야 한다.
    public List<String> uploadImages(List<MultipartFile> multipartFileList, String folder) {
        /*List<String> uploadImages = multipartFileList.stream()
                .forEach(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList());
        List<ObjectMetadata> objectMetadataList = multipartFileList.stream()
                        .map(o ->
        multipartFileList.stream().map().forE

        그냥 위에 uploadImage만 불러오면 될것을 ㅋㅋ..
*/
        return multipartFileList.stream()
                .map(multipartFile -> uploadImage(multipartFile, folder))
                .collect(Collectors.toList());
        // TODO 스트림 공부하자..

    }

    // 파일명 (중복 방지)
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // 파일 유효성 검사
    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new NotFoundException(Error.NOT_FOUND_SAVE_IMAGE_EXCEPTION, Error.NOT_FOUND_SAVE_IMAGE_EXCEPTION.getMessage());
        }

        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");

        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new BadRequestException(Error.INVALID_MULTIPART_EXTENSION_EXCEPTION, Error.INVALID_MULTIPART_EXTENSION_EXCEPTION.getMessage());
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void deleteFile(String imageUrl) {
        String imageKey = imageUrl.substring(56);
        amazonS3.deleteObject(bucket, imageKey);
    }


    // TODO 파일 용량 제한 걸어놓고 예외처리 해보기!
}
