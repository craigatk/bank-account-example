CREATE TABLE account_holder(
   id         BIGSERIAL PRIMARY KEY,
   first_name TEXT,
   last_name  TEXT
);

CREATE TYPE bank_account_type AS ENUM ('CHECKING', 'SAVINGS');
CREATE TABLE bank_account(
 id                BIGSERIAL         PRIMARY KEY,
 account_holder_id BIGINT            REFERENCES account_holder ON DELETE CASCADE,
 account_type      bank_account_type NOT NULL
);

CREATE TYPE transaction_type AS ENUM ('DEPOSIT', 'WITHDRAWAL');
CREATE TABLE account_transaction(
    id               BIGSERIAL        PRIMARY KEY,
    bank_account_id  BIGINT           REFERENCES bank_account ON DELETE CASCADE,
    amount           NUMERIC(12, 2)   NOT NULL,
    transaction_type transaction_type NOT NULL
);
