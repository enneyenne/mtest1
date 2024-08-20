package ru.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface AppConfigs extends Config {

    @Key("host")
    String host();

}
