package com.aktham.dungeon
import java.util.concurrent.TimeUnit

data class plr(val name:String?, var health:Int)

class game{
    var rand_ch: (Int,Int) -> Int = {min, max -> (min..max).random()}
    var enemy_list = listOf<String>("spider", "cyto", "goblin", "enzo", "el0xren", "boss cyto", "boss el0xren", "boss enzo", "boss goblin", "boss spider", "ahmed", "boss ahmed")
    val enemy = enemy_list.random()
    var hp = if("boss" in enemy) (150..200).random() else 100
    var hp_packs = (1..3).random()
    fun Game(player:plr) {
        var enemy = plr(enemy, hp)
        player.health = if(rand_ch(1,10) == 5) 125 else 100
        TimeUnit.SECONDS.sleep(1L)
        println("Welcome ${player.name} to this very epic dungeon game")
        TimeUnit.SECONDS.sleep(2L)
        if(player.health == 125) println("\nyou're lucky! you just got 25+ hp!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nOH SHIT A WILD ${enemy.name?.toUpperCase()} APPEARS!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nGET READY FOR THE FIGHT ${player.name?.toUpperCase()}")
        TimeUnit.SECONDS.sleep(3L)
        var special_attack = 6
        fun attack():String? {
            println("\n\n${enemy.name}'s health: ${enemy.health}, ${player.name}'s health: ${player.health}\n(\"choose from the numbers below\")")
            println("1.do a basic attack\n2.do a special attack \"has a (1/$special_attack) chance of working\"\n3.heal up (current health packs = $hp_packs)\n: ")
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
            if(enemy.health <= 0) {
                println("\nyou've managed to kill ${enemy.name}")
                break
            }
            println("_".repeat(30))
            println("\ndoing a basic attack of 15! ${enemy.name}'s health is now ${enemy.health}")
            println("${enemy.name} Attacks!")
            var rand = rand_ch(1,6)
            if(rand == 3){
                println("${enemy.name} missed!")
            } else {
                player.health -= rand_ch(10, 20)
                if(player.health <= 0) {
                    println("\n${enemy.name} has managed to kill you!")
                    break
                }
                println("${enemy.name} managed to hit you!\nyour health is now ${player.health}!")
            }
            println("_".repeat(30))
        } else if(choice == "2") {
            println("_".repeat(30))
            TimeUnit.SECONDS.sleep(1L)
            println("trying a special attack!")
            TimeUnit.SECONDS.sleep(1L)
            var worked:Boolean = false
            if ((1..special_attack).random() == 1) {
                worked = true
                println("special attack worked!\ndecreasing the enemy's health by 40!")
                enemy.health -= 40
                if(enemy.health <= 0) {
                    println("\nyou've managed to kill ${enemy.name}")
                    break
                }
            } else {
                TimeUnit.SECONDS.sleep(1L)
                println("Attack Failed!")
                worked = false
                println("${enemy.name} Attacks!")
                var rand = rand_ch(1,6)
                if(rand == 3){
                    println("${enemy.name} missed!")
                } else {
                    player.health -= rand_ch(10, 20)
                    if(player.health <= 0) {
                        println("\n${enemy.name} has managed to kill you!")
                        break
                    }
                    println("${enemy.name} managed to hit you!\nyour health is now ${player.health}!")
                }
                println("_".repeat(30))
            }
            when(worked){
                true -> special_attack = 6
                false -> special_attack -= 1
            }
            } else if(choice == "3") {
            println("_".repeat(30))
            TimeUnit.SECONDS.sleep(1L)
            if(hp_packs > 0){
                println("Healing up by 25!")
                TimeUnit.SECONDS.sleep(1L)
                player.health += 25
                hp_packs -= 1
            } else {
                println("you don't have enough health packs!")
            }
            println("_".repeat(30))
        } else {
            println("_".repeat(30))
            println("Invalid option!")
            TimeUnit.SECONDS.sleep(2L)
            attack()
            println("_".repeat(30))
        }
        } while(enemy.health > 0 && player.health > 0)
        if(player.health <= 0){
            println("you have lost!")
        } else if(enemy.health <= 0){
            println("you've won!")
        } else {
            println("you both have tied!")
        }
    }

}
