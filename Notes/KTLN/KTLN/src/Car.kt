package mypackage


//  Classes are blueprints of custom Objects



class Car(
    var brand: String,
    var model: String
) {
    var engine_cc: Int = 0
    var transmission: String = "Unknown"

    fun describe() {
        println("$brand, $model")
    }




}