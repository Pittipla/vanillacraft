package net.vanillacraft.CoreFunctions.utils;

import net.vanillacraft.CoreFunctions.datastores.CoreData;
import net.vanillacraft.CoreFunctions.datastores.Delay;
import net.vanillacraft.CoreFunctions.datastores.PlayerProfile;
import net.vanillacraft.CoreFunctions.main.CoreFunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by ryan on 5/7/2015.
 */
public class CoreErrors
{

    private CoreFunctions plugin;

    public CoreErrors(CoreFunctions plugin)
    {
        this.plugin = plugin;
    }

    private String errorPreFix = ChatColor.RED + "[Error] : ";
    private String warrningPrefix = ChatColor.GOLD + "[Warning] : ";
    private String syntaxPreFix = ChatColor.GRAY + "[Syntax] : ";
    private String nerfPreFix = ChatColor.LIGHT_PURPLE + "[Nerf] : ";
    private String infoPreFix = ChatColor.GRAY + "[Info] : ";

    private void sendMessage(Player player, String preFix, String message)
    {
        player.sendMessage(preFix + message);
    }

    public void sendError(Player player, String errorMessage)
    {
        sendMessage(player, errorPreFix, errorMessage);
    }

    public void sendWarning(Player player, String message)
    {
        sendMessage(player, warrningPrefix, message);
    }

    public void sendNerfMsg(Player player, String message)
    {
        sendMessage(player, nerfPreFix, message);
    }

    public void sendInfoMsg(Player player, String message)
    {
        sendMessage(player, infoPreFix, message);
    }

    public void timerNotDone(Player player, String errorMessage, String formattedTime)
    {
        sendError(player, "You can't " + errorMessage + " so soon. Please wait "
                + ChatColor.GREEN + formattedTime + ChatColor.RED
                + " and try again.");
    }

    public void mustBeInWorld(Player player, String worldName)
    {
        sendError(player, "You must be in the " + worldName + " to use this command");
    }

    public void playerNotOnline(Player player)
    {
        sendError(player, "That player is not online.");
    }

    public void commandSantaxError(Player player, String command, String syntax)
    {
        sendError(player, "That was improper usage of the " + command + " the proper syntax is:");
        sendMessage(player, syntaxPreFix, "/" + command + " " + syntax);
    }

    public void enableModMode(Player player)
    {
        sendError(player, "You must enable moderator mode to use this command.");
    }

    public void notifyPlayerModifyPlace(Player player, String zoneName)
    {
        sendError(player, ChatColor.GREEN + "You are modifying " + ChatColor.WHITE + zoneName + ChatColor.GREEN + ".");
    }

    public void notifyPlayerCantModifyPlace(Player player, String zoneName)
    {
        sendWarning(player, "You're not allowed to modify " + ChatColor.WHITE + zoneName + ChatColor.GREEN + ".");
    }

    public void cantCraftGoldenApples(Player player)
    {
        sendError(player, "You can't craft Enchanted Golden Apples.");
    }

    public void playerNotFound(Player player)
    {
        sendError(player, "That player was not found.");
    }

    public void youCannotNerfThatPlayer(Player player)
    {
        sendError(player, "You can not nerf that player.");
    }

    public void notifyModNerfedPlayer(Player mod, Player target, boolean froze)
    {
        String msg = "";
        if (froze)
        {
            msg = " has frozen ";
        }
        else
        {
            msg = " has unfrozen ";
        }

        for (Player player : Bukkit.getOnlinePlayers())
        {
            PlayerProfile profile = CoreData.getProfile(target);
            if (profile.hasPermision("nerf"))
            {
                sendNerfMsg(player, mod.getDisplayName() + ChatColor.LIGHT_PURPLE + msg + target.getDisplayName());
            }
        }
    }

    public void playerAlreadyFrozen(Player mod)
    {
        sendError(mod, "That player is already frozen.");
    }

    public void playerFactionLoaded(Player player)
    {
        sendInfoMsg(player, "Your faction has been loaded and you can now interact with the world normally");
    }

}
