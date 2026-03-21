package App.PhiloHub.Models.JoinTablesKeys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceivedDiscussionMessageKey implements Serializable {
    private long idDiscussionMessage;
    private long idReceiver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedDiscussionMessageKey that = (ReceivedDiscussionMessageKey) o;
        return idDiscussionMessage == that.idDiscussionMessage && idReceiver == that.idReceiver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscussionMessage, idReceiver);
    }
}
