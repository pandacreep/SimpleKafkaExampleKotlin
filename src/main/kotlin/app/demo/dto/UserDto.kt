package app.demo.dto

data class UserDto(
    val age: Long,
    val name: String,
    var address: Address?,
)