package ru.sema1ary.vedrocraftapi.service;

@SuppressWarnings("unused")
public interface ServiceGetter {
    default <T> T getService(Class<T> clazz) {
        return ServiceManager.getService(clazz);
    }
}
