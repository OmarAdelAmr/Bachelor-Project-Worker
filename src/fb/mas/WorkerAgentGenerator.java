package fb.mas;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class WorkerAgentGenerator
{
	/**
	 * A container instance for the
	 */
	private static ContainerController cc = null;

	public static ContainerController getCCInstance()
	{
		if (cc == null)
		{
			// Start the platform.
			String ar[] = new String[3];
			ar[0] = "-gui";
			ar[1] = "-platform-id";
			ar[2] = "WorkerPlatform";

			jade.Boot.main(ar);

			// ***** JADE programmers guide p. 52. *****
			// Get a hold on JADE runtime
			Runtime rt = Runtime.instance();
			// Create a default profile
			Profile p = new ProfileImpl();
			// Create a new non-main container, connecting to the default
			// main container (i.e. on this host, port 1099)
			cc = rt.createAgentContainer(p);
		}
		return cc;
	}
}