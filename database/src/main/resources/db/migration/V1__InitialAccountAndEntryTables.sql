CREATE TABLE account_holder(
   id         BIGSERIAL PRIMARY KEY,
   first_name TEXT,
   last_name  TEXT
);

CREATE TABLE bank_account(
 id                BIGSERIAL PRIMARY KEY,
 account_holder_id BIGINT    REFERENCES account_holder ON DELETE CASCADE,
 account_type      TEXT      NOT NULL
);

CREATE TABLE account_transaction(
    id               BIGSERIAL      PRIMARY KEY,
    bank_account_id  BIGINT         REFERENCES bank_account ON DELETE CASCADE,
    amount           NUMERIC(12, 2) NOT NULL,
    transaction_type TEXT           NOT NULL
);
