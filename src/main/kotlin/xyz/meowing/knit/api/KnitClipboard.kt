package xyz.meowing.knit.api

/**
 * @author: Deftu
 */
object KnitClipboard {
    @JvmStatic
    var string: String
        get() {
            return KnitClient.client.keyboardHandler.clipboard
        }
        set(value) {
            KnitClient.client.keyboardHandler.clipboard = value
        }
}