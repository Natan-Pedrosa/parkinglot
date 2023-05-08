
const val NO_CAR = "No_CAR"
const val NO_HASH_CODE = "NO_HASH_CODE"
const val PARK_FREE = false
const val PARK_NOT_FREE = true

class Parking {
    val parkHashCode = mutableListOf<String>()
    val parkColorCar = mutableListOf<String>()
    val parkLot = mutableListOf<Boolean>()
    val parkCarFree = mutableListOf<Int>()
    var created = false
}
fun main() {

    start()
}

fun start() {
    val parkLot = Parking()

    while (true) {
        val input = readln().split(" ")
        val command = input[0]
        val isExit = showMenu(command, input, parkLot)
        if (isExit) return
    }
}

fun showMenu(command: String, input: List<String>, parking: Parking):Boolean {

    var isExit = false
    when(command){
        "create" -> {
                val size = input[1].toInt()
                createParking(size, parking)
        }
        "park" ->  if(parking.created) addPark(parking, input) else println("Sorry, a parking lot has not been created.")
        "leave" -> if(parking.created) leavePark(parking, input) else println("Sorry, a parking lot has not been created.")
        "status" -> if(parking.created) showStatus(parking) else println("Sorry, a parking lot has not been created.")
        "reg_by_color" -> if(parking.created) printRegisterCarByColor(parking, input[1]) else println("Sorry, a parking lot has not been created.")
        "spot_by_color" -> if(parking.created) printSpotParkingByColor(parking, input[1]) else println("Sorry, a parking lot has not been created.")
        "spot_by_reg" -> if(parking.created) printSpotParkingByReg(parking, input[1]) else println("Sorry, a parking lot has not been created.")
        "exit" -> isExit = true
    }
    return isExit
}

fun createParking(sizeParking: Int, parking: Parking) {
    if(parking.created){
        parking.parkLot.clear()
        parking.parkColorCar.clear()
        parking.parkHashCode.clear()
        parking.parkCarFree.clear()
    }

    for(index in 0 until sizeParking) {
        parking.parkLot.add(false)
        parking.parkCarFree.add(index)
        parking.parkColorCar.add(NO_CAR)
        parking.parkHashCode.add(NO_HASH_CODE)
    }
    println("Created a parking lot with $sizeParking spots.")
    parking.created = true
}
fun addPark(parking: Parking, input: List<String>){
    if(parking.parkCarFree.isNotEmpty()){
        val index = parking.parkCarFree[0]
        parking.parkLot[index] = true
        parking.parkHashCode[index] = input[1]
        parking.parkColorCar[index] = input[2]
        parking.parkCarFree.removeAt(0)

        println("${parking.parkColorCar[index]} car parked in spot ${index + 1}.")
    }else{
        println("Sorry, the parking lot is full.")
    }
}

fun leavePark(parking: Parking, input: List<String>){
    val index = input[1].toInt() - 1
    if(parking.parkLot[index] == PARK_NOT_FREE){
        parking.parkLot[index] = PARK_FREE
        parking.parkHashCode[index] = NO_HASH_CODE
        parking.parkColorCar[index] = NO_CAR
        parking.parkCarFree.add(index)
        parking.parkCarFree.sort()

        println("Spot ${index + 1} is free.")
    }else {
        println("There is no car in spot ${index + 1}")
    }
}
fun showStatus(parking: Parking) {
    var isEmpity = true
    for(index in parking.parkLot.indices) {
       if(parking.parkLot[index] == true) {
           println("${index + 1} ${parking.parkHashCode[index]} ${parking.parkColorCar[index]}")
           isEmpity = false
       }
    }
    if(isEmpity) println("Parking lot is empty.")
}

fun printRegisterCarByColor(parking: Parking, color: String) {
    val register = mutableListOf<String>()

    for(index in parking.parkColorCar.indices) {
        val parkingColor = parking.parkColorCar[index].lowercase()
        if(color.lowercase() == parkingColor) register.add(parking.parkHashCode[index])
    }
    if (register.isNotEmpty()) {
        for(index in register.indices) {
            when(index){
                0 -> print(register[index])
                else -> print(", ${register[index]}")
            }
        }
        println()
    }else println("No cars with color $color were found.")
}


fun printSpotParkingByReg(parking: Parking, reg: String) {
    val spot = mutableListOf<Int>()

    for (index in parking.parkHashCode.indices) {

        if (reg == parking.parkHashCode[index]) spot.add(index + 1)
    }
    if (spot.isNotEmpty()) {
        for(index in spot.indices) {
            when(index){
                0 -> print(spot[index])
                else -> print(", ${spot[index]}")
            }
        }
        println()
    }else println("No cars with registration number $reg were found.")
}

fun printSpotParkingByColor(parking: Parking, color: String) {
    val spot = mutableListOf<Int>()

    for(index in parking.parkColorCar.indices) {
        val parkingColor = parking.parkColorCar[index].lowercase()
        if(color.lowercase() == parkingColor) spot.add(index + 1)
    }
    if (spot.isNotEmpty()) {
        for(index in spot.indices) {
            when(index){
                0 -> print(spot[index])
                else -> print(", ${spot[index]}")
            }
        }
        println()
    }else println("No cars with color $color were found.")
}
