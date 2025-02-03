package com.jadermunoz.myretail.utils

data class SellerData(val name: String, val sales: Float)

object ChartUtils {
    fun prepareSalesData(): Pair<List<String>, List<Float>> {
        val sellers = listOf(
            SellerData("Rick Sanchez", 100f),
            SellerData("Morty Smith", 80f),
            SellerData("Summer Smith", 120f),
            SellerData("Beth Smith", 110f),
            SellerData("Jerry Smith", 70f),
            SellerData("Mr. Meeseeks", 90f)
        )
        val names = sellers.map { it.name }
        val sales = sellers.map { it.sales }
        return Pair(names, sales)
    }
}
