package com.facedetection.vision;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;

public class FaceDetection
{

	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		CascadeClassifier faceDetector = new CascadeClassifier(
				"files/lbpcascade_frontalface.xml");
		Mat mat = Imgcodecs.imread("files/people.jpg");
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(mat, faceDetections);
		for (Rect rect : faceDetections.toArray())
		{
			Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x
					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}
		ImageGUI.bufferedImageShow(mat, "faceDetection");
		JOptionPane.showMessageDialog(null,
				"Detected " + faceDetections.toArray().length + " faces");
	}
}
