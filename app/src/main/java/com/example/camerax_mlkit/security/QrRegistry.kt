object QrRegistry {
    data class QrMeta(val provider: String, val qrLocationId: String)

    private val mapByKey = mapOf(
        "npay_qr_a.png"     to QrMeta("NPay",     "store_duksung_a"),
        "kakaopay_qr_a.png" to QrMeta("KakaoPay", "store_duksung_a"),
        "npay_qr_b.png"     to QrMeta("NPay",     "store_duksung_b"),
        "kakaopay_qr_b.png" to QrMeta("KakaoPay", "store_duksung_b")
    )

    fun metaOf(qrKey: String): QrMeta? = mapByKey[qrKey]
}