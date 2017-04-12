package project.marky.oc.devtools.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import project.marky.oc.devtools.ApplicationLogger;


public class TextFile
{
	public static String FILE_EXTENSION = ".txt";

	String _content;

	File _location;

	public TextFile()
	{
		_content = "";
	}

	public boolean isEmpty()
	{
		return _content.equals("");
	}

	public TextFile(final File location)
	{
		_content = "";
		_location = location;
	}

	public void write(final String content)
	{
		_content += content;
	}

	public void loadFromFile()
	{
		if (_location != null)
		{
			loadFromFile(_location);
		}
		else
		{
			throw new IllegalStateException("Cannot save without a location - specify in constructor or saveToFile(File)");
		}
	}

	public void saveToFile()
	{
		if (_location != null)
		{
			saveToFile(_location);
		}
		else
		{
			throw new IllegalStateException("Cannot save without a location - specify in constructor or saveToFile(File)");
		}
	}


	public boolean loadFromFile(final File inputFile)
	{
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new FileReader(inputFile));

			final Stream<String> lines = reader.lines();

			final Iterator<String> iter= lines.iterator();

			while (iter.hasNext())
			{
				final String line = iter.next();

				write(line + "\n");
			}

			return true;
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
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

		return false;
	}

	public boolean saveToFile(final File outputFile)
	{
		Helper.ensureCreateFile(outputFile);

		FileWriter writer = null;

		try
		{
			writer = new FileWriter(outputFile);
			writer.write(_content);
			return true;
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (writer != null)
				{
					writer.close();
				}
			}
			catch (final IOException e)
			{
				ApplicationLogger.getLogger().throwing("", "Cannot close writer", e);
			}
		}
		return false;
	}
}
