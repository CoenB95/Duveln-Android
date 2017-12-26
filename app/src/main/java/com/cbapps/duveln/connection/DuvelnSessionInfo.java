package com.cbapps.duveln.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author CoenB95
 */

public class DuvelnSessionInfo {
	/**The (mostly) unique id of this game-session.*/
	private String id;

	public PlayerLeavePolicy leavePolicy = PlayerLeavePolicy.AlwaysResetRound;

	/**The name of this game-session.*/
	private String name;

	/**Gets or sets a value indicating if the session has been started.*/
	private boolean open;

	/**Gets or sets a value indicating the number of rings this session's board should have.*/
	private int rings;

	/**Gets or sets a value indicating the start-score each player should begin with in this session.*/
	private int startScore;

	/**The player competing in this game-sesion.*/
	private List<PlayerInfo> players;

	private DuvelnSessionInfo(boolean open, String id, String name) {
		this(open, id, name, 5, 2, null);
	}
	private DuvelnSessionInfo(boolean open, String id, String name, int rings, int startScore, Collection<PlayerInfo> players)
	{
		this.open = open;
		this.id = id;
		this.name = name;
		this.rings = rings;
		this.startScore = startScore;
		this.players = new ArrayList<PlayerInfo>(players);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof DuvelnSessionInfo))
			return false;
		DuvelnSessionInfo other = (DuvelnSessionInfo) obj;
		return id != null && id.equals(other.id);
	}

	@Override
	public String toString() {
		return "DuvelnSessionInfo{id=" + id + ", name=" + name + "}";
	}

	public enum PlayerLeavePolicy
	{
		/**Game-policy in which, when a player leaves, the current round is reset.*/
		AlwaysResetRound
	}
}
