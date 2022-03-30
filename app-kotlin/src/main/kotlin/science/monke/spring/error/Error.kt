package science.monke.spring.error

enum class Error(val id: Int, val message: String) {

    ORDER_NOT_FOUND(1, "Order could not be found")
}