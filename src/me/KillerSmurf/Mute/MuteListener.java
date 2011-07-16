package me.KillerSmurf.Mute;

import org.bukkit.entity.Player;
import org.bukkit.event.player.*;


public class MuteListener extends PlayerListener{
	
	public Mute plugin;
	public MuteListener(Mute plugin){
		this.plugin=plugin;
	}

	public void onPlayerChat(PlayerChatEvent event){
		Player player = event.getPlayer();
		Object[] recips = event.getRecipients().toArray();
		for(int i=0;i<recips.length;i++){
			if(this.plugin.mutes.containsKey((Player)recips[i])){
				if(this.plugin.mutes.get((Player)recips[i]).containsKey(player)){
					event.getRecipients().remove((Player)recips[i]);
				}
			}
		}
	}
}
