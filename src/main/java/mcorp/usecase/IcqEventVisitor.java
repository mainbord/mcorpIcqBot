package mcorp.usecase;

import lombok.SneakyThrows;
import mcorp.client.impl.ForismaticHttpClient;
import mcorp.client.impl.OpenWeatherHttpClient;
import mcorp.client.impl.RzhunemoguHttpClient;
import mcorp.domain.openweather.OpenWeatherResponse;
import mcorp.domain.rzhunemogu.RzhunemoguRandomRequestType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.im.botapi.BotApiClientController;
import ru.mail.im.botapi.api.entity.InlineKeyboardButton;
import ru.mail.im.botapi.api.entity.SendTextRequest;
import ru.mail.im.botapi.entity.ChatAction;
import ru.mail.im.botapi.fetcher.event.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static mcorp.usecase.IcqEventVisitor.Button.*;

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
        return "Введите анекдот, цитата или погода";
    }

    @Override
    public String visitNewMessage(NewMessageEvent event, String s) {

        String chatId = event.getChat().getChatId();
        String receivedMessage = event.getText();
        String botMessage = "";
        long messageId = 0;
        try {
            switch (receivedMessage.toLowerCase()) {
                case "анекдот" -> botMessage = getRandomAnekdot();
                case "погода" -> botMessage = getWeather();
                case "цитата" -> botMessage = getRandomQuotation();
                default -> botMessage = receivedMessage;
            }
            SendTextRequest request = new SendTextRequest();
            request.setChatId(chatId);
            request.setText(botMessage);
            request.setKeyboard(getKeyBoard());

            messageId = controller.sendTextMessage(request).getMsgId();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return String.valueOf(messageId);
    }

    private List<List<InlineKeyboardButton>> getKeyBoard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstLine = new ArrayList<>();
        keyboard.add(firstLine);
        InlineKeyboardButton jokeButton = InlineKeyboardButton.callbackButton("Анекдот", JOKE_BUTTON.name(), "");
        InlineKeyboardButton quotationButton = InlineKeyboardButton.callbackButton("Цитата", QUOTATION_BUTTON.name(), "");
        InlineKeyboardButton weatherButton = InlineKeyboardButton.callbackButton("Погода", WEATHER_BUTTON.name(), "");
        firstLine.add(jokeButton);
        firstLine.add(quotationButton);
        firstLine.add(weatherButton);
        return keyboard;
    }

    enum Button {
        JOKE_BUTTON,
        QUOTATION_BUTTON,
        WEATHER_BUTTON
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

    @Override
    @SneakyThrows
    public String visitCallbackQuery(CallbackQueryEvent event, String s) {
        Button eventType = Button.valueOf(event.getCallbackData());

        SendTextRequest request = new SendTextRequest();
        request.setChatId(event.getMessageChat().getChatId());

        request.setKeyboard(getKeyBoard());

        switch (eventType) {
            case JOKE_BUTTON -> request.setText(getRandomAnekdot());
            case QUOTATION_BUTTON -> request.setText(getRandomQuotation());
            case WEATHER_BUTTON -> request.setText(getWeather());
            default -> request.setText(event.getMessageText());
        }
        controller.sendTextMessage(request);

        return "";
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
