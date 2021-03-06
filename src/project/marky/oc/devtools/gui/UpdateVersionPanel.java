package project.marky.oc.devtools.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel that holds version string and a button.
 */
@SuppressWarnings("serial") // no serialization intended
public class UpdateVersionPanel extends JPanel implements ActionListener, KeyListener
{
	final JTextField _versionField = new JTextField();
	final JButton _update = new JButton("Perform update!");

	Timer _timer;


	public UpdateVersionPanel()
	{
		super();

		_versionField.setText("-");
		_versionField.addActionListener(this);
		_versionField.addKeyListener(this);
		_versionField.setSize(100, 0);

		_update.addActionListener(this);

		this.setBorder(BorderFactory.createTitledBorder("DefCore.txt Version Update"));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(new JLabel("Version    "));
		this.add(_versionField);
		this.add(_update);

		checkVersion();
	}


	@Override
	public void actionPerformed(final ActionEvent action)
	{
		if (action.getSource() == _update)
		{
			updateVersion();
		}
		else if (action.getSource() == _versionField)
		{
			checkVersion();
		}
	}


	@Override
	public void keyPressed(final KeyEvent event)
	{
		// do nothing
	}


	@Override
	public void keyReleased(final KeyEvent event)
	{
		// do nothing
		checkVersion(150);
	}


	@Override
	public void keyTyped(final KeyEvent event)
	{
		// do nothing
	}


	private void updateVersion()
	{
		// does nothing at the moment
	}


	private void checkVersion()
	{
		final boolean isValid = isValidVersion(_versionField.getText());

		if (isValid)
		{
			_versionField.setForeground(Color.BLACK);
			_update.setEnabled(true);
		}
		else
		{
			_versionField.setForeground(Color.RED);
			_update.setEnabled(false);
		}

		cancelTimer();
	}


	private void cancelTimer()
	{
		if (_timer != null)
		{
			_timer.cancel();
			_timer = null;
		}
	}


	private void checkVersion(final long delay)
	{
		cancelTimer();

		_timer = new Timer();
		_timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				checkVersion();
			}
		}, delay);
	}


	static boolean isValidVersion(final String version)
	{
		if (version == null)
		{
			return false;
		}

		return version.matches("\\d+(,\\d+)*");
	}
}
