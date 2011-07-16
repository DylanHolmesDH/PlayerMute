package me.KillerSmurf.Mute;

import java.util.HashMap;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.*;

public class Mute extends JavaPlugin{
	public HashMap<Player, HashMap<Player, Integer>> mutes=new HashMap<Player, HashMap<Player, Integer>>();
	public Mute(){
	}
	public MuteListener ml = new MuteListener(this);
	public void onDisable(){
		System.out.println("PlayerMute Disabled!");
	}
	public void onEnable(){
		System.out.println("PlayerMute Enabled!");
		PluginManager pm = getServer().getPluginManager();
		getCommand("mute").setExecutor(new MuteAdder(this));
		pm.registerEvent(Event.Type.PLAYER_CHAT, ml, Event.Priority.Normal, this);
	}
}