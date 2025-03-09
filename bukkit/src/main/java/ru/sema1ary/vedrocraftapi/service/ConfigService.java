package ru.sema1ary.vedrocraftapi.service;

import lombok.NonNull;

@SuppressWarnings("unused")
public interface ConfigService extends Service {
    void reload();

    @NonNull
    <T> T get(@NonNull String index);
}
