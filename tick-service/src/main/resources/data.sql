DROP TABLE IF EXISTS ticks;

--TIMESTAMP=<linux timestamp>|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ
--CREATE TABLE ticks (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  time_stamp VARCHAR(50) NOT NULL,
--  price DECIMAL(5,2) DEFAULT NULL,
--  close_price DECIMAL(5,2) DEFAULT NULL,
--  currency VARCHAR(10) NOT NULL,
--  ric VARCHAR(50) NOT NULL
--);
--
--CREATE TABLE export_indices (
--  id INT AUTO_INCREMENT PRIMARY KEY,
--  ric VARCHAR(50) NOT NULL,
--  closing_price_index INT NOT NULL,
--  previous_index INT NOT NULL,
--  status INT NOT NULL
--)