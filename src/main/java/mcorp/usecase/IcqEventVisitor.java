package mcorp.usecase;

import mcorp.client.impl.ForismaticHttpClient;
import mcorp.client.impl.OpenWeatherHttpClient;
import mcorp.client.impl.RzhunemoguHttpClient;
import mcorp.domain.openweather.OpenWeatherResponse;
import mcorp.domain.rzhunemogu.RzhunemoguRandomRequestType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.im.botapi.BotApiClientController;
import ru.mail.im.botapi.entity.ChatAction;
import ru.mail.im.botapi.fetcher.event.*;

import java.io.IOException;

import static java.util.Objects.isNull;

public class IcqEventVisitor implements EventVisitor<String, String> {

    private final BotApiClientController controller;

    private final RzhunemoguHttpClient rzhunemoguClient = new RzhunemoguHttpClient();
    private final OpenWeatherHttpClient openWeatherClient = new OpenWeatherHttpClient();
    private final ForismaticHttpClient forismaticClient = new ForismaticHttpClient();

    private final static Logger log = LogManager.getLogger("app");

    public IcqEventVisitor(BotApiClientController controller) {
        this.controller = controller;
    }

    @Override
    public String visitUnknown(UnknownEvent event, String s) {
        return null;
    }

    @Override
    public String visitNewMessage(NewMessageEvent event, String s) {
        String chatId = event.getChat().getChatId();
        String recivedMessage = event.getText();
        String botMessage = "";
        long messageId = 0;
        try {
            switch (recivedMessage.toLowerCase()){
                case "анекдот" -> botMessage = getRandomAnekdot();
                case "погода" -> botMessage = getWeather();
                case "цитата" -> botMessage = getRandomQuotation();
                default -> botMessage = recivedMessage;
            }
            controller.sendActions(chatId, ChatAction.TYPING);
            messageId = controller.sendTextMessage(chatId, botMessage).getMsgId(); // send message
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "";
    }

    @Override
    public String visitNewChatMembers(NewChatMembersEvent event, String s) {
        return null;
    }

    @Override
    public String visitLeftChatMembers(LeftChatMembersEvent leftChatMembersEvent, String s) {
        return null;
    }

    @Override
    public String visitDeletedMessage(DeletedMessageEvent deletedMessageEvent, String s) {
        return null;
    }

    @Override
    public String visitEditedMessage(EditedMessageEvent editedMessageEvent, String s) {
        return null;
    }

    @Override
    public String visitPinnedMessage(PinnedMessageEvent pinnedMessageEvent, String s) {
        return null;
    }

    @Override
    public String visitUnpinnedMessage(UnpinnedMessageEvent unpinnedMessageEvent, String s) {
        return null;
    }

    private String getRandomAnekdot() {
        if (isNull(rzhunemoguClient)) return "";
        return rzhunemoguClient.getRandomAnekdotJoke(RzhunemoguRandomRequestType.JOKE);
    }

    private String getWeather() {
        if (isNull(openWeatherClient)) return "";
        OpenWeatherResponse response = openWeatherClient.getWeather(1, "Moscow");
        StringBuilder sb = new StringBuilder();
        sb.append(response.getCity().getName());
        sb.append(" ");
        sb.append("\n");
        response.getList().forEach(x -> {
            sb.append(x.getDt_txt());
            sb.append(", ");
            sb.append("temp = ");
            sb.append(x.getMain().getTemp());
            sb.append("\n");
        });
        return sb.toString();
    }

    private String getRandomQuotation() {
        if (isNull(forismaticClient)) return "";
        return isNull(forismaticClient.getRandomQuotation()) ? "" : forismaticClient.getRandomQuotation().getQuoteText();
    }
}
