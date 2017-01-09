package com.facedetection.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;

public class PedestrianDetector {

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	VideoCapture cap = new VideoCapture(0);
	Mat imageMatrix = new Mat();
	if (!cap.isOpened())
	    Message.displayErrorMessage("Web cam not open");
	else

	    for (int i = 0; i < 20; i++) {
		cap.read(imageMatrix);
	    }
	HOGDescriptor pedesDetector = new HOGDescriptor();
	pedesDetector.load(Constants.CASCADE_CLASSIFIER_PEDESTRIANS);
	MatOfRect pedesDetection = new MatOfRect();
	// pedesDetector.
	for (Rect drawPedes : pedesDetection.toArray())
	    Imgproc.rectangle(imageMatrix, new Point(drawPedes.x, drawPedes.y),
		    new Point(drawPedes.x + drawPedes.width, drawPedes.y
			    + drawPedes.height), new Scalar(0, 255, 0));
	ImageGUI.bufferedImageShow(imageMatrix, "Hello");
    }
}
