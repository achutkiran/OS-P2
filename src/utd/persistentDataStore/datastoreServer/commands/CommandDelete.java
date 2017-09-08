package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class CommandDelete extends ServerCommand {
	@Override
	public void run() throws IOException, ServerException {
		String name = StreamUtil.readLine(inputStream);
		if(!FileUtil.deleteData(name)){
			StreamUtil.sendError("File not found", outputStream);
		}
		else{
			StreamUtil.writeLine("ok", outputStream);
		}
	}
}
