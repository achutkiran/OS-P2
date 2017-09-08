package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;


public abstract class ServerCommand
{
	private static Logger logger = Logger.getLogger(ServerCommand.class);

	protected InputStream inputStream;
	protected OutputStream outputStream;
	
	abstract public void run() throws IOException, ServerException;
	
	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	public void setOutputStream(OutputStream outputStream)
	{
		this.outputStream = outputStream;
	}
	
	protected void sendOK() throws IOException
    {
		String msg = "OK\n";
		outputStream.write(msg.getBytes());
		outputStream.flush();
    }

	protected void sendError(String errMsg) 
    {
		StreamUtil.sendError(errMsg, outputStream);
    }
	
}
