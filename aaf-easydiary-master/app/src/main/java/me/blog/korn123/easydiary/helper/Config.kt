package me.blog.korn123.easydiary.helper

import android.content.Context
import io.github.aafactory.commons.helpers.BaseConfig
import io.github.aafactory.commons.utils.CommonUtils

/**
 * Created by CHO HANJOONG on 2017-12-24.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

class Config(context: Context) : BaseConfig(context) {
    companion object {
        fun newInstance(context: Context) = Config(context)
    }

    var settingFontName: String
        get() = legacyPrefs.getString(SETTING_FONT_NAME, CUSTOM_FONTS_SUPPORTED_LANGUAGE_DEFAULT)
        set(settingFontName) = legacyPrefs.edit().putString(SETTING_FONT_NAME, settingFontName).apply()

    var aafPinLockSavedPassword: String
        get() = legacyPrefs.getString(APP_LOCK_SAVED_PASSWORD, APP_LOCK_DEFAULT_PASSWORD)
        set(aafPinLockSavedPassword) = legacyPrefs.edit().putString(APP_LOCK_SAVED_PASSWORD, aafPinLockSavedPassword).apply()

    var previousActivity: Int
        get() = legacyPrefs.getInt(PREVIOUS_ACTIVITY, -1)
        set(previousActivity) = legacyPrefs.edit().putInt(PREVIOUS_ACTIVITY, previousActivity).apply()
    
    var settingFontSize: Float
        get() = legacyPrefs.getFloat(SETTING_FONT_SIZE, CommonUtils.dpToPixelFloatValue(context, DEFAULT_FONT_SIZE_SUPPORT_LANGUAGE.toFloat()))
        set(settingFontSize) = legacyPrefs.edit().putFloat(SETTING_FONT_SIZE, settingFontSize).apply()

    var diarySearchQueryCaseSensitive: Boolean
        get() = legacyPrefs.getBoolean(DIARY_SEARCH_QUERY_CASE_SENSITIVE, false)
        set(diarySearchQueryCaseSensitive) = legacyPrefs.edit().putBoolean(DIARY_SEARCH_QUERY_CASE_SENSITIVE, diarySearchQueryCaseSensitive).apply()

    var aafPinLockEnable: Boolean
        get() = legacyPrefs.getBoolean(APP_LOCK_ENABLE, false)
        set(aafPinLockEnable) = legacyPrefs.edit().putBoolean(APP_LOCK_ENABLE, aafPinLockEnable).apply()

    var isInitDummyData: Boolean
        get() = legacyPrefs.getBoolean(INIT_DUMMY_DATA_FLAG, false)
        set(isInitDummyData) = legacyPrefs.edit().putBoolean(INIT_DUMMY_DATA_FLAG, isInitDummyData).apply()

    var lineSpacingScaleFactor: Float
        get() = legacyPrefs.getFloat(LINE_SPACING_SCALE_FACTOR, LINE_SPACING_SCALE_DEFAULT)
        set(lineSpacingScaleFactor) = legacyPrefs.edit().putFloat(LINE_SPACING_SCALE_FACTOR, lineSpacingScaleFactor).apply()

    var settingThumbnailSize: Float
        get() = prefs.getFloat(SETTING_THUMBNAIL_SIZE, DEFAULT_THUMBNAIL_SIZE_DP.toFloat())
        set(settingThumbnailSize) = prefs.edit().putFloat(SETTING_THUMBNAIL_SIZE, settingThumbnailSize).apply()

    var boldStyleEnable: Boolean
        get() = prefs.getBoolean(SETTING_BOLD_STYLE, false)
        set(boldStyleEnable) = prefs.edit().putBoolean(SETTING_BOLD_STYLE, boldStyleEnable).apply()
    
    var multiPickerEnable: Boolean
        get() = prefs.getBoolean(SETTING_MULTIPLE_PICKER, false)
        set(multiPickerEnable) = prefs.edit().putBoolean(SETTING_MULTIPLE_PICKER, multiPickerEnable).apply()

    var fingerprintLockEnable: Boolean
        get() = prefs.getBoolean(SETTING_FINGERPRINT_LOCK, false)
        set(fingerprintLockEnable) = prefs.edit().putBoolean(SETTING_FINGERPRINT_LOCK, fingerprintLockEnable).apply()

    var fingerprintEncryptData: String
        get() = prefs.getString(FINGERPRINT_ENCRYPT_DATA, "")
        set(fingerprintEncryptData) = prefs.edit().putString(FINGERPRINT_ENCRYPT_DATA, fingerprintEncryptData).apply()

    var fingerprintEncryptDataIV: String
        get() = prefs.getString(FINGERPRINT_ENCRYPT_DATA_IV, "")
        set(fingerprintEncryptDataIV) = prefs.edit().putString(FINGERPRINT_ENCRYPT_DATA_IV, fingerprintEncryptDataIV).apply()

    var fingerprintAuthenticationFailCount: Int
        get() = prefs.getInt(FINGERPRINT_AUTHENTICATION_FAIL_COUNT, 0)
        set(fingerprintAuthenticationFailCount) = prefs.edit().putInt(FINGERPRINT_AUTHENTICATION_FAIL_COUNT, fingerprintAuthenticationFailCount).apply()

    var enableCardViewPolicy: Boolean
        get() = prefs.getBoolean(ENABLE_CARD_VIEW_POLICY, false)
        set(enableCardViewPolicy) = prefs.edit().putBoolean(ENABLE_CARD_VIEW_POLICY, enableCardViewPolicy).apply()
    
    var enableContentsSummary: Boolean
        get() = prefs.getBoolean(SETTING_CONTENTS_SUMMARY, true)
        set(enableContentsSummary) = prefs.edit().putBoolean(SETTING_CONTENTS_SUMMARY, enableContentsSummary).apply()

    var postCardCropMode: Int
        get() = prefs.getInt(POSTCARD_CROP_MODE, 0)
        set(postCardCropMode) = prefs.edit().putInt(POSTCARD_CROP_MODE, postCardCropMode).apply()

}