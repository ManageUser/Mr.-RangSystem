package core;

import commands.CommandTest;
import events.EventVoiceJoin;
import events.EventVoiceLeave;
import events.EventXpSystem;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    static String token;

    public static void main(String args[]) throws LoginException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken(token).build();

        jda.setAutoReconnect(true);



        jda.addEventListener(new EventXpSystem());
        jda.addEventListener(new EventVoiceJoin());
        jda.addEventListener(new EventVoiceLeave());




    }
}
