package com.twitter.listeners;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.recorder.VideoRecorder;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureVideoListener extends VideoListener {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        attachment(VideoRecorder.getLastRecording());
    }

    @Attachment(value = "video", type = "video/mp4")
    private byte[] attachment(File video) {
        try {
            return Files.readAllBytes(Paths.get(video.getAbsolutePath()));
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
