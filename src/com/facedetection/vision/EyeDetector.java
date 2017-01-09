package com.facedetection.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class EyeDetector {

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	VideoCapture capture = new VideoCapture(0);
	if (!capture.isOpened())
	    Message.displayErrorMessage("Web Cam not open");
	else
	    Message.displayMessage("Web cam is open");
	Mat matFrame = new Mat();
	for (int i = 0; i < 20; i++) {
	    capture.read(matFrame);
	}
	CascadeClassifier eyedetector = new CascadeClassifier();
	eyedetector.load(Constants.CASCADE_CLASSIFIER_FRONT_FACE);
	if (eyedetector.empty())
	    Message.displayErrorMessage("NULL");
	else
	    Message.displayMessage("Go on");

	MatOfRect eyeDetection = new MatOfRect();
	eyedetector.detectMultiScale(matFrame, eyeDetection);
	for (Rect drawEye : eyeDetection.toArray())
	    Imgproc.rectangle(matFrame, new Point(drawEye.x, drawEye.y),
		    new Point(drawEye.x + drawEye.width, drawEye.y
			    + drawEye.height), new Scalar(0, 255, 0));
	ImageGUI.bufferedImageShow(matFrame, "The Eye");
	capture.release();
    }
}
