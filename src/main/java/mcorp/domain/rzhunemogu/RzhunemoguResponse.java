package mcorp.domain.rzhunemogu;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "root")
public class RzhunemoguResponse {
    String content;
}
