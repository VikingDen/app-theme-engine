package com.afollestad.appthemeengine.customizers;

import com.afollestad.appthemeengine.Config;

/**
 * @author Aidan Follestad (afollestad)
 */
public interface ATEToolbarCustomizer {

    @Config.LightToolbarMode
    int getLightToolbarMode();
}
