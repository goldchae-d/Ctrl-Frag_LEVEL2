package com.example.camerax_mlkit.security

object QrRawWhitelist {

    // 🔒 LV2: raw 문자열 → 매장 locationId 매핑
    //  - 스캔된 raw를 키로 조회해서 이 QR이 어느 매장 소속인지 판별
    private val map: MutableMap<String, String> = linkedMapOf(
        // ===== A 매장 (store_duksung_a) =====
        "https://pay.naver.com/remit/qr/inflow?v=1&a=1002858310954&c=020&d=317bb0795ee5eb20e48760734b5d7372"
                to "store_duksung_a",
        "https://qr.kakaopay.com/281006011000013813839564"
                to "store_duksung_a",

        // ===== B 매장 (store_duksung_b) =====
        "https://pay.naver.com/remit/qr/inflow?v=1&a=110290521049&c=088&d=d268ef57c81cc46b34a51e96ff0497cb"
                to "store_duksung_b",
        "https://qr.kakaopay.com/281006011000077232921124"
                to "store_duksung_b",
    )

    /** 조회: 이 raw가 어느 매장 소속인지 반환 (없으면 null) */
    fun locationOf(raw: String): String? = map[raw.trim()]

    /** 등록/갱신: 런타임에서 캡처한 raw를 특정 매장에 바인딩(시연 편의용) */
    fun registerRawForStore(raw: String, locationId: String) {
        map[raw.trim()] = locationId
    }

    /** (선택) 일괄 등록 */
    fun registerAll(pairs: List<Pair<String, String>>) {
        pairs.forEach { (raw, loc) -> registerRawForStore(raw, loc) }
    }

    // ✅ 추가: LV2 헬퍼 — 이 raw가 현재 컨텍스트 locationId에서 허용되는지
    fun isAllowedAt(raw: String, ctxLocationId: String?): Boolean {
        val ctx  = ctxLocationId?.trim()?.lowercase() ?: return false
        val qrId = locationOf(raw)?.trim()?.lowercase() ?: return false
        return qrId == ctx
    }
}
