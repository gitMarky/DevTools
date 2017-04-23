package project.marky.oc.devtools.gui;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.marky.oc.devtools.util.StyleConstants;


/**
 * Panel that holds the project settings and generate-button.
 */
@SuppressWarnings("serial")
// no serialization intended
public class ProjectPanel extends JPanel
{
	final JTextField _titleField = new JTextField();

	public ProjectPanel()
	{
		super();

		this.setBorder(BorderFactory.createTitledBorder("Project"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(assembleTitleRow());
	}


	private Component assembleTitleRow()
	{
		final JPanel panel = subPanel();
		final JLabel label = createLabel("Title");
		panel.add(label);
		panel.add(_titleField);
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


	// //////////////////////////////////////////////////////////////
	//
	// Public interface

}
