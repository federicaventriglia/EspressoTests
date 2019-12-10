#!/usr/bin/env bash

echo "Enter language code"
read language_code

adb shell am broadcast -a com.android.intent.action.SET_LOCALE --es com.android.intent.extra.LOCALE $language_code

