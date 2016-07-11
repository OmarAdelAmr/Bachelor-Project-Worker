package fb.mas;

import java.io.IOException;

import fb.rt.ExecuteProduct;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ExecuteAgent extends MainAgent
{
	private static ExecuteProduct ss = new ExecuteProduct("");

	public ExecuteAgent(String AgentName, int platform, String DestinationAgentName)
	{
		super(AgentName, platform, DestinationAgentName);
	}

	protected void setup()
	{
		this.addBehaviour(new ReceiveExecuteMSG());
	}

	private class ReceiveExecuteMSG extends CyclicBehaviour
	{
		MessageTemplate mt = MessageTemplate.MatchConversationId("Execute");

		@Override
		public void action()
		{
			ACLMessage msg = myAgent.receive(mt);
			// TODO
			if (msg != null)
			{
				String product_name = msg.getContent();
				ss.setProduct_name(product_name);
				try
				{
					ss.execute_order();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
			{

			}
		}

	}

}
