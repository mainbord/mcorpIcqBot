package mcorp.usecase;

import ru.mail.im.botapi.BotApiClientController;
import ru.mail.im.botapi.fetcher.OnEventFetchListener;
import ru.mail.im.botapi.fetcher.event.Event;

import java.util.List;

public class IcqEventListener implements OnEventFetchListener {

    private final BotApiClientController controller;

    public IcqEventListener(BotApiClientController controller) {
        this.controller = controller;
    }

    @Override
    public void onEventFetch(List<Event> events) {
        events.forEach(e -> e.accept(new IcqEventVisitor(controller), ""));
    }
}
