package com.aktham.dungeon

fun main(){
    println("_".repeat(25))
    println("Hello there!\nwelcome to this kotlin dungeon game!\nplease enter your name!: ")
    println("_".repeat(25))
    var name = readLine()
    if(!name.isNullOrBlank()){
        println("\nstarting the game!")
    } else {
        println("bruh invalid option")
        main()
    }
    var player = plr(name, 100)
    game().Game(player)
}