package de.presti.ree6.commands.impl.fun;

import com.google.gson.JsonObject;
import de.presti.ree6.bot.BotWorker;
import de.presti.ree6.commands.Category;
import de.presti.ree6.commands.CommandEvent;
import de.presti.ree6.commands.interfaces.Command;
import de.presti.ree6.commands.interfaces.ICommand;
import de.presti.ree6.main.Main;
import de.presti.ree6.utils.data.Data;
import de.presti.ree6.utils.external.RequestUtility;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

/**
 * A command to get a random dog images.
 */
@Command(name = "monke", description = "Shows monke.", category = Category.FUN)
public class Monkey implements ICommand {

	/**
	 * @inheritDoc
	 */
	@Override
	public void onPerform(CommandEvent commandEvent) {
		JsonObject js = RequestUtility.request(RequestUtility.Request.builder()
		                                                             .url("https://tenor.com/view/reject-modernity-return-to-monke-monke-gif-19167526")
		                                                             .build()).getAsJsonObject();

		EmbedBuilder em = new EmbedBuilder();

		em.setTitle("Monke!");
		em.setColor(BotWorker.randomEmbedColor());
		em.setImage(js.get("message").getAsString());
		em.setFooter("Requested by " + commandEvent.getMember().getUser().getAsTag() + " - " + Data.ADVERTISEMENT,
		             commandEvent.getMember().getUser().getAvatarUrl()
		);

		Main.getInstance().getCommandManager().sendMessage(em,
		                                                   commandEvent.getChannel(),
		                                                   commandEvent.getInteractionHook()
		);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public CommandData getCommandData() {
		return null;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public String[] getAlias() {
		return new String[]{"monkey", "monkegif"};
	}
}
