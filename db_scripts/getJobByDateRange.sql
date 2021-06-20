DROP PROCEDURE IF EXISTS  getJobByDateRange;

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobByDateRange`(IN form_date varchar(20),IN to_date varchar(20))
BEGIN

SELECT created_at,
       amount,cost,
       end_date,
       name,
       job_number,
       status,
       total_square_feet,
       rate_per_square_feet,
       fk_customer_id
FROM   jobs
WHERE  DATE(created_at)>=form_date and date(created_at)<to_date;

END //

DELIMITER ;
