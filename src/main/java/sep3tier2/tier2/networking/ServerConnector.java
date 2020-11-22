package sep3tier2.tier2.networking;

import sep3tier2.tier2.models.ActualRequest;

public interface ServerConnector
{
    ActualRequest requestToServer(ActualRequest actualRequest);
}
