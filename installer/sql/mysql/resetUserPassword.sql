--
-- Script to reset a user's password. This script sets the password of the 
-- specified user to "adminadmin".
--
-- This script shouldn't be used unless you are told to do so by the development 
-- team or the installation documentation. There's a page in the application 
-- which lets you change your own password and a specific button to reset other 
-- user's passwords in the user details page. Obviously, that button can only 
-- be used if you are an administrator.
--
--

UPDATE User SET password='dd94709528bb1c83d08f3088d4043f4742891f4f' 
            WHERE login='<CHANGE THIS TO DESIRED USER LOGIN>'

