package events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.HashMap;

public class EventXpSystem extends ListenerAdapter {


    private HashMap<Member, Integer> playerXp = new HashMap<Member, Integer>();
    private HashMap<Member, Integer> playerMin = new HashMap<Member, Integer>();
    private HashMap<Member, Integer> playerSec = new HashMap<Member, Integer>();


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!level")) {
            if (event.getChannel().getName().equalsIgnoreCase("level")) {
                event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("Dein Level ["+ getPlayerXp(event.getMember()) + "]").setDescription("\nStunden: [" + getPlayerXp(event.getMember()) + "]\nMinuten: [" + getPlayerMin(event.getMember()) + "]\nSekunden: [" + getPlayerSec(event.getMember()) + "]").build()).complete();
            }
        }

    }



    @Override
    public void onGuildVoiceJoin(final GuildVoiceJoinEvent event) {

        //randXp(event.getMember());
        if (!playerXp.containsKey(event.getMember())){
            setPlayerXp(event.getMember(), 0);
        }
        setPlayerXp(event.getMember(), getPlayerXp(event.getMember()) + 0);



        if (!playerSec.containsKey(event.getMember())){
            setPlayerSec(event.getMember(), 0);
        }
        setPlayerSec(event.getMember(), getPlayerSec(event.getMember()) + 0);



        if (!playerMin.containsKey(event.getMember())){
            setPlayerMin(event.getMember(), 0);
        }
        setPlayerMin(event.getMember(), getPlayerMin(event.getMember()) + 0);
        event.getMember().getGuild().getController().setNickname(event.getMember(),  " ").queue();
        event.getMember().getGuild().getController().setNickname(event.getMember(), event.getMember().getUser().getName() + " [" + getPlayerMin(event.getMember()) + "]").queue();



        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (event.getMember().getVoiceState().inVoiceChannel() == true && event.getMember().getVoiceState().isMuted() == false){
                    loop(event.getMember());
                }
            }
        });
        if (event.getMember().getVoiceState().inVoiceChannel() == true) {
            thread.start();
        }

    }

    @Override
    public void onGuildVoiceMute(final GuildVoiceMuteEvent event) {
         if (event.getMember().getVoiceState().isMuted() == false) {
             Thread thread = new Thread(new Runnable() {
                 public void run() {
                     while (event.getMember().getVoiceState().inVoiceChannel() == true){
                         loop(event.getMember());
                     }
                 }
             });
             if (event.getMember().getVoiceState().inVoiceChannel() == true) {
                 thread.start();
             }
         }
    }

    private void loop(Member member) {
        while (member.getVoiceState().inVoiceChannel() == true && member.getVoiceState().isMuted() == false) {
            if (getPlayerSec(member) < 60) {
                setPlayerSec(member, getPlayerSec(member) + 1);
            }
            if (getPlayerSec(member) >= 60) {
                if (getPlayerMin(member) < 60) {
                    setPlayerMin(member, getPlayerMin(member) + 1);
                    member.getGuild().getController().setNickname(member,  " ").queue();
                    member.getGuild().getController().setNickname(member, member.getUser().getName() + " [" + getPlayerMin(member) + "]").queue();
                    setPlayerSec(member, 0);
                }
                if (getPlayerMin(member) >= 60) {
                    setPlayerXp(member, getPlayerXp(member) + 1);
                    member.getGuild().getController().setNickname(member,  " ").queue();
                    member.getGuild().getController().setNickname(member, member.getUser().getName() + " [" + getPlayerXp(member) + "]").queue();
                    if (getPlayerXp(member) == 1) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\n" + member + " hat nun seine erste Wurst gelegt!").build()).complete();
                    }
                    if (getPlayerXp(member) == 4) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\n4 steht für Livestyle!").build()).complete();
                    }
                    if (getPlayerXp(member) == 11) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\n" + member + " ist nun ein echter Basketballer").build()).complete();
                    }
                    if (getPlayerXp(member) == 20) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\n" + member + " ist nun ein richtiger Horst").build()).complete();
                    }
                    if (getPlayerXp(member) == 31) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\n" + member + " ist nun ein richtiger 31er!!").build()).complete();
                    }

                    if (getPlayerXp(member) == 88) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\nMontanablack ist cool!").build()).complete();
                    }

                    if (getPlayerXp(member) == 404) {
                        member.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Der User " + member + " ist nun Level [" + getPlayerXp(member) + "]\nError 404!").build()).complete();
                    }
                    setPlayerMin(member, 0);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }









    private int getPlayerXp(Member member) {
        return playerXp.get(member);
    }
    private void setPlayerXp(Member member, int num) {
        playerXp.put(member, num);
    }



    private int getPlayerMin(Member member) {
        return playerMin.get(member);
    }
    private void setPlayerMin(Member member, int num) {
        playerMin.put(member, num);
    }




    private int getPlayerSec(Member member) {
        return playerSec.get(member);
    }
    private void setPlayerSec(Member member, int num) {
        playerSec.put(member, num);
    }


}
