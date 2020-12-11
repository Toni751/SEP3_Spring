package sep3tier2.tier2.networking;

import sep3tier2.tier2.models.ActualRequest;

/**
 * Interface responsible for establishing sockets connections with tier3 and sending and receiving requests and responses
 * @version 1.0
 * @author Group1
 */
public interface ServerConnector
{
    /**
     * Sends a request to the server, with the given actual request (request object + images, if any)
     * @param actualRequest the request to be sent
     * @return an actual request with the request object + images, if any
     */
    ActualRequest requestToServer(ActualRequest actualRequest);
}
