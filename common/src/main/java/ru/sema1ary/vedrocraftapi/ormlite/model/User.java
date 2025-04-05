package ru.sema1ary.vedrocraftapi.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sema1ary.vedrocraftapi.ormlite.dao.impl.UserDaoImpl;
import ru.sema1ary.vedrocraftapi.ormlite.model.gender.Gender;
import ru.sema1ary.vedrocraftapi.ormlite.model.joiner.JoinerMessage;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "players", daoClass = UserDaoImpl.class)
public class User {
    @DatabaseField(unique = true, generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false)
    private String username;

    @DatabaseField(canBeNull = false)
    private int reputation;

    @DatabaseField(canBeNull = false)
    private Gender gender;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "join_message")
    private JoinerMessage joinMessage;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "quit_message")
    private JoinerMessage quitMessage;

    @DatabaseField(canBeNull = false, columnName = "is_vanish_active")
    private boolean isVanishActive;

    @DatabaseField(canBeNull = false, columnName = "is_sticks_enabled")
    private boolean isSticksEnabled;
}
