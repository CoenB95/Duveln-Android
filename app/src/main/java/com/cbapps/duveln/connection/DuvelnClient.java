package com.cbapps.duveln.connection;

import java.util.List;

/**
 * @author CoenB95
 */
public interface DuvelnClient {
	void HandleAvailableSessionsResponse(DuvelnServerInfo serverInfo, List<DuvelnSessionInfo> result);
	void HandleSessionJoined(DuvelnSessionInfo session, boolean success, String reason);
}
