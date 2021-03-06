package org.kethereum.functions

import org.kethereum.functions.rlp.RLPList
import org.kethereum.functions.rlp.encode
import org.kethereum.functions.rlp.toRLP
import org.kethereum.model.SignatureData
import org.kethereum.model.SignedTransaction
import org.kethereum.model.Transaction
import org.komputing.khex.extensions.hexToByteArray
import org.komputing.khex.model.HexString

fun Transaction.toRLPList(signature: SignatureData?) = RLPList(listOf(
        nonce!!.toRLP(),
        gasPrice!!.toRLP(),
        gasLimit!!.toRLP(),
        HexString(to?.hex?.let { it } ?: "0x").hexToByteArray().toRLP(),
        value!!.toRLP(),
        input.toRLP()
).let {

    if (signature == null) {
        it
    } else {
        it.plus(listOf(
                signature.v.toRLP(),
                signature.r.toRLP(),
                signature.s.toRLP()
        ))
    }
})

fun Transaction.encodeRLP(signature: SignatureData? = null) = toRLPList(signature).encode()
fun SignedTransaction.encodeRLP() = transaction.toRLPList(signatureData).encode()