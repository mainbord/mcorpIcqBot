package mcorp.domain.rzhunemogu;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "root")
public class RzhunemoguResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RzhunemoguResponse() {
    }

    public RzhunemoguResponse(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RzhunemoguResponse that = (RzhunemoguResponse) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "RzhunemoguResponse{" +
                "content='" + content + '\'' +
                '}';
    }
}
