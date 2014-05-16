 
 create or replace view review as select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $y in /Review return $y'
             PASSING rt.REVIEWROW
             COLUMNS title VARCHAR2(30) PATH 'Book_Title',
              reviewer VARCHAR2(30) PATH 'Reviewer') rx;
create or replace view book as select bx.*
    from BOOKTABLE bt,
    XMLTABLE('for $x in /Book where month-from-dateTime($x/Publish_Date)>=8 and $x/Price>=25 return $x'            
              PASSING bt.BOOKROW
             COLUMNS title VARCHAR2(30) PATH 'Title',
              price float(10) PATH 'Price',
               publish_data VARCHAR2(30) PATH 'Publish_Date') bx;

select Distinct review.reviewer as reviewerName
from  review, book
 where review.title=book.title; 
 drop view review;
 drop view book;
 
 
 