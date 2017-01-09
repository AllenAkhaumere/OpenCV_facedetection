package com.facedetection.vision;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * The Class ImageGUI.
 */
public class ImageGUI {

    /**
     * Buffered image show.
     *
     * @param mat
     *            the mat
     */
    GraphicsConfiguration gc;

    public static void bufferedImageShow(Mat mat) {
	ImageGUI.bufferedImageShow(mat, null);
    }

    /**
     * Buffered image show.
     *
     * @param mat
     *            the mat
     * @param title
     *            the title
     */
    public static void bufferedImageShow(Mat mat, String title) {
	BufferedImage bufferedImage = ImageGUI.mat2BufferedImage(mat);
	ImageIcon icon = new ImageIcon(bufferedImage);
	JFrame frame = new JFrame(title);
	frame.setLayout(new FlowLayout());
	frame.setSize(bufferedImage.getWidth() + 1200,
		bufferedImage.getHeight() + 600);
	frame.setResizable(true);
	JLabel lbl = new JLabel();
	lbl.setIcon(icon);
	frame.add(lbl);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Mat2 buffered image.
     *
     * @param mat
     *            the mat
     * @return the buffered image
     */
    public static BufferedImage mat2BufferedImage(Mat mat) {
	int type = BufferedImage.TYPE_BYTE_GRAY;
	if (mat.channels() > 1)
	    type = BufferedImage.TYPE_3BYTE_BGR;
	int bufferSize = mat.channels() * mat.cols() * mat.rows();
	byte[] data = new byte[bufferSize];
	mat.get(0, 0, data);
	BufferedImage bufferedImage = new BufferedImage(mat.cols(), mat.rows(),
		type);
	final byte[] targetPixels = ((DataBufferByte) bufferedImage.getRaster()
		.getDataBuffer()).getData();
	System.arraycopy(data, 0, targetPixels, 0, data.length);
	return bufferedImage;
    }
}