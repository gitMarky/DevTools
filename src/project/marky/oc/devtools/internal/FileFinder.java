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

public abstract class FileFinder
{
	public void run(final File inputFolderProject)
	{
		final ArrayList<File> files = parseFiles(inputFolderProject);
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

	protected abstract boolean doStuff(final File file);
	protected abstract boolean isFileCompatible(final File file);
	protected abstract void doReadLine(final String line);
	protected abstract void doFolderStuff(final File folder);

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
}
