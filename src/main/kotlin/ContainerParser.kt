package org.bytebloom

/*
 * =================================================================================
 *  How this Code Works in JVM Memory (A simple, human-friendly explanation)
 * =================================================================================
 *
 *  Since we are using "Recursion" to parse nested structures (Crates & Boxes),
 *  the JVM manages memory like a " queue of workers"
 *  passing items back and forth:
 *
 *  1. Memory Boxes (Stack Frames):
 *     Every time `parseRecursive` is called—either at start or upon hitting an
 *     opening bracket '['—the JVM creates a temporary memory box called a "Stack Frame".
 *     This box holds local variables for that specific scope:
 *     - `startIndex`: Where this box started parsing.
 *     - `index`: Where the reader head currently is.
 *     - `foundPackages`: List collecting package codes extracted at this level.
 *
 *  2. Stacking Up (Push Phase):
 *     As we dive deeper into nested brackets, new memory boxes stack on top of
 *     each other (The Call Stack). For example, with "Crate [ Box [ ] , PKG-202 ]":
 *     - Box 1 (startIndex=0): Parses "Crate", sees '[' at index 6 -> Pauses, calls Box 2.
 *     - Box 2 (startIndex=7): Parses "Box", sees '[' at index 12 -> Pauses, calls Box 3.
 *     - Box 3 (startIndex=13): Sees ']' immediately at index 15.
 *     At this exact moment, all 3 boxes are open in memory simultaneously!
 *
 *  3. Wrapping Up & Releasing (Base Case & Pop Phase):
 *     When a box hits a closing bracket ']' or reaches the end of the text:
 *     - Box 3 sees ']' -> Returns empty `ParseResult([], 16)` to Box 2, then instantly
 *       disappears from memory (Pop).
 *     - Box 2 resumes at index 16 -> Parses ", PKG-202", adds "PKG-202", sees ']' at 26 ->
 *       Returns `ParseResult(["PKG-202"], 27)` to Box 1, then disappears from memory (Pop).
 *     - Box 1 resumes at index 27 -> Loop ends -> Returns final list `["PKG-202"]` to main
 *       and vanishes.
 *
 *  4. Memory Footprint (Space Complexity):
 *     The maximum number of open boxes in memory at any moment equals the maximum
 *     nesting depth of brackets `[ [ [ ... ] ] ]`. In the worst-case scenario, this
 *     gives us a Space Complexity of O(N) where N is the length of the input.
 *
 * =================================================================================
 */

private const val OPEN_BRACKET = '['
private const val CLOSE_BRACKET = ']'
private const val COMMA = ','
private const val SPACE = ' '

private const val CRATE_PREFIX = "Crate"
private const val BOX_PREFIX = "Box"
private const val PKG_PREFIX = "PKG-"



data class ParseResult(
    val packages: List<String>,
    val nextIndex: Int
)

fun parseContainer(input: String): List<String> {
    val result = parseRecursive(input, startIndex = 0)
    return result.packages
}
private fun parseRecursive(input: String, startIndex: Int): ParseResult {
    val foundPackages = mutableListOf<String>()
    var index = startIndex

    while (index < input.length) {
        val currentChar = input[index]

        when {
            currentChar == CLOSE_BRACKET -> {
                return ParseResult( foundPackages, index + 1 )
            }
            currentChar == OPEN_BRACKET -> {
                val nestedResult = parseRecursive(input, index + 1)
                foundPackages.addAll(nestedResult.packages)
                index = nestedResult.nextIndex
            }
            input.startsWith(CRATE_PREFIX, index) -> {
                index += CRATE_PREFIX.length
            }
            input.startsWith(BOX_PREFIX, index) -> {
                index += BOX_PREFIX.length
            }
            currentChar == SPACE || currentChar == COMMA -> {
                index++
            }
            input.startsWith(PKG_PREFIX, index) -> {
                val packageCode = extractPackageCode(input, index)
                foundPackages.add(packageCode)
                index += packageCode.length
            }
            else -> {
                index++
            }
        }
    }

    return ParseResult( foundPackages, index)
}

private fun extractPackageCode(input: String, startIndex: Int): String {
    var endIndex = startIndex
    while (endIndex < input.length) {
        val char = input[endIndex]
        if (char == SPACE || char == COMMA || char == CLOSE_BRACKET || char == OPEN_BRACKET) {
            break
        }
        endIndex++
    }
    return input.substring(startIndex, endIndex)
}

fun main() {

    val test1 = "Crate [ Box [ ] , PKG-202 ]"
    println("Test 1 Result: ${parseContainer(test1)}")

    val test2 = "Crate [ PKG-101 , Box [ PRR-999 ] , PKG-303 ]"
    println("Test 2 Result: ${parseContainer(test2)}")

    val test3 = "Crate[PKG-101"
    println("Test 3 Result: ${parseContainer(test3)}")

    val test4 = "Box[PKG-101,,PKG-102]"
    println("Test 4 Result: ${parseContainer(test4)}")

    val test5 = "PKG-101]"
    println("Test 5 Result: ${parseContainer(test5)}")



}

