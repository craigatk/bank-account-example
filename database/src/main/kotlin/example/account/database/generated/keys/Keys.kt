/*
 * This file is generated by jOOQ.
 */
package example.account.database.generated.keys


import example.account.database.generated.tables.AccountHolder
import example.account.database.generated.tables.AccountTransaction
import example.account.database.generated.tables.BankAccount
import example.account.database.generated.tables.records.AccountHolderRecord
import example.account.database.generated.tables.records.AccountTransactionRecord
import example.account.database.generated.tables.records.BankAccountRecord

import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal



// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val ACCOUNT_HOLDER_PKEY: UniqueKey<AccountHolderRecord> = Internal.createUniqueKey(AccountHolder.ACCOUNT_HOLDER, DSL.name("account_holder_pkey"), arrayOf(AccountHolder.ACCOUNT_HOLDER.ID), true)
val ACCOUNT_TRANSACTION_PKEY: UniqueKey<AccountTransactionRecord> = Internal.createUniqueKey(AccountTransaction.ACCOUNT_TRANSACTION, DSL.name("account_transaction_pkey"), arrayOf(AccountTransaction.ACCOUNT_TRANSACTION.ID), true)
val BANK_ACCOUNT_PKEY: UniqueKey<BankAccountRecord> = Internal.createUniqueKey(BankAccount.BANK_ACCOUNT, DSL.name("bank_account_pkey"), arrayOf(BankAccount.BANK_ACCOUNT.ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val ACCOUNT_TRANSACTION__ACCOUNT_TRANSACTION_BANK_ACCOUNT_ID_FKEY: ForeignKey<AccountTransactionRecord, BankAccountRecord> = Internal.createForeignKey(AccountTransaction.ACCOUNT_TRANSACTION, DSL.name("account_transaction_bank_account_id_fkey"), arrayOf(AccountTransaction.ACCOUNT_TRANSACTION.BANK_ACCOUNT_ID), example.account.database.generated.keys.BANK_ACCOUNT_PKEY, arrayOf(BankAccount.BANK_ACCOUNT.ID), true)
val BANK_ACCOUNT__BANK_ACCOUNT_ACCOUNT_HOLDER_ID_FKEY: ForeignKey<BankAccountRecord, AccountHolderRecord> = Internal.createForeignKey(BankAccount.BANK_ACCOUNT, DSL.name("bank_account_account_holder_id_fkey"), arrayOf(BankAccount.BANK_ACCOUNT.ACCOUNT_HOLDER_ID), example.account.database.generated.keys.ACCOUNT_HOLDER_PKEY, arrayOf(AccountHolder.ACCOUNT_HOLDER.ID), true)