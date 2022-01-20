package com.hansoft.tryglidesixth

interface NetworkInterface {
    /**
     * Get string by username password
     * @param endpoint rest url
     * @param username username
     * @param password password
     * @return String
     */
    fun getString(endpoint: String?, username: String?, password: String?): String?

    /**
     * Get string by bear token
     * @param endpoint rest url
     * @param token bearer token
     * @return String
     */
    fun getString(endpoint: String?, token: String?): String?

    /**
     * Get string by bear token
     * @param url rest url
     * @return String
     */
    fun getString(url: String?): String?
}