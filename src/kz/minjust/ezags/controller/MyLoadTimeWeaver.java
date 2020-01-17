package kz.minjust.ezags.controller;

import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;

public class MyLoadTimeWeaver extends SimpleLoadTimeWeaver
{
    public ClassLoader getInstrumentableClassLoader()
    {
        ClassLoader instrumentableClassLoader = super.getInstrumentableClassLoader();
        if (instrumentableClassLoader.getClass().getName().endsWith("SimpleInstrumentableClassLoader")) {
            return instrumentableClassLoader.getParent();
        }
        return instrumentableClassLoader;
    }
}
