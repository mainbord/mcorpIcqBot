package mcorp.domain.forismatic;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForismaticResponse {
    String quoteText;
    String quoteAuthor;
    String senderName;
    String senderLink;
    String quoteLink;
}
