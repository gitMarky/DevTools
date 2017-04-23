package project.marky.oc.devtools.internal;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;

import project.marky.library.xml.BasicXmlFile;


/**
 * Data structure that handles the project configuration.
 */
public class ProjectConfiguration implements IProjectConfiguration
{
	private static final String XML_ROOT_ELEMENT = "docuProject";
	private static final String XML_ELEMENT_SOURCE = "source";


	private final File _workspace;


	/**
	 * Creates a new project configuration.
	 * 
	 * @param docuSource this file is the source folder for a directory.
	 */
	public ProjectConfiguration(final File docuSource)
	{
		if (docuSource == null)
		{
			throw new IllegalArgumentException("Docu source must not be null");
		}
		else if (!docuSource.isDirectory())
		{
			throw new IllegalArgumentException("The docu source must be a directory: " + docuSource.getAbsolutePath());
		}

		_workspace = docuSource;
	}


	public static IProjectConfiguration loadFromXml(final File xml)
	{
		final BasicXmlFile content = new BasicXmlFile(xml);

		if (!content.getRoot().getName().equals(XML_ROOT_ELEMENT))
		{
			throw new IllegalArgumentException("Invalid input file: " + xml.getAbsolutePath());
		}

		final String source = content.getRoot().getChildText(XML_ELEMENT_SOURCE);

		final File docuSource = new File(source);
		return new ProjectConfiguration(docuSource);
	}


	public static void saveToXmlFile(final IProjectConfiguration config, final File file)
	{
		// Setup content

		final Element source = new Element(XML_ELEMENT_SOURCE);
		source.setText(config.getSource().getAbsolutePath());

		// Create document

		final Element rootElement = new Element(XML_ROOT_ELEMENT);

		rootElement.addContent(source);

		final Document document = new Document(rootElement);

		final BasicXmlFile xml = new BasicXmlFile(document, file);
		xml.saveToFile();
	}


	@Override
	public File getSource()
	{
		return _workspace;
	}
}
