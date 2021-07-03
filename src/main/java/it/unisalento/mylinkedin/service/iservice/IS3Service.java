package it.unisalento.mylinkedin.service.iservice;

public interface IS3Service {
    void downloadFile(String keyName);
    boolean uploadFile(String keyName, String uploadFilePath);
}
