package tictactoe

import java.lang.NumberFormatException

var mutList3D = mutableListOf<MutableList<Char>>()
var movesCounter = 0
var turnCounter = 0
var user = 'X'
var isThereWinner = false


fun main() {

    generateGrid()

    while (!isThereWinner) {

        showGrid()
        validateCoordinates()
        calculateWinner()

    }

}


fun validateCoordinates() {

    val coordinates = readln().split(" ")
    var isValid = true

    try {

        val cell1 = coordinates[0].toInt()
        val cell2 = coordinates[1].toInt()

        if (cell1 !in 1..3 || cell2 !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            isValid = false

        } else if (mutList3D[cell1 - 1][cell2 - 1] != ' ') {
            println("This cell is occupied! Choose another one!")
            isValid = false

        } else {

            updateGrid(cell1 - 1, cell2 - 1)

        }

    } catch (nfe : NumberFormatException){
        println("You should enter numbers!")
        isValid = false

    } catch (e: Exception) {
        println(e.message)
        isValid = false
    }

    if (!isValid){
        validateCoordinates()
    }

}



fun updateGrid(cell1: Int, cell2: Int){

    mutList3D[cell1][cell2] = user

    turnCounter = movesCounter

    movesCounter++

    if (user == 'X' && movesCounter == turnCounter + 1){
        user = 'O'

    } else {
        user = 'X'

    }

}



fun generateGrid(){

    val grid = "         "

    mutList3D = mutableListOf(
        mutableListOf<Char>(grid[0], grid[1], grid[2]),
        mutableListOf<Char>(grid[3], grid[4], grid[5]),
        mutableListOf<Char>(grid[6], grid[7], grid[8]),
    )
}



fun showGrid(){

    println("---------")

    for (ch in mutList3D) {

        print("| ")
        print(ch.joinToString(" "))
        println(" |")

    }

    println("---------")
}



fun calculateWinner(){

    var winner = ' '

    //Evaluate horizontal winner
    if (mutList3D[0][0] == mutList3D[0][1] && mutList3D[0][1] == mutList3D[0][2]){
        winner = mutList3D[0][0]
    } else if (mutList3D[1][0] == mutList3D[1][1] && mutList3D[1][1] == mutList3D[1][2]){
        winner = mutList3D[1][0]
    } else if (mutList3D[2][0] == mutList3D[2][1] && mutList3D[2][1] == mutList3D[2][2]){
        winner = mutList3D[2][0]
    }

    //Evaluate vertical winner
    if (mutList3D[0][0] == mutList3D[1][0] && mutList3D[1][0] == mutList3D[2][0]){
        winner = mutList3D[0][0]

    } else if (mutList3D[0][1] == mutList3D[1][1] && mutList3D[1][1] == mutList3D[2][1]){
        winner = mutList3D[0][1]

    } else if (mutList3D[0][2] == mutList3D[1][2] && mutList3D[1][2] == mutList3D[2][2]){
        winner = mutList3D[0][2]
    }

    //Evaluate diagonal winner
    if (mutList3D[0][0] == mutList3D[1][1] && mutList3D[1][1] == mutList3D[2][2]){
        winner = mutList3D[0][0]
    } else if (mutList3D[0][2] == mutList3D[1][1] && mutList3D[1][1] == mutList3D[2][0]){
        winner = mutList3D[0][2]
    }

    //Evaluate final Winner
    if (winner == 'X' || winner == 'O'){
        isThereWinner = true
        showGrid()
        println("$winner wins")
    } else if (movesCounter == 9){
        isThereWinner = true
        showGrid()
        println("Draw")
    }


}