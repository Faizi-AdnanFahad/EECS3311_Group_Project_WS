-- For product database

CREATE TABLE Product (
	id INTEGER PRIMARY KEY,
	name TEXT NOT NULL,
    stock_qty INTEGER NOT NULL,
    targ_min_stock_qty INTEGER NOT NULL,
    targ_max_stock_qty INTEGER NOT NULL,
    targ_restock_val INTEGER NOT NULL,
    disc_strategy_id INTEGER
);