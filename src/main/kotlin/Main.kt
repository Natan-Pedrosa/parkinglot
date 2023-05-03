const val PARK_FREE = false
const val PARK_NOT_FREE = true

fun main() {

    start()
}

fun start() {
    val parkLot = MutableList(20){false}
    val parkinFree = MutableList(20){it}

    while (true) {
        val lineDigited = readln().split(" ")
        val command = lineDigited[0]
        val isExit = menu(command, parkLot,parkinFree, lineDigited)
        if (isExit) return
    }
}

fun menu(command: String, parkLot: MutableList<Boolean>, parkCarLeaved: MutableList<Int>, lineDigited: List<String>):Boolean {
    var isExit = false
    when(command){
        "park" ->  addPark(parkLot, parkCarLeaved, lineDigited)
        "leave" -> leavePark(parkLot, parkCarLeaved, lineDigited)
        "exit" -> isExit = true
    }
    return isExit
}

fun addPark(parkLot: MutableList<Boolean>, parkingFree: MutableList<Int>, lineDigited: List<String>){
    if(parkingFree.isNotEmpty()){
        val index = parkingFree[0]
        parkLot[index] = true
        parkingFree.removeAt(0)
        println("${lineDigited[2]} car parked in spot ${index + 1}.")
    }else{
        println("Sorry, the parking lot is full.")
    }
}

fun leavePark(parkLot: MutableList<Boolean>, parkingFree: MutableList<Int>, lineDigited: List<String>){
    val index = lineDigited[1].toInt() - 1
    if(parkLot[index] == PARK_NOT_FREE){
        parkLot[index] = PARK_FREE
        parkingFree.add(index)
        parkingFree.sort()
        println("Spot ${index + 1} is free.")
    }else {
        println("There is no car in spot ${index + 1}")
    }
}
