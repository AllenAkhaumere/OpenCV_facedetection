package com.gui.view;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * 
 */
public class VideoAndImageForm extends JPanel {

    private static final long serialVersionUID = 1L;
    public BufferedImage bufferImage;
    public Mat imageFrame;

    /**
     * Create the application.
     */
    public VideoAndImageForm() {
    }

    /**
     * Initialize the contents of the frame.
     */
    public static void initialize(String title, Mat imageframe) {
	BufferedImage buff = viewImage(imageframe);
	ImageIcon icon = new ImageIcon(buff);
	JFrame frame = new JFrame(title);
	frame.setLayout(new FlowLayout());
	frame.setVisible(true);
	frame.setSize(buff.getWidth(), buff.getHeight());
	frame.setResizable(true);
	JLabel jlb = new JLabel();
	jlb.setIcon(icon);
	frame.add(jlb);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static BufferedImage viewImageMatrix(Mat frame) {
	return viewImage(frame);
    }

    public static BufferedImage viewImage(Mat image) {
	int imgType = BufferedImage.TYPE_BYTE_GRAY;
	if (image.channels() > 1) {
	    imgType = BufferedImage.TYPE_3BYTE_BGR;
	}
	int bufferSize = image.channels() * image.rows() * image.cols();
	byte[] data = new byte[bufferSize];
	image.get(0, 0, data);
	BufferedImage bufferImage = new BufferedImage(image.cols(),
		image.rows(), imgType);
	final byte[] pixels = ((DataBufferByte) bufferImage.getRaster()
		.getDataBuffer()).getData();
	System.arraycopy(data, 0, pixels, 0, data.length);
	return bufferImage;
    }

}
