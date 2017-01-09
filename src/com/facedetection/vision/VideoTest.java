package com.facedetection.vision;

public class VideoTest
{

	public static void main(String[] args)
	{
		VideoProcessor proceesor = new VideoProcessor();
		proceesor.run(Constants.CASCADE_CLASSIFIER_FRONT_FACE, 0, 20);
	}
}
