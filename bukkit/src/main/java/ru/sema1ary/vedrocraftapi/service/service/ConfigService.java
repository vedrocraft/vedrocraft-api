package ru.sema1ary.vedrocraftapi.service.service;

import lombok.NonNull;
import ru.sema1ary.vedrocraftapi.service.Service;

@SuppressWarnings("unused")
public interface ConfigService extends Service {
    void reload();

    @NonNull
    <T> T get(@NonNull String index);
}
