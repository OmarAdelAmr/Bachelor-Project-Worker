package fb.rt;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFrame;

import fb.datatype.ANY;
import fb.datatype.WSTRING;

public class MainAppGUIFB extends FBInstance
{
	// WorkerAgent fake_agent = new WorkerAgent();

	// GUI Variables
	private JFrame mainAppFrame = new JFrame();
	private javax.swing.JButton jButton_execute;
	private javax.swing.JButton jButton_new_product;
	private javax.swing.JButton jButton_new_robot_task;
	private javax.swing.JButton jButton_new_worker_task;
	private javax.swing.JComboBox<String> jComboBox_products;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JSeparator jSeparator1;
	// End of GUI Variables

	// INPUT EVENTS
	public EventServer ie_init_main_gui = new EventInput(this);
	// END OF INPUT EVENTS

	// OUTPUT EVENTS
	public EventOutput oe_init_new_robot_task = new EventOutput();
	public EventOutput oe_init_new_human_task = new EventOutput();
	public EventOutput oe_init_new_product = new EventOutput();
	public EventOutput oe_execute_product = new EventOutput();
	// END OF OUTPUT EVENTS

	// OUTPUT VARIABLES
	public WSTRING ov_product_name = new WSTRING(); // OUTPUT
	// END OF OUTPUT VARIABLES

	public MainAppGUIFB()
	{
		super();
		// fake_agent.start();

	}

	/////////////////////// FBDK ////////////////////////////

	// LINKING INPUT EVENTS TO THEIR NAMES
	public EventServer eiNamed(String s)
	{
		if ("ie_init_main_gui".equals(s))
			return ie_init_main_gui;
		return super.eiNamed(s);
	}

	// LINKING OUTPUT EVENTS TO THEIR NAMES
	public EventOutput eoNamed(String s)
	{
		if ("oe_init_new_robot_task".equals(s))
			return oe_init_new_robot_task;
		if ("oe_init_new_human_task".equals(s))
			return oe_init_new_human_task;
		if ("oe_init_new_product".equals(s))
			return oe_init_new_product;
		if ("oe_execute_product".equals(s))
			return oe_execute_product;
		return super.eoNamed(s);
	}

	// LINKING OUTPUT VARIABLES TO THEIR NAMES
	public ANY ovNamed(String s) throws FBRManagementException
	{
		if ("ov_product_name".equals(s))
			return ov_product_name;
		return super.ovNamed(s);
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_main_gui)
			service_ie_init_main_gui();
	}

	private void service_ie_init_main_gui()
	{
		initComponents();

	}

	///////////////////////////////// GUI /////////////////////////////
	private void initComponents()
	{

		mainAppFrame = new JFrame("Main App");
		jButton_new_robot_task = new javax.swing.JButton();
		jButton_new_product = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jComboBox_products = new javax.swing.JComboBox<>();
		jButton_execute = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jButton_new_worker_task = new javax.swing.JButton();

		jButton_new_robot_task.setText("New Robot Task");
		jButton_new_robot_task.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_new_robot_taskActionPerformed(evt);
			}
		});

		jButton_new_product.setText("Create new product");
		jButton_new_product.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_new_productActionPerformed(evt);
			}
		});

		jButton_new_worker_task.setText("New Worker Task");
		jButton_new_worker_task.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_new_worker_taskActionPerformed(evt);
			}
		});

		jLabel1.setText("Choose product:");

		String[] products = read_all_products().split("~");

		jComboBox_products.setModel(new javax.swing.DefaultComboBoxModel<>(products));

		jButton_execute.setText("Execute product");
		jButton_execute.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_executeActionPerformed(evt);
			}
		});

		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainAppFrame.getContentPane());
		mainAppFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jButton_new_robot_task, javax.swing.GroupLayout.DEFAULT_SIZE,
												153, Short.MAX_VALUE)
										.addComponent(jButton_new_worker_task, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(jButton_new_product))
						.addGap(60, 60, 60)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(44, 44, 44)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButton_execute)
								.addComponent(jComboBox_products, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addContainerGap(69, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGap(0, 31, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton_new_robot_task).addComponent(jLabel1))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup()
								.addComponent(jComboBox_products, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(29, 29, 29).addComponent(jButton_execute))
								.addGroup(layout.createSequentialGroup().addGap(8, 8, 8)
										.addComponent(jButton_new_worker_task).addGap(26, 26, 26)
										.addComponent(jButton_new_product)))
						.addGap(49, 49, 49))
						.addGroup(layout.createSequentialGroup().addComponent(jSeparator1).addContainerGap()))));

		mainAppFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		mainAppFrame.setVisible(true);
		mainAppFrame.pack();
	}

	private void jButton_new_robot_taskActionPerformed(java.awt.event.ActionEvent evt)
	{
		oe_init_new_robot_task.serviceEvent(this);
		mainAppFrame.dispose();
	}

	private void jButton_new_worker_taskActionPerformed(java.awt.event.ActionEvent evt)
	{
		oe_init_new_human_task.serviceEvent(this);
		mainAppFrame.dispose();
	}

	private void jButton_new_productActionPerformed(java.awt.event.ActionEvent evt)
	{
		oe_init_new_product.serviceEvent(this);
		mainAppFrame.dispose();
	}

	private void jButton_executeActionPerformed(java.awt.event.ActionEvent evt)
	{
		ov_product_name.value = jComboBox_products.getSelectedItem().toString();
		mainAppFrame.setVisible(false);
		oe_execute_product.serviceEvent(this);

	}

	private static String read_all_products()
	{
		String dirName = System.getProperty("user.home") + "/HMI_Worker/Products";
		File dir = new File(dirName);

		File[] existing_recoreds = dir.listFiles(new FilenameFilter()
		{
			public boolean accept(File dir, String filename)
			{
				return filename.endsWith(".product");
			}
		});

		String res = "";
		if (existing_recoreds != null)
		{
			for (int i = 0; i < existing_recoreds.length; i++)
			{
				String file_name = existing_recoreds[i].getName();
				//
				if (file_name.endsWith(".product"))
				{
					String split = file_name.substring(0, file_name.length() - ".product".length());
					res += split + "~";
				}
			}
			if (!res.equals(""))
			{
				res = res.substring(0, res.length() - 1);
			}
		}

		return res;
	}

	public static void main(String[] args)
	{
		new MainAppGUIFB();
	}

}
