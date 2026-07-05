package org.bytebloom

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val name = "Kotlin"
//    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//    // to see how IntelliJ IDEA suggests fixing it.
//    println("Hello, " + name + "!")
//
//    for (i in 1..5) {
//        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//        println("i = $i")
//    }


//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
                         println("--------------------- Problem 1 -----------------------------")
    for (i in 1..50) {

        if (((i%5)==0)&&((i%3)==0)) { println("PriorityExpress") }
        else if ((i%5)==0) { println("Freight") }
        else if ((i%3)==0) { println("Express") }
        else println("$i")
    }
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")



                        println("--------------------- Problem 2 -----------------------------")


    val route = "HubA - HubB - HubC"
    val hubs = route.split(" - ")
    var reversedRoute = ""
    var l = hubs.size - 1

    while (l >= 0) {
        reversedRoute += hubs[l]

        if (l != 0) {
            reversedRoute += " - "
        }
        l--
    }
    println(reversedRoute)
    println(route)


println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")

                    println("--------------------- Problem 3 -----------------------------")

    // Problem 3 (Max Weight Filter): Identify the highest valid
    // package weight in a decimal array containing positive
    // numbers and invalid -1.0 tags. Avoid helper sorting  functions.


    val weights = doubleArrayOf(14.5,19.5, 28.0, 100.0,29.7,-1.0,72.8)
    var maxWeight = 0.0
    for (i in weights.indices) {
        if (maxWeight < weights[i]) {
            maxWeight = weights[i]
        }
    }
    println("Max Weight from the double array is  $maxWeight")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")


                        println("--------------------- Problem 4 -----------------------------")

// Problem 4 (Palindrome Transit Code): Write a case-insensitive check to
//determine if transit codes like 'TR808RT' are palindromes.
//
    val text = "TR808RT"
    val TextLowercase = text.lowercase()
    println(TextLowercase)

    var i = 0
    var j = TextLowercase.length - 1
    var isPalindrome = true

    while (i < j) {
        if (text[i] != text[j]) {
            println("Not Palindrome")
            isPalindrome = false
            break
        }else{
            i++
            j--
        }
    }
    if (isPalindrome) {
        println("Palindrome")
    }
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")

                      println("--------------------- Problem 5 -----------------------------")


    val TrackingIds = intArrayOf(28,37,48,66,76,98,125)
    val target = 98
    var low = 0
    var high = TrackingIds.size - 1
    var step = 1

    while (low <= high) {
        val  mid = ( low + (high - low) /  2 )
        val currentValue = TrackingIds[mid]
        println( " Step $step ")
        println("Current search range (indices): from $low to $high")
        println("In this step $step, the middle value is $currentValue")
        if (currentValue == target) {
            println(" target = $target in index number $mid" )
            break
        } else if (currentValue > target) {
            println(" $currentValue is greater than $target. Cutting the right half ")
            high = mid - 1
        }   else {
            println(" $currentValue is smaller than $target. Cutting the left half")
            low = mid +1
        }
        step ++
    }
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")



}