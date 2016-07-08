package fb.mas;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ContainerController;

@SuppressWarnings("serial")
public class WorkerAgent extends Agent
{

	private AID ServerID;
	private String ServerAddress;
	private static int agents_counter = 1;

	public WorkerAgent()
	{
		this.ServerID = new AID("Baxter@BaxterPlatform", AID.ISGUID);
		// this.ServerAddress = "http://192.168.12.160:7778/acc";
		this.ServerAddress = "http://172.20.10.2:7778/acc";
	}

	public void start()
	{
		ContainerController cc = WorkerAgentGenerator.getCCInstance();
		try
		{
			cc.acceptNewAgent("Worker" + agents_counter, this).start();
			agents_counter++;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void setup()
	{

	}

	public AID getServerID()
	{
		return ServerID;
	}

	public void setServerID(AID serverID)
	{
		ServerID = serverID;
	}

	public String getServerAddress()
	{
		return ServerAddress;
	}

	public void setServerAddress(String serverAddress)
	{
		ServerAddress = serverAddress;
	}

	public void send_inform_message(String conversationID, String message_content)
	{
		ACLMessage inform_message = new ACLMessage(ACLMessage.INFORM);
		inform_message.setConversationId(conversationID);
		inform_message.setContent(message_content);
		this.getServerID().addAddresses(this.getServerAddress());
		inform_message.addReceiver(this.getServerID());
		this.send(inform_message);
	}

}