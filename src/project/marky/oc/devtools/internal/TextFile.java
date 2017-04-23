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


/**
 * Representation of a text file.
 */
public class TextFile
{
	/**
	 * File extension: {@value #FILE_EXTENSION}.
	 */
	public static String FILE_EXTENSION = ".txt";

	String _content;

	File _location;

	/**
	 * Constructor. Creates an empty text file.
	 */
	public TextFile()
	{
		_content = "";
	}


	/**
	 * Decides if the file is empty.
	 * @return
	 */
	public boolean isEmpty()
	{
		return _content.equals("");
	}


	/**
	 * Creates an empty text file at a location.
	 * @param location the location.
	 */
	public TextFile(final File location)
	{
		_content = "";
		_location = location;
	}

	/**
	 * Adds content.
	 * 
	 * @param content the additional content.
	 *        You have to insert line breaks manually.
	 */
	public void write(final String content)
	{
		_content += content;
	}


	/**
	 * Loads the file from its location.
	 * 
	 * @throws IllegalStateException if the file does not have a location,
	 *         that is if the constructor {@link #TextFile()} was used.
	 */
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


	/**
	 * Saves the file content to its location.
	 * 
	 * @throws IllegalStateException if the file does not have a location,
	 *         that is if the constructor {@link #TextFile()} was used.
	 */
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


	/**
	 * Loads the file from a location.
	 * 
	 * @param inputFile the address of the input file
	 * @return {@code true} if reading the file was successful.
	 *         Possible {@link FileNotFoundException} is caught.
	 */
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
			ApplicationLogger.getLogger().throwing("", "Error while reading file", e);
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


	/**
	 * Saves the file content to a location.
	 * 
	 * @return {@code true} if saving the file was successful.
	 *         Possible {@link IOException} is caught.
	 */
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
			ApplicationLogger.getLogger().throwing("", "Error while saving file", e);
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
