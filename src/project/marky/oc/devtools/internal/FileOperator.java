package project.marky.oc.devtools.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import project.marky.oc.devtools.ApplicationLogger;

/**
 * Abstract base class for performing operations
 * on certain files.
 */
public abstract class FileOperator
{
	/**
	 * <p>
	 * Run the operator on files in a specific folder.
	 * </p><p>
	 * The method goes through the directories and sub-directories and does the following:
	 * <nl>
	 * <li>Call {@link #doFolderStuff(File)} on every directory,</li>
	 * <li>Call {@link #doStuff(File) on every file that was in the directory before step 1.</li>
	 * </nl>
	 * </p>
	 * 
	 * @param sourceFolder the source folder. All files in this
	 *                     directory and its sub-directories
	 *                     will be manipulated.
	 */
	public void run(final File sourceFolder)
	{
		final ArrayList<File> files = parseFiles(sourceFolder);
		doStuff(files);
	}


	private ArrayList<File> parseFiles(final File sourceFolder)
	{
		final ArrayList<File> scriptFiles = new ArrayList<File>();

		parseFolder(sourceFolder, scriptFiles);

		ApplicationLogger.getLogger().info("Found Files:");

		for (final File file : scriptFiles)
		{
			ApplicationLogger.getLogger().info(" * " + file.getAbsolutePath());
		}

		ApplicationLogger.getLogger().info("Found " + scriptFiles.size() + " files");

		return scriptFiles;
	}

	private void doStuff(final ArrayList<File> files)
	{
		ApplicationLogger.getLogger().info("Performing Actions on Files: ");

		for (final File file : files)
		{
			if (doStuff(file))
			{
				ApplicationLogger.getLogger().info(" * > Success!");
			}
			else
			{
				ApplicationLogger.getLogger().warning(" * > Error!");
			}
		}
	}


	private void parseFolder(final File sourceFolder, final ArrayList<File> scriptFiles)
	{
		if (sourceFolder.isFile())
		{
			if (isFileCompatible(sourceFolder))
			{
				scriptFiles.add(sourceFolder);
			}
		}
		else if (sourceFolder.isDirectory())
		{
			final File[] subFiles = sourceFolder.listFiles();

			for (final File file : subFiles)
			{
				parseFolder(file, scriptFiles);
			}

			doFolderStuff(sourceFolder);
		}
	}


	/**
	 * Convenience method. Reads a file, line by line, and calls {@link #doReadLine(String)}
	 * on each line.
	 * 
	 * @param file the file.
	 * @return {@code true} if all lines could be read. In case of an input error
	 *         the method returns {@code false}.
	 */
	protected boolean readFile(final File file)
	{
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new FileReader(file));

			final Stream<String> lines = reader.lines();

			final Iterator<String> iter= lines.iterator();

			while (iter.hasNext())
			{
				final String line = iter.next();

				doReadLine(line);
			}

			return true;
		}
		catch (final FileNotFoundException e)
		{
			ApplicationLogger.getLogger().throwing("", "File not found", e);
			return false;
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (final IOException e)
				{
					ApplicationLogger.getLogger().throwing("", "Cannot close reader", e);
				}
			}
		}
	}


	/**
	 * Filter method. This decides whether a file should be manipulated or not.
	 * 
	 * @param file the input file.
	 * @return in your implementation, return {@code true} if you want to manipulate
	 *         the file.
	 */
	protected abstract boolean isFileCompatible(final File file);


	/**
	 * Manipulation method. Use this if you want to manipulate a folder.
	 * 
	 * @param folder the folder.
	 */
	protected abstract void doFolderStuff(final File folder);

	/**
	 * Manipulation method. Use this if you want to manipulate a file.
	 * 
	 * @param file the file.
	 * @return {@code true} if the manipulation was successful.
	 */
	protected abstract boolean doStuff(final File file);


	/**
	 * Manipulation method, is called by {@link #readFile(File).
	 * 
	 * @param line the line that was read from a file.
	 */
	protected abstract void doReadLine(final String line);
}
