package com.facedetection.vision;

/**
 * The Class Message.
 *
 * @author Akhaumere Allen
 */
public class Message {
    /**
     * Display a error log message
     * 
     * @param message
     * @return
     */
    public static String displayErrorMessage(String message) {
	if (message != null)
	    System.err.println("[ " + message + " ]");
	return null;
    }

    public static String displayMessage(String message) {
	if (message != null)
	    System.out.println(message);
	return null;
    }
}
