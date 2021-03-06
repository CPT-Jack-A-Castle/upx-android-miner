// Copyright (c) 2019, Mine2Gether.com
// Copyright (c) 2021 Scala
// Copyright (c) 2020, uPlexa
// Please see the included LICENSE file for more information.

// Note: This file contains some code taken from Scala, a project that had
// forked uPlexa's original android miner and stripped all copyright and
// and released the miner as their own without any credit to the uPlexa
// contributors. Since then, the only thing the Scala team has completed in their original
// whitepaper from 2018 is the android miner (after we were able to
// get one working for them) Their new UI is shiny, and thus, some of their code has
// been used.

package io.uplexaproject.androidminer;

import android.content.SharedPreferences;

import java.util.HashMap;

public class Config {
    final static String[] SUPPORTED_ARCHITECTURES = {"arm64-v8a", "armeabi-v7a"};

    private static Config mSettings;
    private SharedPreferences preferences;

    static final int DefaultPoolIndex = 1;
    public static final Long statsDelay = 30000L;
    static final String miner_upxrig = "xmrig";
    static final String algo = "cryptonight-upxtwo";

    public static final String version = "4";
    static final Integer logMaxLength = 50000;
    static final Integer logPruneLength = 1000;

    private HashMap<String,String> mConfigs = new HashMap<String, String>();

    static void initialize(SharedPreferences preferences) {
        mSettings = new Config();
        mSettings.preferences = preferences;
    }

    static void write(String key, String value) {
        if(!key.startsWith("system:")) {
            mSettings.preferences.edit().putString(key, value).apply();
        }

        if(value.isEmpty()) {
            return;
        }

        mSettings.mConfigs.put(key, value);
    }

    static void clear() {
        mSettings.preferences.edit().clear().apply();
        mSettings.mConfigs.clear();
    }

    public static String read(String key) {
        if(!key.startsWith("system:")) {
            return mSettings.preferences.getString(key, "");
        }
        if(!mSettings.mConfigs.containsKey(key)) {
            return "";
        }
        return mSettings.mConfigs.get(key);
    }

    public Config() {
    }
}
