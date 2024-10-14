package io.github.joaogouveia89.checkmarket.itemAdd.data.usecase

class LevenshteinDistance(
    private val s1: String,
    private val s2: String
) {

    private val s1Length = s1.length
    private val s2Length = s2.length

    private fun levenshteinDistance(): Int {

        val dp = Array(s1Length + 1) { IntArray(s2Length + 1) }

        for (i in 0..s1Length) {
            dp[i][0] = i
        }
        for (j in 0..s2Length) {
            dp[0][j] = j
        }

        for (i in 1..s1Length) {
            for (j in 1..s2Length) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1

                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,    // Deletion
                    dp[i][j - 1] + 1,    // Insertion
                    dp[i - 1][j - 1] + cost  // Substitution
                )
            }
        }

        return dp[s1Length][s2Length]
    }

    fun similarity(): Float {
        val distance = levenshteinDistance()
        val maxLength = maxOf(s1Length, s2Length)

        return if (maxLength == 0) 1.0f else 1.0f - (distance.toFloat() / maxLength.toFloat())
    }
}