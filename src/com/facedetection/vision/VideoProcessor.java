package com.facedetection.vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 * 
 * @author Akhaumere Allen
 *
 */
public class VideoProcessor
{

	public VideoCapture capture;
	public String cascadeClassifier;
	public Mat matFrame;
	public MatOfRect faceDetectoion;
	public CascadeClassifier faceDetector;

	public void run(String cascadeClassifierFile, int numOfCamera,
			int numOfFrame)
	{
		this.cascadeClassifier = cascadeClassifierFile;
		this.capture = new VideoCapture(numOfCamera);
		if (!capture.isOpened())
		{
			Message.displayErrorMessage("Unable to access the webcam");
		}
		else
			Message.displayMessage("The web cam is on");
		matFrame = new Mat();
		for (int i = 0; i < numOfFrame; i++)
		{
			capture.read(matFrame);
			Message.displayMessage("Frame " + i);
		}
		this.faceDetector = new CascadeClassifier(cascadeClassifierFile);
		this.faceDetectoion = new MatOfRect();
		faceDetector.detectMultiScale(matFrame, faceDetectoion);
		for (Rect drawFace : faceDetectoion.toArray())
			Imgproc.rectangle(matFrame, new Point(drawFace.x, drawFace.y),
					new Point(drawFace.x + drawFace.width, drawFace.y
							+ drawFace.height), new Scalar(0, 0, 255));
		ImageGUI.bufferedImageShow(matFrame, "Face Detection");
	}

	public boolean webCamIsOpen()
	{
		return capture.isOpened();
	}

	public void release()
	{
		capture.release();
	}

	public static CascadeClassifier loadCascade(String cascade)
	{
		CascadeClassifier cas = new CascadeClassifier(cascade);
		return cas;
	}

}
