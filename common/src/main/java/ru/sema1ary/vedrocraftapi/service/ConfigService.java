package ru.sema1ary.vedrocraftapi.service;

import lombok.NonNull;

@SuppressWarnings("all")
public interface ConfigService extends Service {
    void reload();

    @NonNull
    <T> T get(@NonNull String index);

    <T> void set(@NonNull String index, T value);
}
