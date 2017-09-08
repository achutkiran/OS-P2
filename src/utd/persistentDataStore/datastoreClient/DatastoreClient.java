package utd.persistentDataStore.datastoreClient;

import java.util.List;

public interface DatastoreClient
{

	void write(String name, byte data[]) throws ClientException, ConnectionException;

	byte[] read(String name) throws ClientException, ConnectionException;

	void delete(String name) throws ClientException, ConnectionException;

	List<String> directory() throws ClientException, ConnectionException;

}