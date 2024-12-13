package dwn.cda.thebot.bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
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
                String duelId = event.getUser().getId() + "-" + adversaire.getId();
                if(adversaire != null)
                event.reply(event.getUser().getAsMention()+ " défie " +adversaire.getAsMention()+ " en duel !").addActionRow(
                        Button.success("accept-" + duelId, "Accepter"),
                        Button.danger("decline-" + duelId, "Refuser")
                ).queue();
                else {
                    event.reply("Utilisateur inconnu").setEphemeral(true).queue();
                }
                break;
            default:
                event.reply("Requete inconnu").setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event){
        String[] parts = event.getComponentId().split("-");
        String action = parts[0];

        switch(action) {
            case "accept":
                event.reply("Duel accepté ! Le combat commence !").queue();
                break;
            case "decline":
                event.reply("Duel refusé !").queue();
                break;
            default:
                event.reply("Action inconnue !").setEphemeral(true).queue();
                break;
        }
    }
}
