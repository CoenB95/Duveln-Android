package com.cbapps.duveln.connection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author CoenB95
 */
public interface ClientSession
{
	void HandleCardsReset(Collection<PlayerInfo> players);
	void HandleCardsRevealed(Map<PlayerInfo, Card> revealings);
	void HandleGameEnded(List<PlayerInfo> winners);
	void HandleGameStarted();
	void HandleMoveRequested();
	void HandlePlayerScoreAdjusted(PlayerInfo player, int scoreDelta);
	void HandlePlayerMove(PlayerInfo player, PlayerMove move, PlayerInfo nextPlayer, Card newCard);
	void HandlePlayerRenamed(PlayerInfo oldName, PlayerInfo newName);
	void HandlePlayersChanged(Collection<PlayerInfo> newPlayers, Collection<PlayerInfo> oldPlayers);
}

