package project.marky.oc.devtools.tools;

import java.io.File;

import project.marky.oc.devtools.ApplicationLogger;
import project.marky.oc.devtools.internal.FileFinder;
import project.marky.oc.devtools.internal.TextFile;

/**
 * Updates the version entry in a DefCore.txt
 */
public class VersionUpdater extends FileFinder
{
	TextFile output;
	String version;

	public void run(final File inputFolderProject, final String vrsn)
	{
		version = vrsn;
		run(inputFolderProject);
	}

	@Override
	protected boolean doStuff(final File file)
	{
		ApplicationLogger.getLogger().info(" * > Updating version " + file.getAbsolutePath());
		if (saveDefCore(file))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean saveDefCore(final File file)
	{
		output = new TextFile(new File(file.getParentFile(), "Defcore.txt"));

		readFile(file);

		output.saveToFile();

		return true;
	}

	@Override
	protected boolean isFileCompatible(final File file)
	{
		return file.getName().equalsIgnoreCase("Defcore.txt");
	}

	@Override
	protected void doReadLine(final String line)
	{
		if (output == null)
		{
			throw new IllegalArgumentException();
		}

		if (line.startsWith("Version="))
		{
			output.write("Version=" + version + "\n");
		}
		else
		{
			output.write(line + "\n");
		}
	}

	@Override
	protected void doFolderStuff(final File folder)
	{
		// do nothing in folders
	}
}
