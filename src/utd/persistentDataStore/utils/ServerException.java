package utd.persistentDataStore.utils;

public class ServerException extends Exception
{
	public ServerException(String msg)
	{
		super(msg);
	}

	public ServerException(String msg, Throwable throwable)
	{
		super(msg, throwable);
	}

}
