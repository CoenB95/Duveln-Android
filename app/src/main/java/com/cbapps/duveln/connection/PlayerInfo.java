package com.cbapps.duveln.connection;

/**
 * @author CoenB95
 */

public class PlayerInfo {
	private String username;

	public PlayerInfo(String username)
	{
		this.username = username;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof PlayerInfo))
			return false;
		PlayerInfo other = (PlayerInfo) obj;
		return username != null && username.equals(other.username);
	}

	@Override
	public String toString() {
		return "PlayerInfo{username=" + username + "}";
	}
}
