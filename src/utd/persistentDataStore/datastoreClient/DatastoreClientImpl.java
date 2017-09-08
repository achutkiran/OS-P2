package utd.persistentDataStore.datastoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DatastoreClientImpl implements DatastoreClient
{
	private static Logger logger = Logger.getLogger(DatastoreClientImpl.class);

	private InetAddress address;
	private int port;
	public DatastoreClientImpl(InetAddress address, int port)
	{
		this.address = address;
		this.port = port;
		
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#write(java.lang.String, byte[])
	 */
	@Override
    public void write(String name, byte data[]) throws ClientException, ConnectionException
	{
		try {
			logger.debug("Executing Write Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			StreamUtil.writeLine("write\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			StreamUtil.writeLine(String.valueOf(data.length), outputStream);
			StreamUtil.writeData(data, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#read(java.lang.String)
	 */
	@Override
    public byte[] read(String name) throws ClientException, ConnectionException
	{	
		byte[] data = null;
		try {
			logger.debug("Executing Read Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			StreamUtil.writeLine("read\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			String response = StreamUtil.readLine(inputStream);
			if(response.equalsIgnoreCase("ok")){
				int length = Integer.parseInt(StreamUtil.readLine(inputStream));
				data = StreamUtil.readData(length, inputStream);
			}
			else{
				throw new ClientException("file not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#delete(java.lang.String)
	 */
	@Override
    public void delete(String name) throws ClientException, ConnectionException
	{
		try {
			logger.debug("Executing Delete Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			StreamUtil.writeLine("delete\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			String response = StreamUtil.readLine(inputStream);
			if(!response.equalsIgnoreCase("ok")){
				throw new ClientException("File doesn't exist");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#directory()
	 */
	@Override
    public List<String> directory() throws ClientException, ConnectionException
	{	
		try {
			logger.debug("Executing Directory Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			StreamUtil.writeLine("directory\n", outputStream);
			String response = StreamUtil.readLine(inputStream);
			if(response.equalsIgnoreCase("ok")){
				List<String> directory = new ArrayList<String>();
				int length = Integer.parseInt(StreamUtil.readLine(inputStream));
				for(int i=0;i<length;i++){
					String name = StreamUtil.readLine(inputStream);
					directory.add(name);
				}
				return directory;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
