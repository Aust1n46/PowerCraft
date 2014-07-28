package larsg310.mods.powercraft.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import larsg310.mods.powercraft.util.BlockCoord;
import net.minecraft.client.Minecraft;

public class Task
{
	public List<BlockCoord> blocks = new ArrayList<BlockCoord>();
	private TaskType type;
	
	public Task(TaskType type)
	{
		this.type = type;
	}
	
	public TaskType getTaskType()
	{
		return type;
	}
	
	public void buildTask()
	{
		String basePath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		String path = basePath.substring(0, basePath.length() - 1) + "tasks\\" + type.path;
		try
		{
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			blocks = (ArrayList<BlockCoord>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void save()
	{
		String basePath = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		String path = basePath.substring(0, basePath.length() - 1) + "tasks\\" + type.path;
		File file = new File(path);
		try
		{
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(blocks);
			out.close();
			fileOut.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
