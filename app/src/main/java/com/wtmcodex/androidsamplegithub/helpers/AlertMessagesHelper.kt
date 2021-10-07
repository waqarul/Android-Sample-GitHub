package com.wtmcodex.androidsamplegithub.helpers

import com.wtmcodex.androidsamplegithub.R
import com.wtmcodex.androidsamplegithub.core.Domain
import com.wtmcodex.androidsamplegithub.core.data.enums.ErrorType
import com.wtmcodex.androidsamplegithub.core.data.model.AlertModel
import com.wtmcodex.androidsamplegithub.core.data.model.AlertModel.OnAlertClickListener
import retrofit2.HttpException
import java.io.IOException

object AlertMessagesHelper {
    fun getNoInternetConnectionAlertModel(
        listener: OnAlertClickListener?
    ): AlertModel {
        val context = Domain.instance.context
        return AlertModel(
            context.getString(R.string.title_no_internet_connection),
            context.getString(R.string.message_no_internet_connection),
            context.getString(R.string.alert_ok_label),
            null,
            listener,
            ErrorType.NO_INTERNET_CONNECTION
        )
    }

    fun getAlertMessageFromException(
        exception: Throwable,
        listener: OnAlertClickListener?
    ): AlertModel {
        val context = Domain.instance.context
        var errorType = ErrorType.UNKNOWN_ERROR
        var errorMessage = context.getString(R.string.message_error_in_request)
        if (exception is HttpException) {
            errorType = ErrorType.fromString(
                exception.code()
            )
            errorMessage = ErrorType.getMessageForType(errorType, context)
        } else if (exception is IOException) {
            errorType = ErrorType.UNKNOWN_ERROR
            errorMessage = exception.getLocalizedMessage()
        } else if (exception is IOException) {
            errorType = ErrorType.UNKNOWN_ERROR
            errorMessage = context.getString(R.string.message_error_in_request)
        }
        return AlertModel(
            context.getString(R.string.title_error_in_request),
            errorMessage,
            context.getString(R.string.alert_ok_label),
            null,
            listener,
            errorType
        )
    }

    fun getAlertMessageFromError(
        listener: OnAlertClickListener?
    ): AlertModel {
        val context = Domain.instance.context
        val errorType = ErrorType.UNKNOWN_ERROR
        val errorMessage = context.getString(R.string.message_error_in_request)

        return AlertModel(
            context.getString(R.string.title_error_in_request),
            errorMessage,
            context.getString(R.string.alert_ok_label),
            null,
            listener,
            errorType
        )
    }
}