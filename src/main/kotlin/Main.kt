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
    for (packageNumber in 1..50) {

        if ( ( (packageNumber%5) ==0) &&( (packageNumber%3) ==0)) { println("PriorityExpress") }
        else if ((packageNumber%5)==0) { println("Freight") }
        else if ((packageNumber%3)==0) { println("Express") }
        else println("$packageNumber")
    }
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")



                        println("--------------------- Problem 2 -----------------------------")


    val originalRoute = "HubA - HubB - HubC"
    val hubsList = originalRoute.split(" - ")
    var reversedRoute = ""
    var currentIndex = hubsList.size - 1

    while (currentIndex >= 0) {
        reversedRoute += hubsList[currentIndex]

        if (currentIndex != 0) {
            reversedRoute += " - "
        }
        currentIndex--
    }
    println(reversedRoute)
    println(originalRoute)


println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")

                    println("--------------------- Problem 3 -----------------------------")

    // Problem 3 (Max Weight Filter): Identify the highest valid
    // package weight in a decimal array containing positive
    // numbers and invalid -1.0 tags. Avoid helper sorting  functions.


    val weights = doubleArrayOf(14.5,19.5, 28.0, 100.0,29.7,-1.0,72.8)
    var maxWeight = weights[0]
    for (currentWeight in weights.indices) {
        if (maxWeight < weights[currentWeight]) {
            maxWeight = weights[currentWeight]
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
    val LowercaseText = text.lowercase()
    println(LowercaseText)

    var leftIndex = 0
    var rightIndex = LowercaseText.length - 1
    var isPalindrome = true

    while (leftIndex < rightIndex) {
        if (LowercaseText[leftIndex] != LowercaseText[rightIndex]) {
            println("Not Palindrome")
            isPalindrome = false
            break
        }else{
            leftIndex++
            rightIndex--
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
    val targetID = 98
    var startIndex = 0
    var endIndex = TrackingIds.size - 1
    var countStep = 1

    while (startIndex <= endIndex) {
        val  middleIndex = ( startIndex + (endIndex -startIndex) /  2 )
        val currentValue = TrackingIds[middleIndex]
        println( " Step $countStep ")
        println("Current search range (indices): from $startIndex to $endIndex")
        println("In this step $countStep, the middle value is $currentValue")
        if (currentValue == targetID) {
            println(" target = $targetID in index number $middleIndex" )
            break
        } else if (currentValue > targetID) {
            println(" $currentValue is greater than $targetID. Cutting the right half ")
            endIndex = middleIndex - 1
        }   else {
            println(" $currentValue is smaller than $targetID. Cutting the left half")
            startIndex = middleIndex +1
        }
        countStep ++
    }
        println("Big O Notation is: 0(log N)")


println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")
println("-----------------------------------------------------------------------------------------------------------")



}