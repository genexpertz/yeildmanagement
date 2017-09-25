DROP FUNCTION IF EXISTS insert_dates;
--  DELIMITER $$
    CREATE FUNCTION insert_dates(start_date DATE, num_days INT)
      RETURNS BOOL
      BEGIN
        DECLARE i INT DEFAULT 1;
        DECLARE id INT DEFAULT i;
        DECLARE date DATE DEFAULT start_date;

      WHILE i < num_days DO
      BEGIN
          INSERT INTO dates(id,date,month,year) values (i, date, month(date),year(date));
          SET i := i + 1;
          SET date := date_add(date, INTERVAL 1 DAY);
      END;
      END WHILE;
        RETURN TRUE;
   END;
-- DELIMITER ;
