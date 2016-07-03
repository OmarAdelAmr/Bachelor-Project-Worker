package fb.rt;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewHumanTaskGUIFB extends FBInstance
{

	// GUI Variables
	private JFrame jframe_new_human_task = new JFrame("New Human Task");
	private javax.swing.JButton jButton_close;
	private javax.swing.JButton jButton_create_task;
	private javax.swing.JComboBox<String> jComboBox_gestures;
	private javax.swing.JLabel jLabel_choose_gesture;
	private javax.swing.JLabel jLabel_create_new_task;
	private javax.swing.JLabel jLabel_name_warning;
	private javax.swing.JLabel jLabel_task_name;
	private javax.swing.JTextField jTextField_task_name;
	private javax.swing.JLabel jLabel_task_created;
	// End of GUI Variables

	// INPUT EVENTS
	public EventServer ie_init_new_human_task = new EventInput(this);
	// END OF INPUT EVENTS

	// OUTPUT EVENTS
	public EventOutput oe_close_new_human_task_window = new EventOutput();
	// END OF OUTPUT EVENTS

	public NewHumanTaskGUIFB()
	{
		super();
		// TODO
		initComponents();
	}

	/////////////////////////////// GUI Methods //////////////////////

	private void initComponents()
	{

		jLabel_create_new_task = new javax.swing.JLabel();
		jLabel_task_name = new javax.swing.JLabel();
		jTextField_task_name = new javax.swing.JTextField();
		jLabel_choose_gesture = new javax.swing.JLabel();
		jComboBox_gestures = new javax.swing.JComboBox<>();
		jLabel_name_warning = new javax.swing.JLabel();
		jButton_create_task = new javax.swing.JButton();
		jButton_close = new javax.swing.JButton();
		jLabel_task_created = new javax.swing.JLabel();

		jLabel_task_created.setForeground(Color.BLUE);
		jLabel_name_warning.setForeground(Color.RED);

		jLabel_create_new_task.setText("Create New Human Task:");

		jLabel_task_name.setText("Task Name:");

		jTextField_task_name.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e)
			{
				warn();
			}

			public void removeUpdate(DocumentEvent e)
			{
				warn();
			}

			public void insertUpdate(DocumentEvent e)
			{
				warn();
			}

			public void warn()
			{
				jLabel_task_created.setText("");
			}
		});

		jLabel_choose_gesture.setText("Choose Gesture:");

		jComboBox_gestures.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Right Hand,Back", "Right Hand,Front", "Left Hand,Back", "Left Hand,Front", "Tool",
						"Swipe Right", "Swipe Left", "Screentab", "Keytab" }));
		jComboBox_gestures.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jComboBox_gesturesActionPerformed(evt);
			}
		});

		jButton_create_task.setText("Create");
		jButton_create_task.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				try
				{
					jButton_create_taskActionPerformed(evt);
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton_close.setText("Close");
		jButton_close.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_closeActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jframe_new_human_task.getContentPane());
		jframe_new_human_task.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout
												.createSequentialGroup().addGroup(layout.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(layout
																.createSequentialGroup().addContainerGap().addComponent(
																		jLabel_create_new_task))
														.addGroup(layout
																.createSequentialGroup().addGap(43, 43,
																		43)
																.addGroup(layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel_task_name)
																				.addGap(18, 18, 18)
																				.addComponent(jTextField_task_name,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						148,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				layout.createSequentialGroup()
																						.addComponent(
																								jLabel_choose_gesture)
																						.addGap(18, 18, 18)
																						.addComponent(
																								jComboBox_gestures,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								161,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jLabel_name_warning))
														.addGroup(layout.createSequentialGroup().addGap(203, 203, 203)
																.addComponent(jButton_create_task)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jLabel_task_created)))
												.addGap(0, 145, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
														.addComponent(jButton_close)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addComponent(jLabel_create_new_task)
						.addGap(36, 36, 36)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel_task_name)
								.addComponent(jTextField_task_name, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel_name_warning))
						.addGap(35, 35, 35)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel_choose_gesture).addComponent(jComboBox_gestures,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(27, 27, 27)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton_create_task).addComponent(jLabel_task_created))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addComponent(jButton_close).addContainerGap()));

		jframe_new_human_task.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe_new_human_task.setVisible(true);
		jframe_new_human_task.pack();
	}

	private void jButton_create_taskActionPerformed(java.awt.event.ActionEvent evt)
			throws FileNotFoundException, UnsupportedEncodingException
	{
		String task_name = jTextField_task_name.getText();
		if (task_name.matches("\\s*"))
		{
			jLabel_name_warning.setText("Required Field");

		} else
		{
			jLabel_name_warning.setText("");
			String task_gesture = jComboBox_gestures.getSelectedItem().toString();
			save_task(task_name, task_gesture);
			jLabel_task_created.setText("Created Successfully");

		}
	}

	private void jButton_closeActionPerformed(java.awt.event.ActionEvent evt)
	{
		jframe_new_human_task.dispose();
		oe_close_new_human_task_window.serviceEvent(this);
	}

	private void jComboBox_gesturesActionPerformed(java.awt.event.ActionEvent evt)
	{
		jLabel_task_created.setText("");
	}

	///////////////////////// Helper Methods ///////////////////////////
	private String read_all_tasks()
	{
		String path = System.getProperty("user.home") + "/HMI_Worker/Tasks/Human_tasks.task";
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(path));
			String currentLine = "";
			String result = "";
			while ((currentLine = br.readLine()) != null)
			{
				result += currentLine + "~";
			}
			if (result.length() > 0 && result.charAt(result.length() - 1) == '~')
			{
				result = result.substring(0, result.length() - 1);
			}
			br.close();
			return result;
		} catch (IOException e)
		{
			return "";
		}

	}

	private void save_task(String new_task_name, String new_task_gesture)
			throws FileNotFoundException, UnsupportedEncodingException
	{
		String old_data = read_all_tasks();
		String[] old_data_arr = old_data.split("~");

		String user_home_directory = System.getProperty("user.home");
		File file = new File(user_home_directory + "/HMI_Worker/Tasks/Human_tasks.task");
		file.getParentFile().mkdirs();

		PrintWriter writer = new PrintWriter(file, "UTF-8");

		for (int i = 0; i < old_data_arr.length; i++)
		{
			if (!old_data_arr[i].matches("\\s*"))
			{
				String[] task_details = old_data_arr[i].split(":");
				if (!task_details[0].equals(new_task_name))
				{
					writer.println(old_data_arr[i]);
				}
			}

		}

		writer.print("Human Task:" + new_task_name + ":" + new_task_gesture);
		writer.close();

	}

	public static void main(String[] args)
	{
		new NewHumanTaskGUIFB();
	}

}
