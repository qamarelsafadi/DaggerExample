package net.qamar.daggerexample.data.models


class Resource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED, SUCCESS
    }

    companion object {
        fun <T> success(data: T?): Resource<T?> {
            return Resource(AuthStatus.SUCCESS, data, null)
        }

        fun <T> success(data: List<T>?): Resource<List<T?>> {
            return Resource(AuthStatus.SUCCESS, data, null)
        }

        fun <T> authenticated(data: T?): Resource<T> {
            return Resource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(AuthStatus.LOADING, data, null)
        }

        fun <T> loading(data: List<T>?): Resource<List<T>> {
            return Resource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): Resource<T> {
            return Resource<T>(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}