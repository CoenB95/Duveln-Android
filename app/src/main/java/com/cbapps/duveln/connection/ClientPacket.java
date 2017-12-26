package com.cbapps.duveln.connection;

/**
 * @author CoenB95
 */

public abstract class ClientSidePacket {

	private String packetType;
	//private DuvelnSessionInfo targetSession;

	protected ClientSidePacket(String packetType/*, DuvelnSessionInfo targetSession*/) {
		this.packetType = packetType;
		//this.targetSession = targetSession;
	}

	public abstract void ExecuteOnClient(DuvelnClient client, DuvelnClientSession session, PacketSerializer connection);
}
