package fb.mas;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ContainerController;

@SuppressWarnings("serial")
public class MainAgent extends Agent
{

	private AID DestinationServerID;
	private String DestinationServerAddress;
	private String AgentName;

	public MainAgent(String AgentName, int hostPlatform, String DestinationAgentName)
	{
		// 0 for local host, 1 for remote host
		if (hostPlatform == 0)
		{
			this.DestinationServerID = new AID(DestinationAgentName + "@WorkerPlatform", AID.ISGUID);
			this.DestinationServerAddress = "http://172.0.0.1:7777/acc";
		} else
		{
			this.DestinationServerID = new AID("Baxter@BaxterPlatform", AID.ISGUID);
			this.DestinationServerAddress = "http://192.168.12.160:7778/acc";
		}
		this.AgentName = AgentName;
	}

	public void start()
	{
		ContainerController cc = WorkerAgentGenerator.getCCInstance();
		try
		{
			cc.acceptNewAgent(this.AgentName, this).start();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	// protected void setup()
	// {
	//
	// }

	public void send_inform_message(String conversationID, String message_content)
	{
		ACLMessage inform_message = new ACLMessage(ACLMessage.INFORM);
		inform_message.setConversationId(conversationID);
		inform_message.setContent(message_content);
		this.getServerID().addAddresses(this.getServerAddress());
		inform_message.addReceiver(this.getServerID());
		this.send(inform_message);
	}

	public AID getServerID()
	{
		return DestinationServerID;
	}

	public void setServerID(AID serverID)
	{
		DestinationServerID = serverID;
	}

	public String getServerAddress()
	{
		return DestinationServerAddress;
	}

	public void setServerAddress(String serverAddress)
	{
		DestinationServerAddress = serverAddress;
	}

}
