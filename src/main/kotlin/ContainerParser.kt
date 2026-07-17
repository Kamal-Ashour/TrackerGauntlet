package org.bytebloom

/*
 * =================================================================================
 *  How this code works in JVM Memory (A simple, human-friendly explanation)
 * =================================================================================
 *
 *  Since we are using "Recursion" instead of traditional loops (like for/while),
 *  the computer manages memory in a way that is very similar to a "queue of workers"
 *  passing a bag to one another:
 *
 *  1. Memory Boxes (Stack Frames):
 *     Every time the function calls itself to process a new character, the JVM opens
 *     a small temporary box in memory called a "Stack Frame". This box keeps track
 *     of the current state: (the current index we are reading, the word we are
 *     building, and the bracket depth).
 *
 *  2. Stacking Up (Push Phase):
 *     These memory boxes keep piling up on top of each other (the Call Stack) with
 *     each new recursive call. For example, with the input "P K":
 *     - Box 1 (index=0, text=""): Reads 'P', saves it, then calls Box 2 with "P".
 *     - Box 2 (index=1, text="P"): Sees a space, ignores it, then calls Box 3 with "P".
 *     - Box 3 (index=2, text="P"): Reads 'K', merges it to make "PK", then calls Box 4 with "PK".
 *     At this exact moment, all boxes are open, waiting for the next one to finish.
 *
 *  3. Wrapping Up & Releasing (Base Case & Pop Phase):
 *     Once we reach the end of the text (index=3), the last box triggers our "Base Case".
 *     It checks the built word ("PK") to see if it's a valid package (starts with "PKG-").
 *     Since it's not, it returns an empty list [] to the box below it, and then this last
 *     box instantly disappears from memory (Pop).
 *     This "returning and disappearing" chain reaction continues downward until the very
 *     first box finishes, giving us the final result.
 *
 *  4. Memory Footprint (Space Complexity):
 *     The maximum number of open boxes in memory at any given time depends directly
 *     on how long the input text is. A longer text means more boxes stacked at once,
 *     which gives us a Space Complexity of O(N).
 *
 * =================================================================================
 */

class StructuralMismatchException(message: String) : RuntimeException(message)
/**
 * Parses a structured container text to extract all valid package identifiers.
 *
 * This function recursively analyzes a string representation of hierarchical containers
 * (like Crates or Boxes) to find and isolate items starting with the "PKG-" prefix,
 * while verifying the structural integrity of matching brackets.
 *
 * @param input The raw text string representing the container structure to be parsed.
 * @param index The current character position being processed in the string (used for recursion).
 * @param currentWord The accumulated characters of the token currently being formed.
 * @param bracketDepth The current nesting level, ensuring all opened brackets are properly closed.
 * @param resultList A mutable list used to accumulate and return the extracted package identifiers.
 * @return A [List] of [String] containing all successfully extracted packages (e.g., "PKG-101").
 * @throws StructuralMismatchException If brackets are unbalanced (e.g., unexpected closing bracket or unclosed open bracket).
 */
fun parseContainerText(input: String , index : Int , currentText : String , bracketDepth: Int , extractedPackages: MutableList<String> ): List<String> {
    if(index >= input.length) {
        if (bracketDepth !=0 ){
            throw StructuralMismatchException("Structural Mismatch: Container opened but never closed!")
        }
        if (currentText.startsWith("PKG-")){
            extractedPackages.add(currentText)
        }
        return extractedPackages

    }
    val currentChar = input[index]

    if (currentChar == ' ') {
        return parseContainerText(input, index + 1, currentText , bracketDepth,extractedPackages)
    }else if (currentChar == '['){
        return parseContainerText(input, index + 1, "" ,bracketDepth+1, extractedPackages)
    }else if (currentChar == ']') {
        if (bracketDepth <=0 ){
            throw StructuralMismatchException("Structural Mismatch: Unexpected closing bracket ']'")
        }
        if (currentText.startsWith("PKG-")) {
            extractedPackages.add(currentText)
        }
        return parseContainerText(input, index + 1, "", bracketDepth-1,extractedPackages)
    } else if (currentChar == ',') {
        if (currentText.startsWith("PKG-")) {
            extractedPackages.add(currentText)
        }
        return parseContainerText(input, index + 1, "",bracketDepth, extractedPackages)
    }   else {
        return parseContainerText(input, index + 1, currentText+currentChar ,bracketDepth,extractedPackages)

    }
}


fun main(){

    val dirtyText = " P K G - 1 0 1 "
    val cleanedDirtyTextPackages = parseContainerText(dirtyText, 0, "",0,mutableListOf())
    println(cleanedDirtyTextPackages)


    val boxClassPackages = parseContainerText(" B o x ", 0, "",0, mutableListOf())
    println("$boxClassPackages")

    val testInput = "Crate [ Box [ ] , PKG-202 ]"
    val finalResult = parseContainerText(testInput, 0, "",0, mutableListOf())
    println(finalResult)

    val doubleCommaInput = "Box[PKG-101,,PKG-102]"
    val doubleCommaPackages = parseContainerText(doubleCommaInput, 0, "",0, mutableListOf())
    println("$doubleCommaPackages")

    try {
        parseContainerText("PKG-101]", 0, "", 0, mutableListOf())
    } catch (e: StructuralMismatchException) {
        println("${e.message}")
    }

    try {
        parseContainerText("Crate[PKG-101", 0, "", 0, mutableListOf())
    } catch (e: StructuralMismatchException) {
        println("${e.message}")
    }
}