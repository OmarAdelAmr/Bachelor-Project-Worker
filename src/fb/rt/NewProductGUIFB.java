package fb.rt;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import fb.components.Task;

public class NewProductGUIFB extends FBInstance
{

	// Variables declaration - do not modify
	private JFrame newProductFrame = new JFrame("New Product");
	private javax.swing.JButton jButton_add;
	private javax.swing.JButton jButton_create_product;
	private javax.swing.JButton jButton_close;
	private javax.swing.JButton jButton_delete;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel_choose_dependencies;
	private javax.swing.JLabel jLabel_choose_task;
	private javax.swing.JLabel jLabel_product_name;
	private javax.swing.JLabel jLabel_task_added_warning;
	private javax.swing.JLabel jLabel_empty_name_warning;
	private javax.swing.JPanel jPanel_dependencies_panel;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable jTable_choose_from;
	private javax.swing.JTable jTable_choosen_tasks;
	private javax.swing.JTextField jTextField_product_name;
	// End of variables declaration

	// Written Task data Arrays
	ArrayList<String> robot_tasks = new ArrayList<String>();
	ArrayList<String> worker_tasks = new ArrayList<String>();
	// End of Written Task data Arrays

	ArrayList<Task> all_selected_tasks = new ArrayList<Task>();

	// INPUT EVENTS
	public EventServer ie_init_new_product = new EventInput(this);
	public EventServer ie_delete_step = new EventInput(this);
	public EventServer ie_add_step = new EventInput(this);
	public EventServer ie_create_product = new EventInput(this);
	public EventServer ie_cancel = new EventInput(this);
	// END OF INPUT EVENTS

	// OUTPUT EVENTS
	public EventOutput oe_close_newProduct_window = new EventOutput();
	// END OF OUTPUT EVENTS

	public NewProductGUIFB()
	{
		super();
	}

	/////////////////////// FBDK ////////////////////////////
	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_new_product".equals(s))
			return ie_init_new_product;
		if ("ie_delete_step".equals(s))
			return ie_delete_step;
		if ("ie_add_step".equals(s))
			return ie_add_step;
		if ("ie_create_product".equals(s))
			return ie_create_product;
		if ("ie_cancel".equals(s))
			return ie_cancel;
		return super.eiNamed(s);
	}

	/** LINKING OUTPUT EVENTS TO THEIR NAMES */
	public EventOutput eoNamed(String s)
	{
		if ("oe_close_newProduct_window".equals(s))
			return oe_close_newProduct_window;
		return super.eoNamed(s);
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_new_product)
			service_ie_init_new_product();
		else if (e == ie_delete_step)
			service_ie_delete_step();
		else if (e == ie_add_step)
			service_ie_add_step();
		else if (e == ie_create_product)
			service_ie_create_product();
		else if (e == ie_cancel)
			service_ie_cancel();
	}

	private void service_ie_init_new_product()
	{
		robot_tasks = read_all_tasks("Robot_tasks");
		worker_tasks = read_all_tasks("Human_tasks");
		all_selected_tasks = new ArrayList<Task>();
		initComponents();
	}

	private void service_ie_delete_step()
	{
		// TODO Auto-generated method stub

	}

	private void service_ie_add_step()
	{
		// TODO Auto-generated method stub

	}

	private void service_ie_create_product()
	{
		// TODO Auto-generated method stub

	}

	private void service_ie_cancel()
	{
		// TODO Auto-generated method stub

	}

	//////////////////////// GUI ///////////////
	private void initComponents()
	{

		newProductFrame = new JFrame("New Product");
		jLabel_product_name = new javax.swing.JLabel();
		jTextField_product_name = new javax.swing.JTextField();
		jPanel_dependencies_panel = new javax.swing.JPanel();
		jComboBox1 = new javax.swing.JComboBox<>();
		jLabel_choose_task = new javax.swing.JLabel();
		jLabel_choose_dependencies = new javax.swing.JLabel();
		jLabel_task_added_warning = new javax.swing.JLabel();
		jButton_add = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable_choose_from = new javax.swing.JTable();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTable_choosen_tasks = new javax.swing.JTable();
		jButton_delete = new javax.swing.JButton();
		jButton_close = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jButton_create_product = new javax.swing.JButton();
		jLabel_empty_name_warning = new javax.swing.JLabel();

		newProductFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel_product_name.setText("Product Name:");

		jTextField_product_name.setText("");

		jPanel_dependencies_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		ArrayList<String> all_tasks = new ArrayList<String>(worker_tasks);
		all_tasks.addAll(robot_tasks);

		String[] all_tasks_arr = all_tasks.toArray(new String[all_tasks.size()]);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(all_tasks_arr));

		jLabel_choose_task.setText("Choose Task:");

		jLabel_choose_dependencies.setText("Choose Dependencies:");

		jButton_add.setText("Add");
		jButton_add.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_addActionPerformed(evt);
			}
		});

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int columnIndex)
			{
				switch (columnIndex)
				{
				case 0:
					return String.class;
				case 1:
					return Boolean.class;
				default:
					return String.class;
				}
			}

		};

		model.addColumn("Task name");
		model.addColumn("Check");

		for (int i = 0; i < all_tasks_arr.length; i++)
		{
			model.addRow(new Object[0]);
			// value, raw, column
			model.setValueAt(all_tasks_arr[i], i, 0);
			model.setValueAt(false, i, 1);

		}

		jTable_choose_from.setModel(model);
		jScrollPane2.setViewportView(jTable_choose_from);

		javax.swing.GroupLayout jPanel_dependencies_panelLayout = new javax.swing.GroupLayout(
				jPanel_dependencies_panel);
		jPanel_dependencies_panel.setLayout(jPanel_dependencies_panelLayout);
		jPanel_dependencies_panelLayout.setHorizontalGroup(jPanel_dependencies_panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel_dependencies_panelLayout.createSequentialGroup()
						.addGroup(jPanel_dependencies_panelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										jPanel_dependencies_panelLayout.createSequentialGroup().addContainerGap()
												.addGroup(jPanel_dependencies_panelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel_dependencies_panelLayout.createSequentialGroup()
																		.addGap(12, 12, 12).addComponent(jComboBox1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jLabel_choose_task)))
								.addGroup(jPanel_dependencies_panelLayout.createSequentialGroup().addGap(168, 168, 168)
										.addComponent(jButton_add).addGap(18, 18, 18).addComponent(
												jLabel_task_added_warning))
								.addGroup(jPanel_dependencies_panelLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel_dependencies_panelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jPanel_dependencies_panelLayout
																.createSequentialGroup().addGap(12, 12, 12)
																.addComponent(jScrollPane2,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 381,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jLabel_choose_dependencies))))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel_dependencies_panelLayout.setVerticalGroup(jPanel_dependencies_panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel_dependencies_panelLayout.createSequentialGroup().addGap(5, 5, 5)
						.addComponent(jLabel_choose_task)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel_choose_dependencies)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addGroup(jPanel_dependencies_panelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton_add).addComponent(jLabel_task_added_warning))
						.addContainerGap()));
		jTable_choosen_tasks.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Task", "Dependencies" }));
		jScrollPane3.setViewportView(jTable_choosen_tasks);

		jButton_delete.setText("Delete");
		jButton_delete.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton_deleteActionPerformed(evt);
			}
		});

		jLabel1.setText("Choosen tasks:");

		jButton_create_product.setText("Create");
		jButton_create_product.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				try
				{
					jButton_create_productActionPerformed(evt);
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} catch (UnsupportedEncodingException e)
				{
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

		jLabel1.setText("Choosen tasks:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(newProductFrame.getContentPane());
		newProductFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel_product_name)
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel_dependencies_panel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jTextField_product_name, javax.swing.GroupLayout.PREFERRED_SIZE,
												160, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(jLabel_empty_name_warning)))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1)
										.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addComponent(jButton_create_product)
														.addGap(13, 13, 13)))))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jButton_delete).addComponent(jButton_close))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(21, 21, 21).addComponent(jLabel_product_name).addGap(12, 12, 12)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField_product_name, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel_empty_name_warning))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jPanel_dependencies_panel, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(9, 9, 9)
												.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(jButton_delete).addGap(243, 243, 243)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton_close).addComponent(jButton_create_product))))
				.addContainerGap(18, Short.MAX_VALUE)));

		jTable_choose_from.getColumnModel().getColumn(1).setMaxWidth(60);
		newProductFrame.setVisible(true);
		newProductFrame.pack();
	}

	private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt)
	{
		ie_delete_step.serviceEvent(this);
	}

	private void jButton_addActionPerformed(java.awt.event.ActionEvent evt)
	{
		String temp_selected_task = jComboBox1.getSelectedItem().toString();
		ArrayList<Task> temp_dependencies = new ArrayList<Task>();
		for (int i = 0; i < jTable_choose_from.getRowCount(); i++)
		{
			Boolean is_checked = Boolean.valueOf(jTable_choose_from.getValueAt(i, 1).toString());
			String selected_value = jTable_choose_from.getValueAt(i, 0).toString();
			if (is_checked && !selected_value.equals(temp_selected_task))
			{
				temp_dependencies.add(new Task(selected_value));
			}

		}
		Task temp_task = new Task(temp_selected_task, temp_dependencies);
		if (!task_already_added(temp_task))
		{
			all_selected_tasks.add(temp_task);
			redraw_chosen_task_table();
		}

	}

	private void jButton_create_productActionPerformed(java.awt.event.ActionEvent evt)
			throws FileNotFoundException, UnsupportedEncodingException
	{
		String temp_product_name = jTextField_product_name.getText();
		if (temp_product_name.matches("\\s*"))
		{
			jLabel_empty_name_warning.setText("Name is Empty");
			jLabel_empty_name_warning.setForeground(Color.RED);
		} else
		{
			jLabel_empty_name_warning.setText("");
			save_product(temp_product_name);
			oe_close_newProduct_window.serviceEvent(this);
			newProductFrame.dispose();
		}
	}

	private void jButton_closeActionPerformed(java.awt.event.ActionEvent evt)
	{
		oe_close_newProduct_window.serviceEvent(this);
		newProductFrame.dispose();
	}

	// Helper Methods
	private ArrayList<String> read_all_tasks(String task_type)
	{
		String path = System.getProperty("user.home") + "/HMI_Worker/Tasks/" + task_type + ".task";
		BufferedReader br;
		ArrayList<String> result = new ArrayList<String>();
		try
		{
			br = new BufferedReader(new FileReader(path));
			String currentLine = "";

			while ((currentLine = br.readLine()) != null)
			{
				result.add(currentLine);
			}
			br.close();
			return result;
		} catch (IOException e)
		{
			return result;
		}
	}

	public void redraw_chosen_task_table()
	{
		DefaultTableModel chosen_task_table_model = (DefaultTableModel) jTable_choosen_tasks.getModel();
		chosen_task_table_model.getDataVector().removeAllElements();

		for (int i = 0; i < all_selected_tasks.size(); i++)
		{
			Task temp_task = all_selected_tasks.get(i);
			String temp_task_name = temp_task.getName();
			ArrayList<Task> temp_task_dependencies = temp_task.getDependencies();
			int dependencies_size = temp_task_dependencies.size();

			for (int j = 0; j < (dependencies_size > 1 ? dependencies_size : 1); j++)
			{
				if (j == 0)
				{
					if (dependencies_size > 0)
					{
						chosen_task_table_model
								.addRow(new Object[] { temp_task_name, temp_task_dependencies.get(j).getName() });
					} else
					{
						chosen_task_table_model.addRow(new Object[] { temp_task_name, "" });
					}

				} else
				{
					chosen_task_table_model.addRow(new Object[] { "", temp_task_dependencies.get(j).getName() });
				}
			}
			chosen_task_table_model.addRow(new Object[] { "---------------------------------------------------------",
					"---------------------------------------------------------" });

		}

	}

	public boolean task_already_added(Task task)
	{
		String temp_task_name = task.getName();
		for (int i = 0; i < all_selected_tasks.size(); i++)
		{
			if (all_selected_tasks.get(i).getName().equals(temp_task_name))
			{
				jLabel_task_added_warning.setText("Task already added");
				jLabel_task_added_warning.setForeground(Color.red);
				return true;
			}
		}
		jLabel_task_added_warning.setText("");
		return false;
	}

	private void save_product(String product_name) throws FileNotFoundException, UnsupportedEncodingException
	{
		String user_home_directory = System.getProperty("user.home");

		File file = new File(user_home_directory + "/HMI_Worker/Products/" + product_name + ".product");
		file.getParentFile().mkdirs();
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		for (int i = 0; i < all_selected_tasks.size(); i++)
		{
			writer.println(all_selected_tasks.get(i).toString());
		}

		writer.close();
	}

	public static void main(String[] args)
	{
		new NewProductGUIFB();
	}

}
