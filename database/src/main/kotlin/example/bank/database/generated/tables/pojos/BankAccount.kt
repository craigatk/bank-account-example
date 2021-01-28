/*
 * This file is generated by jOOQ.
 */
package example.bank.database.generated.tables.pojos


import java.io.Serializable


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class BankAccount(
    var id: Long? = null, 
    var accountHolderId: Long? = null, 
    var accountType: String? = null
): Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other === null)
            return false
        if (this::class != other::class)
            return false
        val o: BankAccount = other as BankAccount
        if (id === null) {
            if (o.id !== null)
                return false
        }
        else if (id != o.id)
            return false
        if (accountHolderId === null) {
            if (o.accountHolderId !== null)
                return false
        }
        else if (accountHolderId != o.accountHolderId)
            return false
        if (accountType === null) {
            if (o.accountType !== null)
                return false
        }
        else if (accountType != o.accountType)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.id === null) 0 else this.id.hashCode())
        result = prime * result + (if (this.accountHolderId === null) 0 else this.accountHolderId.hashCode())
        result = prime * result + (if (this.accountType === null) 0 else this.accountType.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("BankAccount (")

        sb.append(id)
        sb.append(", ").append(accountHolderId)
        sb.append(", ").append(accountType)

        sb.append(")")
        return sb.toString()
    }
}