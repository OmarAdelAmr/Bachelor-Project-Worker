package fb.rt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fb.components.HumanTask;
import fb.components.RobotTask;
import fb.components.Task;
import fb.mas.ExecuteAgent;
import fb.mas.MainAgent;

public class ExecuteProduct2
{

	private String product_name;
	private ArrayList<Task> product_tasks_arr = new ArrayList<Task>();
	private static MainAgent execute_agent = new ExecuteAgent("ExecuteAgent", 1, "Baxter-Execute");
	private static MainAgent image_agent = new ExecuteAgent("ImageAgent", 1, "Baxter-Image");
	private String image_details = "";
	// TCP Client Variables
	private Socket skt;
	private int socket_port = 10016;
	// End of TCP Variables

	// Gesture Variables
	private String last_gesture = "";
	private int last_gesture_counter = 0;
	// End of Gesture Variables

	// GUI Variables
	private JFrame execute_product_frame = new JFrame("Execute Task");
	private javax.swing.JLabel image_label;
	private int image_hight = 700;
	private int image_width = 600;
	// End of GUI Variables

	public ExecuteProduct2(String product_name)
	{
		execute_agent.start();
		image_agent.start();
		this.product_name = product_name;
		initComponents();
	}

	///////////////////////////////// GUI /////////////////////////////////
	private void initComponents()
	{
		execute_product_frame = new JFrame("Execute Task");
		image_label = new javax.swing.JLabel();

		execute_product_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(execute_product_frame.getContentPane());
		execute_product_frame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(image_label, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(image_label, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE));

		execute_product_frame.setSize(image_hight, image_width);

	}

	////////////////////////////// Helper Methods //////////////////////////////
	public void execute_order() throws IOException
	{
		reset_variables();
		last_gesture = "";
		last_gesture_counter = 0;
		if (!product_name.matches("\\s*"))
		{
			execute_product_frame.setVisible(true);
			TCP_open_connection(true);
			product_tasks_arr = new ArrayList<Task>(read_product_tasks(product_name));
			// TODO Add dependencies
			for (int i = 0; i < product_tasks_arr.size(); i++)
			{
				Task temp_task = product_tasks_arr.get(i);
				create_image(i);
				if (temp_task instanceof RobotTask)
				{
					execute_agent.send_inform_message("execute_task", temp_task.getName());
					System.out.println(temp_task.getName());
					try
					{

						Thread.sleep(((RobotTask) temp_task).getTask_time() + 500);
					} catch (InterruptedException ex)
					{
						Thread.currentThread().interrupt();
					}
				} else if (temp_task instanceof HumanTask)
				{
					System.out.println(temp_task.getName());
					read_from_leap(((HumanTask) temp_task).getAssociated_gesture());
				}
				if (i == product_tasks_arr.size() - 1)
				{
					create_image(product_tasks_arr.size());
				}
			}
			TCP_open_connection(false);
			execute_product_frame.setVisible(false);

		}

	}

	public ArrayList<Task> read_product_tasks(String product_name)
	{
		String path = System.getProperty("user.home") + "/HMI_Worker/Products/" + product_name + ".product";
		BufferedReader br;
		ArrayList<Task> result = new ArrayList<Task>();
		try
		{
			System.out.println(path);
			br = new BufferedReader(new FileReader(path));
			String currentLine = "";

			while ((currentLine = br.readLine()) != null)
			{
				String[] temp_currentLine_arr = currentLine.split("~");
				// TODO Add Dependencies Here
				String[] temp_task_details = temp_currentLine_arr[0].split(":");
				if (temp_task_details[0].equals("Human Task"))
				{
					result.add(new HumanTask(temp_task_details[1], temp_task_details[2]));
				} else if (temp_task_details[0].equals("Robot Task"))
				{
					result.add(new RobotTask(temp_task_details[1] + ":" + temp_task_details[2],
							Long.parseLong(temp_task_details[3])));
				}

			}
			br.close();
			return result;
		} catch (IOException e)
		{
			e.printStackTrace();
			return result;
		}
	}

	public void create_image(int current_task_index) throws IOException
	{
		image_details = "";
		BufferedImage img = new BufferedImage(image_hight, image_width, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.WHITE);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		g.fillRect(0, 0, image_hight, image_width);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		int x_position = 10;
		int y_position = 35;
		for (int i = 0; i < product_tasks_arr.size(); i++)
		{
			Task temp_task = product_tasks_arr.get(i);
			String temp_task_details = "";
			if (temp_task instanceof RobotTask)
			{
				temp_task_details = temp_task.getName();
				image_details += temp_task_details + "~";
			} else
			{
				temp_task_details = temp_task.getName() + "(" + ((HumanTask) temp_task).getAssociated_gesture() + ")";
				image_details += temp_task_details + "~";
			}
			if (i < current_task_index)
			{
				g.setColor(Color.GREEN);
				g.drawString(temp_task_details + " ==> DONE", x_position, y_position);
			} else if (i == current_task_index)
			{
				g.setColor(Color.RED);
				g.drawString(temp_task_details + " ==> In Progress", x_position, y_position);
			} else
			{
				g.setColor(Color.BLACK);
				g.drawString(temp_task_details, x_position, y_position);
			}

			y_position += 35;
		}
		image_details += current_task_index;
		image_agent.send_inform_message("image_order", image_details);

		g.dispose();
		image_label.setIcon(new ImageIcon(img));
	}

	public void read_from_leap(String required_gesture) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		System.out.println(required_gesture);
		last_gesture = "";
		last_gesture_counter = 0;
		while (true)
		{
			// Message from client
			if ((last_gesture = in.readLine()) != null)
			{
				if (last_gesture.equals(required_gesture))
				{
					last_gesture_counter++;
					System.out.println(last_gesture_counter);
					if (last_gesture_counter == 50)
					{
						break;
					}

				}
			}
		}

	}

	public void TCP_open_connection(boolean open_flag) throws IOException
	{
		if (open_flag)
		{
			while (true)
			{
				try
				{
					skt = new Socket("127.0.0.1", socket_port);
					System.out.println("Connected");
					break;

				} catch (IOException e)
				{
					System.out.println("Waiting for server. Trying again in 10 seconds");
					try
					{
						Thread.sleep(10000);
					} catch (InterruptedException ex)
					{
						Thread.currentThread().interrupt();
					}
				}
			}

		} else
		{
			if (!skt.isClosed())
			{
				skt.close();
			}
		}
	}

	public void test() throws IOException
	{
		product_tasks_arr.add(new RobotTask("Robot Task 1", 10000));
		product_tasks_arr.add(new HumanTask("Human Task 1", "Tool"));
		product_tasks_arr.add(new HumanTask("Human Task 2", "Right Hand:Front"));
		product_tasks_arr.add(new RobotTask("Robot Task 2", 9000));
		product_tasks_arr.add(new RobotTask("Robot Task 3", 10000));
		product_tasks_arr.add(new RobotTask("Robot Task 4", 10000));
		product_tasks_arr.add(new HumanTask("Human Task 3", "Swipe Right"));
		execute_order();
	}

	public String getProduct_name()
	{
		return product_name;
	}

	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
	}

	public void reset_variables()
	{
		product_tasks_arr = new ArrayList<Task>();
		initComponents();
	}

	public static void main(String[] args) throws IOException
	{

	}

}
