package me.KillerSmurf.Mute;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MuteAdder implements CommandExecutor{
	private Mute plugin;
	public MuteAdder(Mute plugin){
		this.plugin=plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			System.out.println("Console Can Not Mute Players");
			return true;
		}
		Player player = (Player) sender;
		if(!(args.length==1)){
			player.sendMessage("mute : /mute <PlayerName>");
			return true;
		}
		try{
			Player other = player.getServer().getPlayer(args[0]);
			if(this.plugin.mutes.containsKey(player)){
				if(this.plugin.mutes.get(player).containsKey(other)){
					this.plugin.mutes.get(player).remove(other);
					player.sendMessage("You Un-Muted : "+args[0]);
					other.sendMessage("Player "+player.getDisplayName()+" Un-Muted You");
				}else{
					this.plugin.mutes.get(player).put(other, 1);
					this.plugin.mutes.put(player, this.plugin.mutes.get(player));
					player.sendMessage("You Muted : "+args[0]);
					other.sendMessage("Player "+player.getDisplayName()+" Muted You");
				}
			}
			else{
				HashMap<Player, Integer> pl = new HashMap<Player, Integer>();
				pl.put(other, 1);
				this.plugin.mutes.put(player, pl);
				player.sendMessage("You Muted : "+args[0]);
				other.sendMessage("Player "+player.getDisplayName()+" Muted You");
			}
		}catch (Exception e){
			player.sendMessage("Player Named "+args[0]+" does not exist or is not online");
		}
		return true;
	}
}
