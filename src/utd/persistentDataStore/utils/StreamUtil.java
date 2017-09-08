package utd.persistentDataStore.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * This class provides services used to read & write data to 
 * streams in various formats needed to implement the 
 * project services.
 */
public class StreamUtil
{
	private static Logger logger = Logger.getLogger(StreamUtil.class);

	/**
	 * Reads and returns the string without newline. 
	 * ByteArrayOutputStream is a very handy method of collecting 
	 * bytes when the length is unknown.
	 */
	public static String readLine(InputStream inputStream) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int ch;
		while((ch = inputStream.read()) != '\n') {
			baos.write(ch);
		}
		
		String result = baos.toString();
		return result;
	}
	
	/**
	 * Reads and returns the next N bytes of data from the stream
	 * in a byte array.
	 */
	public static byte[] readData(int length, InputStream inputStream) throws IOException
    {
	    byte bytes[] = new byte[length];
	    for(int idx = 0; idx < length; idx++) {
	    	int ch = inputStream.read();
	    	bytes[idx] = (byte)ch;
	    }
	    return bytes;
    }

	public static void writeData(byte data[], OutputStream outputStream) throws IOException
	{
		outputStream.write(data);
		outputStream.flush();
	}

	public static void writeLine(String line, OutputStream outputStream) throws IOException
	{
		if(!line.endsWith("\n")) {
			line = line + "\n";
		}
		outputStream.write(line.getBytes());
		outputStream.flush();
	}

	/**
	 * Close the socket by closing its input stream;
	 */
	public static void closeSocket(InputStream inputStream) 
	{
		if(inputStream != null) {
			try {
	            inputStream.close();
            }
            catch (IOException ex) {
            	logger.error(ex);
            }
		}
	}
	
	public static void sendError(String errMsg, OutputStream outputStream) 
    {
		if(!errMsg.endsWith("\n")) {
			errMsg = errMsg + "\n";
		}
		
		try {
	        outputStream.write(errMsg.getBytes());
	        outputStream.flush();
        }
        catch (IOException ex) {
        	logger.error(ex.getMessage(), ex);
        }
    }

}
