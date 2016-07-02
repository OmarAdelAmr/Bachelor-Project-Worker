package fb.rt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fb.components.HumanTask;
import fb.components.RobotTask;
import fb.components.Task;
import fb.datatype.ANY;
import fb.datatype.WSTRING;

public class ExecuteProduct extends FBInstance
{

	private ArrayList<Task> test_arr = new ArrayList<Task>();

	// TCP Client Variables
	private Socket skt;
	private int socket_port = 10015;
	// End of TCP Variables

	// Gesture Variables
	private String last_gesture = "";
	private int last_gesture_counter = 0;
	// End of Gesture Variables

	// GUI Variables
	private int image_hight = 700;
	private int image_width = 600;
	private JFrame execute_product_frame = new JFrame();
	private javax.swing.JLabel image_label;
	// End of GUI Variables

	// INPUT EVENTS
	public EventServer ie_init_execute_product = new EventInput(this);
	// END OF INPUT EVENTS

	// INPUT VARIABLES
	public WSTRING iv_product_name = new WSTRING(); // INPUT
	// END OF INPUT VARIABLES

	public ExecuteProduct() throws IOException
	{
		super();
		// TODO
		initComponents();
		TCP_open_connection(true);
		test();
	}

	/** LINKING INPUT EVENTS TO THEIR NAMES */
	public EventServer eiNamed(String s)
	{
		if ("ie_init_execute_product".equals(s))
			return ie_init_execute_product;
		return super.eiNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR NAMES */
	public ANY ivNamed(String s) throws FBRManagementException
	{
		if ("iv_product_name".equals(s))
			return iv_product_name;
		return super.ivNamed(s);
	}

	/** LINKING INPUT VARIABLES TO THEIR VALUES */
	public void connectIV(String ivName, ANY newIV) throws FBRManagementException
	{
		if ("iv_product_name".equals(ivName))
		{
			connect_iv_product_name((WSTRING) newIV);
			return;
		}
		super.connectIV(ivName, newIV);
	}

	private void connect_iv_product_name(WSTRING newIV)
	{
		iv_product_name = newIV;
	}

	/** Defining the Methods */
	public void serviceEvent(EventServer e)
	{
		if (e == ie_init_execute_product)
			try
			{
				service_ie_init_execute_product();
			} catch (IOException e1)
			{
				// TODO
				e1.printStackTrace();
			}
	}

	private void service_ie_init_execute_product() throws IOException
	{
		initComponents();
		test();
	}

	private void initComponents()
	{

		execute_product_frame = new JFrame();
		image_label = new javax.swing.JLabel();

		execute_product_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(execute_product_frame.getContentPane());
		execute_product_frame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(image_label, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(image_label, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE));

		execute_product_frame.setSize(image_hight, image_width);

		execute_product_frame.setVisible(true);
	}

	public void create_image(int current_task_index) throws IOException
	{
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
		for (int i = 0; i < test_arr.size(); i++)
		{
			if (i < current_task_index)
			{
				g.setColor(Color.GREEN);
				g.drawString(test_arr.get(i).getName() + " ==> DONE", x_position, y_position);
			} else if (i == current_task_index)
			{
				g.setColor(Color.RED);
				g.drawString(test_arr.get(i).getName() + " ==> In Progress", x_position, y_position);
			} else
			{
				g.setColor(Color.BLACK);
				g.drawString(test_arr.get(i).getName(), x_position, y_position);
			}

			y_position += 35;
		}

		g.dispose();
		image_label.setIcon(new ImageIcon(img));

	}

	public void execute_order() throws IOException
	{
		for (int i = 0; i < test_arr.size(); i++)
		{
			Task temp_task = test_arr.get(i);
			if (temp_task instanceof RobotTask)
			{
				create_image(i);
				try
				{
					Thread.sleep(((RobotTask) temp_task).getTask_time());
				} catch (InterruptedException ex)
				{
					Thread.currentThread().interrupt();
				}
			} else if (temp_task instanceof HumanTask)
			{
				create_image(i);
				read_from_leap(((HumanTask) temp_task).getAssociated_gesture());
			}
			if (i == test_arr.size() - 1)
			{
				create_image(test_arr.size());
			}
		}
		TCP_open_connection(false);
	}

	public void read_from_leap(String required_gesture) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		System.out.println(required_gesture);
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
		last_gesture = "";
		last_gesture_counter = 0;
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

	public static void main(String[] args) throws IOException
	{
		new ExecuteProduct();
	}

	public void test() throws IOException
	{
		test_arr.add(new RobotTask("Robot Task 1", 10000));
		test_arr.add(new HumanTask("Human Task 1", "Tool"));
		test_arr.add(new HumanTask("Human Task 2", "Right Hand:Front"));
		test_arr.add(new RobotTask("Robot Task 2", 9000));
		test_arr.add(new RobotTask("Robot Task 3", 10000));
		test_arr.add(new RobotTask("Robot Task 4", 10000));
		test_arr.add(new HumanTask("Human Task 3", "Swipe Right"));
		execute_order();
	}

}
