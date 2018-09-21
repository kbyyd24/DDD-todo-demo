CREATE TABLE check_item (
  id           VARCHAR(100) NOT NULL UNIQUE,
  todo_item_id VARCHAR(100) NOT NULL,
  status       VARCHAR(50)  NOT NULL,
  description  VARCHAR(500) NOT NULL
)