package mcorp.domain.forismatic;

public record ForismaticResponse(
        String quoteText,
        String quoteAuthor,
        String senderName,
        String senderLink,
        String quoteLink) {
    public ForismaticResponse {
    }

    public ForismaticResponse() {
        this(null, null, null, null, null);
    }
}
