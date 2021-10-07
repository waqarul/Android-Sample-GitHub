package com.wtmcodex.androidsamplegithub.core.data.enums

import android.content.Context
import com.wtmcodex.androidsamplegithub.R

//HTTP ERRORS
enum class ErrorType(val code: Int) {
    UNKNOWN_ERROR(0),
    NO_INTERNET_CONNECTION(100),
    RESOURCE_NOT_FOUND(404),
    UNAUTHORIZED(401), CANNOT_REACH_SERVER(900),
    INTERNAL_SERVER_ERROR(500), BAD_REQUEST(400);

    companion object {
        fun fromString(code: Int): ErrorType {
            for (type in values()) {
                if (type.code == code) {
                    return type
                }
            }
            return UNKNOWN_ERROR
        }

        fun getMessageForType(type: ErrorType, context: Context?): String {
            var message = context!!.getString(R.string.message_error_in_request)
            if (type == NO_INTERNET_CONNECTION) {
                message = context.getString(R.string.message_no_internet_connection)
            } else if (type == RESOURCE_NOT_FOUND) {
                message = context.getString(R.string.error_page_not_found)
            } else if (type == UNAUTHORIZED) {
                message = context.getString(R.string.error_unauthorized)
            } else if (type == INTERNAL_SERVER_ERROR) {
                message = context.getString(R.string.error_internal_server_error)
            } else if (type == BAD_REQUEST) {
                message = context.getString(R.string.message_error_in_request)
            }
            return message
        }
    }
}