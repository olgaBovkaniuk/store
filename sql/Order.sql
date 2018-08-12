CREATE TABLE IF NOT EXISTS T_ORDER(
  ID INTEGER (5) AUTO_INCREMENT NOT NULL,
  ORDER_NUMBER VARCHAR (80) NOT NULL,
  USER_ID INTEGER (5) NOT NULL,
  PRODUCT_ID INTEGER (5) NOT NULL,
  TOTAL_PRICE DECIMAL (7) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES T_USER(ID),
  FOREIGN KEY (PRODUCT_ID) REFERENCES T_PRODUCT(ID)
)