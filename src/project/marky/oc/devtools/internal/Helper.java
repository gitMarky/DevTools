package project.marky.oc.devtools.internal;

import java.io.File;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;


/**
 * Collection of static methods.
 */
public class Helper
{
	private Helper() throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException("Do not instantiate a utility class!");
	}


	/**
	 * Ensures that a file exists.
	 * 
	 * @param file the file address.
	 * @return {@code true} if the file could be created.
	 *         {@code false} if the file could not be created.
	 *         Possible {@link IOException} is caught.
	 */
	public static boolean ensureCreateFile(final File file)
	{
		if (!file.exists())
		{
			file.getParentFile().mkdirs();
		}

		if (!file.exists())
		{
			try
			{
				return file.createNewFile();
			}
			catch (final IOException e)
			{
				throw new IllegalArgumentException(e);
			}
		}

		return false;
	}


	/**
	 * Decides whether a file a C4 file.
	 * @param file the file.
	 * @return {@code true} if the file ends with one of the following strings:
	 *         {@code c4g, c4d, c4s, c4f}.
	 */
	public static boolean isC4File(final File file)
	{
		if (file == null)
		{
			return false;
		}
		else
		{
			return file.getName().toLowerCase().endsWith("c4g")
					|| file.getName().toLowerCase().endsWith("c4d")
					|| file.getName().toLowerCase().endsWith("c4s")
					|| file.getName().toLowerCase().endsWith("c4f");
		}
	}
}
