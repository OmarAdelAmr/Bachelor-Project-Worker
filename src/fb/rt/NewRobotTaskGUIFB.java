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

import fb.datatype.ANY;
import fb.datatype.WSTRING;

public class NewRobotTaskGUIFB extends FBInstance
{
	// private static WorkerAgent new_robot_task_agent = new WorkerAgent();

	// CHECK VARS
	private boolean start_recorder = false;
	private String task_name_copy;
	private String selected_arm_copy;
	private long begin_recording_timing;
	private long end_recording_timing;
	// END OF CHECK VARS

	// GUI Variables declaration
	private JFrame newTaskFrame = new JFrame("New Robot Task");
	private javax.swing.JButton btn_close;
	private javax.swing.JButton btn_start_stop_recording;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JComboBox<String> select_arm;
	private javax.swing.JTextField task_name;
	// End of GUI variables declaration

	// INPUT EVENTS
	public EventServer ie_init_new_task = new EventInput(this);
	public EventServer ie_start_stop_recording = new EventInput(this);
	public EventServer ie_close_newTask_window = new EventInput(this);
	// END OF INPUT EVENTS

	// INPUT VARIABLES
	public WSTRING iv_new_task_name = new WSTRING(); // INPUT
	public WSTRING iv_selected_arm = new WSTRING(); // INPUT
	// END OF INPUT VARIABLES

	// OUTPUT VARIABLES
	public WSTRING ov_new_task_name = new WSTRING();
	// ov_new_task_name Format => Start~$taskName || Stop
	public WSTRING ov_selected_arm = new WSTRING();
	// END OF OUTPUT VARIABLES

	// OUTPUT EVENTS
	public EventOutput oe_start_stop_recording = new EventOutput();
	public EventOutput oe_close_newTask_window = new EventOutput();
	// END OF OUTPUT EVENTS

	public NewRobotTaskGUIFB()
	{
		super();
		// TODO
		// Thread one = new Thread()
		// {
		// public void run()
		// {
		// new_robot_task_agent.start();
		// }
		// };
		// one.start();

	}

	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_new_task".equals(s))
			return ie_init_new_task;
		if ("ie_start_stop_recording".equals(s))
			return ie_start_stop_recording;
		if ("ie_close_newTask_window".equals(s))
			return ie_close_newTask_window;
		return super.eiNamed(s);
	}

	/** LINKING OUTPUT EVENTS TO THEIR NAMES */
	public EventOutput eoNamed(String s)
	{
		if ("oe_start_stop_recording".equals(s))
			return oe_start_stop_recording;
		if ("oe_close_newTask_window".equals(s))
			return oe_close_newTask_window;
		return super.eoNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR NAMES */
	public ANY ivNamed(String s) throws FBRManagementException
	{
		if ("iv_new_task_name".equals(s))
			return iv_new_task_name;
		if ("iv_selected_arm".equals(s))
			return iv_selected_arm;
		return super.ivNamed(s);
	}

	/** LINKING OUTPUT VARIABLES TO THEIR NAMES */
	public ANY ovNamed(String s) throws FBRManagementException
	{
		if ("ov_new_task_name".equals(s))
			return ov_new_task_name;
		if ("ov_selected_arm".equals(s))
			return ov_selected_arm;
		return super.ovNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR VALUES */
	public void connectIV(String ivName, ANY newIV)

			throws FBRManagementException
	{

		if ("iv_new_task_name".equals(ivName))
		{
			connect_iv_new_task_name((WSTRING) newIV);
			return;
		}
		if ("iv_selected_arm".equals(ivName))
		{
			connect_iv_selected_arm((WSTRING) newIV);
			return;
		}
		super.connectIV(ivName, newIV);

	}

	private void connect_iv_new_task_name(WSTRING newIV)
	{
		iv_new_task_name = newIV;

	}

	private void connect_iv_selected_arm(WSTRING newIV)
	{
		iv_selected_arm = newIV;
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_new_task)
			service_ie_init_new_task();
		else if (e == ie_start_stop_recording)
			service_ie_start_stop_recording();
		else if (e == ie_close_newTask_window)
			service_ie_close_newTask_window();
	}

	private void service_ie_init_new_task()
	{

		initComponents();

	}

	private void service_ie_start_stop_recording()
	{
		ov_new_task_name.value = iv_new_task_name.value;
		// TODO
		// new_robot_task_agent.send_inform_message("new_task",
		// ov_new_task_name.value);
		// oe_start_stop_recording.serviceEvent(this);

	}

	private void service_ie_close_newTask_window()
	{
		newTaskFrame.dispose();
		oe_close_newTask_window.serviceEvent(this);
	}

	private void initComponents()
	{

		newTaskFrame = new JFrame("New Robot Task");
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		select_arm = new javax.swing.JComboBox<>();
		task_name = new javax.swing.JTextField();

		btn_start_stop_recording = new javax.swing.JButton();
		btn_close = new javax.swing.JButton();

		jLabel1.setText("Create New Task:");

		jLabel2.setText("Choose Type:");

		select_arm.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Robot Task:Right Arm", "Robot Task:Left Arm", "Robot Task:Both Arms" }));

		task_name.setText("");

		jLabel3.setText("Task Name:");

		btn_start_stop_recording.setText("Start Recording");
		btn_start_stop_recording.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btn_start_stop_recordingActionPerformed(evt);
			}
		});

		btn_close.setText("Close");
		btn_close.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				btn_closeActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(newTaskFrame.getContentPane());
		newTaskFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGroup(layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addContainerGap()
														.addComponent(jLabel1))
										.addGroup(
												layout.createSequentialGroup().addGap(52, 52, 52)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel2).addComponent(jLabel3))
														.addGap(18, 18, 18)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(select_arm,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(task_name,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 124,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addContainerGap()
														.addComponent(btn_start_stop_recording).addGap(18, 18, 18)))
								.addGap(18, 18, 18).addComponent(jLabel4).addGap(0, 89, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(btn_close)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(jLabel1).addGap(33, 33, 33)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(select_arm, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(41, 41, 41)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(task_name, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3).addComponent(jLabel4))
						.addGap(26, 26, 26).addComponent(btn_start_stop_recording).addGap(19, 19, 19)
						.addComponent(btn_close).addContainerGap(26, Short.MAX_VALUE)));

		newTaskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newTaskFrame.setVisible(true);
		newTaskFrame.pack();
	}

	private void btn_start_stop_recordingActionPerformed(java.awt.event.ActionEvent evt)
	{
		String temp_button_value = btn_start_stop_recording.getText();
		if (temp_button_value.equals("Start Recording"))
		{
			String temp_task_name = task_name.getText();
			if (temp_task_name.matches("\\s*"))
			{
				jLabel4.setText("required");
				jLabel4.setForeground(Color.red);
			} else
			{
				task_name_copy = temp_task_name;
				selected_arm_copy = select_arm.getSelectedItem().toString();
				start_recorder = true;
				btn_start_stop_recording.setText("Stop Recording");
				jLabel4.setText("");
				iv_new_task_name.value = "Start~" + temp_task_name;
				iv_selected_arm.value = select_arm.getSelectedItem().toString();
				begin_recording_timing = System.currentTimeMillis();
				ie_start_stop_recording.serviceEvent(this);
			}

		} else if (temp_button_value.equals("Stop Recording"))
		{
			start_recorder = false;
			btn_start_stop_recording.setText("Start Recording");
			iv_new_task_name.value = "Stop";
			try
			{
				end_recording_timing = System.currentTimeMillis();
				long time_diff = end_recording_timing - begin_recording_timing;
				save_task(task_name_copy, selected_arm_copy, time_diff);
			} catch (FileNotFoundException | UnsupportedEncodingException e)
			{
				System.out.println("Cannot write task data");
			}
			ie_start_stop_recording.serviceEvent(this);
		}
	}

	private void btn_closeActionPerformed(java.awt.event.ActionEvent evt)
	{
		if (start_recorder)
		{
			try
			{
				end_recording_timing = System.currentTimeMillis();
				long time_diff = end_recording_timing - begin_recording_timing;
				save_task(task_name_copy, selected_arm_copy, time_diff);
			} catch (FileNotFoundException | UnsupportedEncodingException e)
			{
				System.out.println("Cannot write task data");
			}
			iv_new_task_name.value = "Stop";
			ie_start_stop_recording.serviceEvent(this);
		}
		ie_close_newTask_window.serviceEvent(this);
	}

	/////////////////////// Helper Methods ////////////////////
	private String read_all_tasks(String arm)
	{
		String path = System.getProperty("user.home") + "/HMI_Worker/Tasks/Robot_tasks.task";
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

	// This method writes the new task data to the system when "stop recording"
	// button is pressed
	private void save_task(String new_task_name, String arm, long task_period)
			throws FileNotFoundException, UnsupportedEncodingException
	{

		String old_data = read_all_tasks(arm);
		String[] old_data_arr = old_data.split("~");

		String user_home_directory = System.getProperty("user.home");
		File file = new File(user_home_directory + "/HMI_Worker/Tasks/Robot_tasks.task");
		file.getParentFile().mkdirs();

		PrintWriter writer = new PrintWriter(file, "UTF-8");

		for (int i = 0; i < old_data_arr.length; i++)
		{
			if (!old_data_arr[i].matches("\\s*"))
			{
				String[] task_details = old_data_arr[i].split(":");
				if (!task_details[1].equals(new_task_name))
				{
					writer.println(old_data_arr[i]);
				}
			}

		}

		writer.print(arm + ":" + new_task_name + ":" + task_period);
		writer.close();

	}

	public static void main(String[] args)
	{
		new NewRobotTaskGUIFB();
	}

}
