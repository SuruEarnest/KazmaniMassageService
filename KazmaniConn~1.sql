SELECT * FROM client_tb;
select * from contact_tb;
select * from users;
select * from therapist_tb;
select * from qualification_tb;
select * from massage_tb;
select * from schedule_tb;

delete from massage_tb; 
delete from client_tb;
delete from contact_tb;
delete from users;
delete from therapist_tb;
delete from qualification_tb;

SELECT ROLE from USERS where USERNAME='silver' AND PASSWORD ='silverl247@';

SELECT 
 
CLIENT_TB.USER_NAME,CLIENT_TB.LAST_NAME,CLIENT_TB.FIRST_NAME,CLIENT_TB.GENDER,CONTACT_TB.EMAIL,
CONTACT_TB.PHONE_NUMBER,CONTACT_TB.ADDR_DESCRIPTION,CONTACT_TB.ADDR_LOCAL_GOVT,CONTACT_TB.ADDR_STATE,
CONTACT_TB.ADDR_TYPE,CONTACT_TB.ADDR_COUNTRY ,USERS.ROLE,USERS.PASSWORD
from

CLIENT_TB,CONTACT_TB,USERS 

WHERE  CLIENT_TB.PASSWORD = CONTACT_TB.PASSWORD AND CONTACT_TB.PASSWORD=USERS.PASSWORD AND USERS.PASSWORD='serihbra1118' ;




SELECT ROLE from USERS where USERNAME='seun' and PASSWORD='serihbra1111' 
OR email='seunadeleke@gmail.com' and password='serihbra1111' 
OR phone_number='0810181118' and password='serihbra1111';


SELECT CLIENT_TB.FIRST_NAME,CLIENT_TB.LAST_NAME,CLIENT_TB.PHONE_NUMBER,CLIENT_TB.GENDER,CONTACT_TB.EMAIL,CONTACT_TB.ADDR_COUNTRY,
CONTACT_TB.ADDR_TYPE,CONTACT_TB.ADDR_STATE,CONTACT_TB.ADDR_LOCAL_GOVT,CONTACT_TB.ADDR_DESCRIPTION
FROM CLIENT_TB,CONTACT_TB where CLIENT_TB.PHONE_NUMBER=CONTACT_TB.PHONE_NUMBER AND CONTACT_TB.PHONE_NUMBER = '0810181000' ;

