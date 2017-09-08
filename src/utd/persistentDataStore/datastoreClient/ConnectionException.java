package utd.persistentDataStore.datastoreClient;

/**
 * This exception is thrown when the client implementation is unable to connect 
 * to the server e.g. in the event of an IOException. 
 */
public class ConnectionException extends Exception
{
	public ConnectionException(String msg)
	{
		super(msg);
	}

	public ConnectionException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

}
