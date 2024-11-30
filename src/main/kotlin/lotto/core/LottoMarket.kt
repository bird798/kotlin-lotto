package lotto.core

import lotto.core.constant.LottoConstants

object LottoMarket {
    fun purchase(purchaseAmount: String): Lottos {
        val purchasableCount = calculatePurchasableCount(purchaseAmount)
        val lottoList = issueLotto(purchasableCount)
        return Lottos(lottoList)
    }

    private fun calculatePurchasableCount(purchaseAmount: String): Int {
        val amount = purchaseAmount.toIntOrNull() ?: throw IllegalArgumentException("잘못된 금액이 입력되었습니다.")
        return amount / LottoConstants.LOTTO_PRICE
    }

    private fun issueLotto(count: Int): List<Lotto> {
        return List(count) { Lotto(generateNumbers()) }
    }

    private fun generateNumbers(): List<LottoNumber> =
        (LottoNumber.LOTTO_NUMBER_MIN..LottoNumber.LOTTO_NUMBER_MAX).map { LottoNumber(it) }
            .shuffled()
            .take(LottoConstants.LOTTO_NUMBER_COUNT)
            .sortedBy { it.number }
}
