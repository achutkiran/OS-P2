package utd.persistentDataStore.datastoreClient;

/**
 * This exception is thrown when the client detects a problem with the 
 * messages received from the server i.e. a message format problem
 */
public class ClientException extends Exception
{
	public ClientException(String msg)
	{
		super(msg);
	}

	public ClientException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

}
