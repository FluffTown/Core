package com.zip.core.utility;

import com.zip.core.Core;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
public class botShit extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getChannelType() == ChannelType.PRIVATE) {
            if (!event.getAuthor().isBot()) {
                if (event.getMessage().getContentDisplay().contains("why")) {
                    event.getMessage().getChannel().sendMessage("Because i say so.").queue();
                } else {
                    event.getMessage().getChannel().sendMessage("dont fucking dm me").queue();
                }
            }
        } else if (!event.getAuthor().isBot() && event.getChannel().getId().equals(Core.plugin.chatChannel)) {
            if (event.getMessage().getContentDisplay().length() > 256) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention()+" please keep your message under 256 characters").queue();
                event.getMessage().delete().queue();
                return;
            }
            if (event.getMessage().getAttachments().size() > 0) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention()+" please do not attach files").queue();
                event.getMessage().delete().queue();
                return;
            }
            Core.plugin.broadcast("&7[&x&7&2&8&9&D&ADiscord&7] &r " + event.getAuthor().getName() + " »&r " + event.getMessage().getContentDisplay());
        }
    }
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        System.out.println("[bot] UwU bot ready");
    }
    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        if (!event.getAuthor().isBot() && event.getChannel().getId().equals(Core.plugin.chatChannel)) {
            if (event.getMessage().getContentDisplay().length() > 256) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention()+" please keep your message under 256 characters").queue();
                event.getMessage().delete().queue();
            }
        }
    }
}