package io.r3k.mydependabotexplore;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();// intentionally badly written code!
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();// intentionally badly written code!
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();// intentionally badly written code!
                return "You failed to upload " + name + " => " + e.getMessage() + exceptionAsString;
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}