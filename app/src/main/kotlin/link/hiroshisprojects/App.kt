package link.hiroshisprojects

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    // Choose word to guess
    val words = arrayOf(
        "the cat in the hat",
        "texas rangers",
        "belly dancing",
        "lana del rey",
        "formula one"
    )
    val randInd = Random().nextInt(words.size)
    val word = words[randInd]
    val wordArray = word.toCharArray()
    val guessed = ArrayList<Char>()

    // Banner
    println("*****")
    println("WELCOME TO HANGMAN")
    println("*****")

    while (!winCondition(guessed, wordArray)) {
        // Display
        for (i in 1..4)
            println()

        for (char in wordArray) {
            if (char.lowercaseChar() in guessed) {
                print("${char.uppercase()} ")
            } else {
                print("  ")
            }
        }
        println()
        for (char in wordArray) {
            if (char == ' ') {
                print("  ")
            } else {
                print("_ ")
            }
        }
        println()

        // Instructions
        println("Number of guesses: ${guessed.size}")
        println("Guess a letter:")
        val userInput = readlnOrNull()

        // Input validation
        if (!validateInput(userInput))
            continue

        val guess = userInput!!.toCharArray()[0].lowercaseChar()
        if (guess in guessed) {
            println("You've already guessed $userInput. Please try again.")
            continue
        }
        guessed += guess

    }

    println("You've guessed the word ${word.uppercase()}!")

}

fun winCondition(guessed: ArrayList<Char>, wordArray: CharArray): Boolean {
    // containsAll requires Set type, filter out spaces
    val compare = wordArray.toSet().filter { it != ' ' }

    if (guessed.containsAll(compare)) {
        return true
    }
    return false
}

fun validateInput(userInput: String?): Boolean {
    if (userInput.isNullOrEmpty()) {
        println("Nothing entered. Please try again.")
        return false
    }

    if (userInput.length > 1) {
        println("Too many characters entered. Please try again.")
       return false
    }

    if (!containsAlphabet(userInput)) {
        println("Please enter a alphabetic character.")
        return false
    }
    return true
}

fun containsAlphabet(userInput: String): Boolean {
    val alphabet = Regex("[a-zA-Z]")
    if (!alphabet.matches(userInput))
        return false
    return true
}
