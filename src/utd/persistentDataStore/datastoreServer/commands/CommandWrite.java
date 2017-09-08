package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class CommandWrite extends ServerCommand {

	@Override
	public void run() throws IOException, ServerException {
		String name = StreamUtil.readLine(inputStream);
		int length = Integer.parseInt(StreamUtil.readLine(inputStream));
		byte[] data = StreamUtil.readData(length, inputStream);
		FileUtil.writeData(name, data);
		StreamUtil.writeLine("ok", outputStream);
	}

}
