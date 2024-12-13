package dwn.cda.thebot.bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class Bot extends ListenerAdapter {
    private Guild guild;
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        guild = event.getGuild();
        guild.updateCommands().addCommands(
                Commands.slash("duel", "Provoquer un utilisateur en duel")
                        .addOption(OptionType.USER, "user", "user", true)
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "duel":
                Member adversaire = event.getOption("user").getAsMember();
                if(adversaire != null)
                event.reply(event.getUser().getAsMention()+ " défie " +adversaire.getAsMention()+ " en duel !").queue();
                else {
                    event.reply("Utilisateur inconnu").setEphemeral(true).queue();
                }
                break;
            default:
                event.reply("Requete inconnu").setEphemeral(true).queue();
        }
    }
}
