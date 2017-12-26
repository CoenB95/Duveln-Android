package com.cbapps.duveln.connection;

/**
 * @author CoenB95
 */

public class DuvelnServerInfo {
	/// <summary>
	/// The (mostly) unique id of this game-session.
	/// </summary>
	private String id;

	/// <summary>
	/// The name of this game-session.
	/// </summary>
	private String name;


	private DuvelnServerInfo(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof DuvelnServerInfo))
			return false;
		DuvelnServerInfo other = (DuvelnServerInfo) obj;
		return id != null && id.equals(other.id) &&
				name != null && name.equals(other.name);
	}

	@Override
	public String toString() {
		return "DuvelnServerInfo{id=" + id + ", name=" + name + "}";
	}
}
