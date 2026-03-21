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
public class UserDiscussionKey implements Serializable{

    private long idUser;

    private long idDiscussion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDiscussionKey that = (UserDiscussionKey) o;
        return idUser == that.idUser && idDiscussion == that.idDiscussion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idDiscussion);
    }
}
