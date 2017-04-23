package project.marky.oc.devtools.tools;

import java.io.File;

import project.marky.oc.devtools.ApplicationLogger;
import project.marky.oc.devtools.internal.FileOperator;
import project.marky.oc.devtools.internal.TextFile;

/**
 * Updates the version entry in a DefCore.txt
 */
public class VersionUpdater extends FileOperator
{
	TextFile _outputFile;
	String _version = null;


	/**
	 * Do not use this method, use {@link #run(File, String)} instead.
	 */
	@Override
	public void run(final File sourceFolder)
	{
		if (_version == null)
		{
			throw new IllegalArgumentException("Cannot update version without version information");
		}

		super.run(sourceFolder);
	}


	/**
	 * Updates the version of Defcore.txt files to a specific version.
	 * 
	 * @param sourceFolder
	 * @param vrsn
	 */
	public void run(final File sourceFolder, final String vrsn)
	{
		_version = vrsn;
		run(sourceFolder);
	}


	@Override
	protected boolean doStuff(final File file)
	{
		ApplicationLogger.getLogger().info(" * > Updating version " + file.getAbsolutePath());
		return saveDefCore(file);
	}


	private boolean saveDefCore(final File file)
	{
		_outputFile = new TextFile(new File(file.getParentFile(), "Defcore.txt"));

		readFile(file);

		_outputFile.saveToFile();

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
		if (_outputFile == null)
		{
			throw new IllegalArgumentException();
		}

		if (line.startsWith("Version="))
		{
			_outputFile.write("Version=" + _version + "\n");
		}
		else
		{
			_outputFile.write(line + "\n");
		}
	}


	@Override
	protected void doFolderStuff(final File folder)
	{
		// do nothing in folders
	}
}
