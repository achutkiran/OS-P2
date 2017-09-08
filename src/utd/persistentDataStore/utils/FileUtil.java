package utd.persistentDataStore.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides utility methods needed to read / write / delete / list file
 * containing binary data. 
 */
public class FileUtil
{
	static File directory = new File("data");
	

	public static void writeData(String name, byte data[]) throws IOException
	{
		File file = new File(directory, name);
		file.createNewFile();
		OutputStream ostream = new FileOutputStream(file);
		for(int idx = 0; idx < data.length; idx++) {
			ostream.write(data[idx]);
		}
		ostream.close();
	}

	public static byte[] readData(String name) throws ServerException, IOException
    {
		File file = new File(directory, name);
	    if(!file.exists()) {
	    	throw new ServerException("No File Found " + name);
	    }
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    InputStream istream = new FileInputStream(file);
	    int ch = 0;
	    while((ch=istream.read()) != -1) {
	    	baos.write(ch);
	    }
	    istream.close();
	    return baos.toByteArray();
    }

	public static boolean deleteData(String name) throws ServerException
    {
		File file = new File(directory, name);
	    if(!file.exists()) {
	    	throw new ServerException("No File Found " + name);
	    }
	    
	    return file.delete();
    }
	
	public static List<String> directory() throws ServerException
    {
		List<String> result = new ArrayList<String>();
		if(directory.exists()) {
			File files[] = directory.listFiles();
			for(File file: files) {
				result.add(file.getName());
			}
		}
		return result;
    }
}
