CREATE TABLE IF NOT EXISTS customers (
    customer_id TEXT PRIMARY KEY,
    first_name TEXT NOT NULL,
    surname TEXT NOT NULL,
    address TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    account_number TEXT PRIMARY KEY,
    account_type TEXT NOT NULL,
    balance REAL NOT NULL,
    branch TEXT NOT NULL,
    customer_id TEXT NOT NULL,
    company_name TEXT,
    company_address TEXT
);

CREATE TABLE IF NOT EXISTS transactions (
    tx_id INTEGER PRIMARY KEY AUTOINCREMENT,
    account_number TEXT NOT NULL,
    tx_type TEXT NOT NULL,
    amount REAL NOT NULL
);

INSERT OR IGNORE INTO customers VALUES ('C001', 'Thabo', 'Mokoena', 'Plot 123, Gaborone');
INSERT OR IGNORE INTO customers VALUES ('C002', 'Kefilwe', 'Kgosi', 'Block 8, Gaborone');
INSERT OR IGNORE INTO customers VALUES ('C003', 'Neo', 'Modise', 'Phakalane, Gaborone');
INSERT OR IGNORE INTO customers VALUES ('C004', 'Kabelo', 'Seretse', 'Extension 9, Gaborone');
INSERT OR IGNORE INTO customers VALUES ('C005', 'Lorato', 'Pule', 'Tlokweng');

INSERT OR IGNORE INTO accounts VALUES ('ACC1001', 'Savings', 1500.00, 'Gaborone Main', 'C001', NULL, NULL);
INSERT OR IGNORE INTO accounts VALUES ('ACC1002', 'Investment', 2500.00, 'Gaborone Main', 'C001', NULL, NULL);
INSERT OR IGNORE INTO accounts VALUES ('ACC1003', 'Cheque', 5000.00, 'Airport Junction', 'C002', 'Kgosi Holdings', 'CBD, Gaborone');

INSERT OR IGNORE INTO transactions (tx_id, account_number, tx_type, amount) VALUES (1, 'ACC1001', 'Deposit', 1500.00);
INSERT OR IGNORE INTO transactions (tx_id, account_number, tx_type, amount) VALUES (2, 'ACC1002', 'Deposit', 2500.00);