package com.aktham.dungeon
import java.util.concurrent.TimeUnit

data class plr(val name:String?, var health:Int)

class game{
    var rand_ch: (Int,Int) -> Int = {min, max -> (min..max).random()}
    var enemy_list = listOf<String>("spider", "cyto", "goblin", "enzo", "el0xren", "boss cyto", "boss el0xren", "boss enzo", "boss goblin", "boss spider")
    val enemy = enemy_list.random()
    var hp = if("boss" in enemy) (150..200).random() else 100
    var hp_packs = (1..3).random()
    fun Game(player:plr) {
        var enemy = plr(enemy, hp)
        println("Welcome ${player.name} to this very epic dungeon game")
        TimeUnit.SECONDS.sleep(2L)
        println("\nOH SHIT A WILD ${enemy.name?.toUpperCase()} APPEARS!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nGET READY FOR THE FIGHT ${player.name?.toUpperCase()}")
        TimeUnit.SECONDS.sleep(3L)
        fun attack():String? {
            println("\n\n${enemy.name}'s health: ${enemy.health}, ${player.name}'s health\n(\"choose from the numbers below\")")
            println("1.do a basic attack\n2.do a special attack  \"(1/6) chace of it working\"\n3.heal up (current health packs = $hp_packs)\n: ")
            var ch = readLine()
            return ch
        }

        // could've used a when statement but i'm too lazy to edit it now
        do {
        TimeUnit.SECONDS.sleep(2L)
        var choice = attack()
        if(choice == "1") {
            TimeUnit.SECONDS.sleep(1L)
            enemy.health -= 15
            println("\ndoing a basic attack of 15!\n${enemy.name}'s health is now ${enemy.health}")
            player.health -= rand_ch(10, 25)
            println("${enemy.name} attacks!\nyour health is now ${player.health}!")
        } else if(choice == "2") {
            TimeUnit.SECONDS.sleep(1L)
            println("trying a special attack!")
            TimeUnit.SECONDS.sleep(1L)
            if ((1..6).random() == 6) {
                println("special attack worked!\n decreasing the enemy's health by 35!")
                enemy.health -= 35
            } else {
                TimeUnit.SECONDS.sleep(1L)
                println("Attack Failed!")
                player.health -= rand_ch(10, 25)
                println("${enemy.name} attacks!\n your health is now ${player.health}!")
            }
            } else if(choice == "3") {
            TimeUnit.SECONDS.sleep(1L)
            if(hp_packs > 0){
                println("Healing up by 25!")
                TimeUnit.SECONDS.sleep(1L)
                player.health += 25
                hp_packs -= 1
            } else {
                println("you don't have enough health packs!")
            }
        } else {
            println("Invalid option!")
            TimeUnit.SECONDS.sleep(2L)
            attack()
        }
        } while(enemy.health > 0 || player.health > 0)
        if(player.health == 0){
            println("you have lost!")
        } else {
            println("you won!")
        }
    }

}