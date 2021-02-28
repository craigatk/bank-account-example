CREATE TABLE account_holder(
   id         BIGSERIAL PRIMARY KEY,
   user_name  TEXT UNIQUE NOT NULL,
   first_name TEXT,
   last_name  TEXT
);
CREATE INDEX account_holder_user_name_idx on account_holder(user_name);

CREATE TYPE bank_account_type AS ENUM ('CHECKING', 'SAVINGS');
CREATE TABLE bank_account(
 id                BIGSERIAL         PRIMARY KEY,
 account_holder_id BIGINT            REFERENCES account_holder ON DELETE CASCADE,
 account_type      bank_account_type NOT NULL
);
CREATE INDEX bank_account_account_holder_id_idx on bank_account(account_holder_id);

CREATE TYPE transaction_type AS ENUM ('DEPOSIT', 'WITHDRAWAL');
CREATE TABLE account_transaction(
    id               BIGSERIAL        PRIMARY KEY,
    bank_account_id  BIGINT           REFERENCES bank_account ON DELETE CASCADE,
    amount           NUMERIC(12, 2)   NOT NULL,
    transaction_type transaction_type NOT NULL
);
CREATE INDEX account_transaction_bank_account_id_idx on account_transaction(bank_account_id);
