package project.marky.oc.devtools.internal;

import java.io.File;
import java.io.IOException;


public class Helper
{
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


	public static boolean isC4File(final File file)
	{
		if (file == null)
		{
			return false;
		}
		else
		{
			return file.getName().endsWith("c4g")
					|| file.getName().endsWith("c4d")
					|| file.getName().endsWith("c4s")
					|| file.getName().endsWith("c4f");
		}
	}
}
