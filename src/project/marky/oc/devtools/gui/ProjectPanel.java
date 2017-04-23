package project.marky.oc.devtools.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import project.marky.java.gui.BrowseFilePanel;
import project.marky.java.gui.IFileSelectionListener;
import project.marky.java.gui.fileChooser.FileChooserDirectory;
import project.marky.oc.devtools.ApplicationLogger;
import project.marky.oc.devtools.util.StyleConstants;


/**
 * Panel that holds the project settings and generate-button.
 */
@SuppressWarnings("serial")
// no serialization intended
public class ProjectPanel extends JPanel implements IFileSelectionListener
{
	private final FileChooserDirectory _dir = new FileChooserDirectory(new File("projects"));
	final BrowseFilePanel _source;

	public ProjectPanel()
	{
		super();

		this.setBorder(BorderFactory.createTitledBorder("Project"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		_source = new BrowseFilePanel(dir());
		_source.addFileSelectionListener(this);

		this.add(assembleButtonRow("Source", _source));
	}


	private Component assembleButtonRow(final String title, final BrowseFilePanel fileChooser)
	{
		final JLabel label = createLabel(title);

		final JPanel panel = subPanel();
		panel.add(label);
		panel.add(fileChooser);
		return panel;
	}


	private JLabel createLabel(final String title)
	{
		final JLabel label = new JLabel(title);
		label.setPreferredSize(StyleConstants.DIMENSION_PROJECT_LABEL);
		return label;
	}


	private JPanel subPanel()
	{
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		return panel;
	}


	private FileChooserDirectory dir()
	{
		return _dir;
	}


	// //////////////////////////////////////////////////////////////
	//
	// Public interface

	@Override
	public void onFileSelection(final ActionEvent e)
	{
		// do nothing at the moment
	}


	public void loadConfigFile(final File file)
	{
		ApplicationLogger.getLogger().info("Loading project configuration from file: " + file.getAbsolutePath());
		// TODO final IProjectConfiguration project = ProjectConfiguration.loadFromXml(file);
		// setTitle(project.getTitle());
		// setSource(project.getSource());
		// setOutput(project.getOutput());
		// setStylesheet(project.getStylesheet());
	}


	public void saveConfigFile(final File file)
	{
		ApplicationLogger.getLogger().info("Saving project configuration to file: " + file.getAbsolutePath());
		// TODO ProjectConfiguration.saveToXmlFile(this, file);
	}
}
