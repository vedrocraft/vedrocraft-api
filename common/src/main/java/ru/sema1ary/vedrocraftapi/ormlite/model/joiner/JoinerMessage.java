package ru.sema1ary.vedrocraftapi.ormlite.model.joiner;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sema1ary.vedrocraftapi.ormlite.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "joiner_messages")
public class JoinerMessage {
    @DatabaseField(unique = true, generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String text;

    @DatabaseField(canBeNull = false)
    private MessageType type;

    @ForeignCollectionField
    private ForeignCollection<User> users;
}
