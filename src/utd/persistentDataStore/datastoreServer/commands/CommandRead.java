package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class CommandRead extends ServerCommand{

	@Override
	public void run() throws IOException, ServerException {
		String name = StreamUtil.readLine(inputStream);
		byte[] data = FileUtil.readData(name);
		if(data==null){
			StreamUtil.sendError("File not found", outputStream);
		}
		else{
			int length = data.length;
			StreamUtil.writeLine("ok\n", outputStream);
			StreamUtil.writeLine(String.valueOf(length), outputStream);
			StreamUtil.writeData(data, outputStream);
		}
		
	}

}
