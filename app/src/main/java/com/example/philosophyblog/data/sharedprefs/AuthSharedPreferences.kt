package com.example.philosophyblog.data.sharedprefs

import android.content.Context
import java.time.LocalDateTime
import javax.inject.Inject

class AuthSharedPreferences @Inject constructor(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    var userId: String?
        get() {
            return sharedPreferences.getString(USER_ID, null)
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(USER_ID, it)
                    .apply()
            }
        }

    var accessToken: String?
        get() {
            return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(ACCESS_TOKEN_KEY, it)
                    .apply()
            }
        }

    var refreshToken: String?
        get() {
            return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(REFRESH_TOKEN_KEY, it)
                    .apply()
            }
        }

    var tokenExpiresAt: LocalDateTime?
        get() {
            val stringDateTime = sharedPreferences.getString(TOKEN_EXPIRES_AT_KEY, null)
            stringDateTime?.let {
                return LocalDateTime.parse(it)
            }
            return null
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(TOKEN_EXPIRES_AT_KEY, it.toString())
                    .apply()
            }
        }

    var login: String?
        get() {
            return sharedPreferences.getString(LOGIN_KEY, null)
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(LOGIN_KEY, it)
                    .apply()
            }
        }

    var password: String?
        get() {
            return sharedPreferences.getString(PASSWORD_KEY, null)
        }
        set(value) {
            value?.let {
                sharedPreferences.edit()
                    .putString(PASSWORD_KEY, it)
                    .apply()
            }
        }

    var isDeleted: Boolean
        get() {
            return sharedPreferences.getBoolean(IS_ACC_DELETED, false)
        }
        set(value) {
            value.let {
                sharedPreferences.edit()
                    .putBoolean(IS_ACC_DELETED, it)
                    .apply()
            }
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    private companion object {
        private const val SHARED_PREF_KEY = "auth_shared_pref"
        private const val USER_ID = "user_id"
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val LOGIN_KEY = "login"
        private const val PASSWORD_KEY = "password"
        private const val TOKEN_EXPIRES_AT_KEY = "token_expires_at"
        private const val IS_ACC_DELETED = "is_acc_deleted"
    }

}
