package com.dayio.automation.ui.core.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"system:properties"})
public interface EnvProperties extends Config {
    @Key("dev.environment.url")
    String getDevEnvironmentUrl();
}

