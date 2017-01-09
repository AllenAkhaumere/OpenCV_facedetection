package com.facedetection.vision;

import javax.swing.JOptionPane;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.gui.view.VideoAndImageForm;

/**
 * Febuary 16, 2015.
 * 
 * @author Akhaumere Allen
 *
 */
public class CaptureAndDetectFace {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public CaptureAndDetectFace() {

    }

    public static void main(String[] args) {
	// VideoProcessor vp = new VideoProcessor();
	System.loadLibrary("opencv_java300");
	// VideoCapture cap = new VideoCapture(0);
	// if (!cap.isOpened())
	// Message.displayErrorMessage("The webcam is not open");
	// else
	// Message.displayMessage("The WebCam is on");
	Mat matFrame = new Mat();
	/* Loop through matrix frame 20x until you get a clear frame */
	for (int i = 0; i <= 200; i++) {
	    // cap.read(matFrame); // First frame maybe empty frame
	    matFrame = Imgcodecs.imread("file/Test at night.png");// Load
	    // the
	    // image
	    // directly
	    Message.displayMessage("I'm looking for a clear frame..." + i);

	}

	MatOfRect faceDetection = new MatOfRect();
	VideoProcessor.loadCascade(Constants.CASCADE_CLASSIFIER_FRONT_FACE_ALT)
		.detectMultiScale(matFrame, faceDetection);
	for (Rect drawRect : faceDetection.toArray())
	    Imgproc.rectangle(matFrame, new Point(drawRect.x, drawRect.y),
		    new Point(drawRect.x + drawRect.width, drawRect.y
			    + drawRect.height), new Scalar(0, 255, 0));
	VideoAndImageForm.initialize("Scope v0.1", matFrame);

	if (faceDetection.toArray().length > 0) {
	    if (faceDetection.toArray().length > 1) {
		JOptionPane.showMessageDialog(null,
			faceDetection.toArray().length + " faces detected :-)");
	    } else {
		JOptionPane.showMessageDialog(null,

		" A baby's face detected :-)");
	    }
	} else {
	    JOptionPane.showMessageDialog(null, "No face detected",
		    "Face Detection Error", JOptionPane.ERROR_MESSAGE);
	}
	String filename = "faceDetect.png";
	System.out.println(String.format("Writing %s", filename));
	Imgcodecs.imwrite(filename, matFrame);
	// cap.release();
    }
}
