package project.marky.oc.devtools.internal;

import java.io.File;

/**
 * Interface for project configuration.
 */
public interface IProjectConfiguration
{
	/**
	 * Defines the source file for this configuration.
	 * 
	 * @return the source file address.
	 */
	File getSource();
}