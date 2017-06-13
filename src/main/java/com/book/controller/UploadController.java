package com.book.controller;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by KAI on 4/27/17.
 */
@Controller
public class UploadController {

    @PostMapping("/handleFileUpload")
    @ResponseBody
    public ResponseEntity handleFileUpload(HttpServletRequest request, HttpServletResponse res) throws IOException, FileUploadException {
//        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();

        ServletFileUpload upload = new ServletFileUpload();

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            // Inform user about invalid request
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        FileItemIterator iter = upload.getItemIterator(request);

        while (iter.hasNext()) {
            FileItemStream item = iter.next();
            String name = item.getFieldName();
            InputStream stream = item.openStream();
            if (item.isFormField()) {
                System.out.println("Form field " + name + " with value "
                        + Streams.asString(stream) + " detected.");
            } else {
                System.out.println("File field " + name + " with file name "
                        + item.getName() + " detected.");


                Base64Utils.encode(IOUtils.toByteArray(stream));


                System.out.println("===Base64Utils.encode(IOUtils.toByteArray(stream))==="+Base64Utils.encodeToString(IOUtils.toByteArray(stream)));
                System.out.println("===IOUtils.toByteArray(stream)==="+IOUtils.toByteArray(stream));

                // Process the input stream
            }
        }

//        String contactDate = multipartHttpServletRequest.getParameter("searchContactDate");
//        String userId = multipartHttpServletRequest.getParameter("userId");
//        String dest = "/Users/KAI/Downloads/image-upload/";
//
//        fileMap.values().forEach(file -> {
//            try {
//                System.out.println("=== file.getName()==" + file.getName());
//                System.out.println("====file.getInputStream()===" + file.getInputStream());
//                System.out.println("==contact date==="+ contactDate + "====userId"+userId);
//                File fileDest = new File(dest);
//                Path path = Paths.get(dest + userId + file.getOriginalFilename());
//                byte[] imageByte = new byte[0];
//                imageByte = file.getBytes();
//                Files.write(path, imageByte);
//                System.out.println("=== done==" + path.toString());
//                System.out.println(Base64Utils.encodeToString(imageByte));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        return new ResponseEntity(HttpStatus.OK);
    }
}
